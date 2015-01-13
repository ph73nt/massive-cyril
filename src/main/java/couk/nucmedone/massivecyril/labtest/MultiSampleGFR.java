package couk.nucmedone.massivecyril.labtest;

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.common.gsl.LeastSquaresFit;
import couk.nucmedone.massivecyril.labtest.exceptions.TimeTooShortFromAdminException;

public class MultiSampleGFR extends GFR {

	private DoublePlus clearanceTime = null;
	protected double intercept = Double.NaN;
	private LeastSquaresFit leastsquaresfit = null;
	protected double slope = Double.NaN;

	private double tHhalf = Double.NaN;
	private DoublePlus volOfDist = null;

	public MultiSampleGFR() throws TimeTooShortFromAdminException {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void calculateGFR() throws TimeTooShortFromAdminException {

		double bsa = getBSA();

		// Correct clearance for BSA
		DoublePlus clearance = getClearance();
		relativeGFR = relative(clearance, bsa);
		relativeGFR = Corrections.brochnerMortensen(relativeGFR);

		// Absolute GFR calculated from relative GFR
		absoluteGFR = relativeGFR.times(bsa).div(BSA.STD_BSA);

		// Correct the volume of distribution
		DoublePlus distVol = getVolOfDist();
		distVol = distVol.div(clearance);
		distVol = distVol.times(absoluteGFR);
		volOfDist = distVol;

	}

	protected DoublePlus getClearance() throws TimeTooShortFromAdminException {

		if (clearanceTime == null) {

			double slp = getSlope();
			DoublePlus distVol = getVolOfDist();
			clearanceTime = distVol.times(-1 * slp);

		}

		return clearanceTime;

	}

	/**
	 * Calculate the slope and intercept by least squares fitting
	 *
	 * @return
	 */
	private LeastSquaresFit getFit() {

		if (leastsquaresfit == null) {

			// Take logs of the concentration
			double[] logConc = new double[samples.length];
			double[] mins = new double[samples.length];
			for (int i = 0; i < samples.length; i++) {
				logConc[i] = Math.log((samples[i].getConcentration()).value());
				try {
					mins[i] = samples[i].secondsFromAdmin() / 60;
				} catch (TimeTooShortFromAdminException e) {
					e.printStackTrace();
				}
			}

			leastsquaresfit = new LeastSquaresFit(mins, logConc,
					logConc.length, 1, 1);
		}

		return leastsquaresfit;
	}

	public double getHalftime() throws TimeTooShortFromAdminException {

		if (Double.isNaN(tHhalf)) {

			double slp = getSlope();
			tHhalf = -1 * Math.log(2d) / slp;

		}

		return tHhalf;

	}

	/**
	 * The intercept of the concentration for the GFR curve is the exponential
	 * of the intercept of the curve fit of logarithmic concentration;
	 * @throws TimeTooShortFromAdminException
	 */
	public double getIntercept() throws TimeTooShortFromAdminException {

		if (Double.isNaN(intercept)) {
			LeastSquaresFit lsf = getFit();
			intercept = Math.exp(lsf.c0());
		}

		return intercept;

	}

	/**
	 * Calculate the gradient of the logarithmic concentration fit.
	 * @throws TimeTooShortFromAdminException
	 */
	public double getSlope() throws TimeTooShortFromAdminException {
		if (Double.isNaN(slope)) {
			LeastSquaresFit lsf = getFit();
			slope = lsf.c1();
		}
		return slope;
	}

	/**
	 * Calculate and retrieve the volume of distribution.
	 *
	 * @return The volume of distribution.
	 * @throws TimeTooShortFromAdminException
	 */
	public DoublePlus getVolOfDist() throws TimeTooShortFromAdminException {

		if (volOfDist == null) {

			DoublePlus vol = injection.calculateVolumeInjected(true);
			double inter = getIntercept();
			volOfDist = vol.div(inter);

		}

		return volOfDist;
	}

}
