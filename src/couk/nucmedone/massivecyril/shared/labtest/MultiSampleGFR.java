package couk.nucmedone.massivecyril.shared.labtest;

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.common.gsl.LeastSquaresFit;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.TimeTooShortFromAdminException;

public class MultiSampleGFR extends GFR {

	private DoublePlus clearanceTime;
	private DoublePlus halftime;
	private double intercept = Double.NaN;
	private LeastSquaresFit leastsquaresfit;
	private double slope = Double.NaN;
	private double tHalfSD;

	private double tHhalf;
	private double volDist;
	private double volDistSD;
	private DoublePlus volOfDist;

	public MultiSampleGFR() throws TimeTooShortFromAdminException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public DoublePlus gfr() throws TimeTooShortFromAdminException {

		return null;
	}

	protected DoublePlus getClearance() {

		double slp = getSlope();
		DoublePlus distVol = getVolOfDist();
		clearanceTime = new DoublePlus();
		clearanceTime.setValue(-1 * slp * distVol.value());
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

	public double getHalftime() {

		if (Double.isNaN(tHhalf)) {

			double slp = getSlope();
			tHhalf = -1 * Math.log(2d) / slp;

		}

		return tHhalf;

	}

	/**
	 * The intercept of the concentration for the GFR curve is the exponential
	 * of the intercept of the curve fit of logarithmic concentration;
	 */
	public double getIntercept() {
		
		if (Double.isNaN(intercept)) {
			LeastSquaresFit lsf = getFit();
			intercept = Math.exp(lsf.c0());
		}
		
		return intercept;
		
	}

	/**
	 * Calculate the gradient of the logarithmic concentration fit.
	 */
	public double getSlope() {
		if (Double.isNaN(slope)) {
			LeastSquaresFit lsf = getFit();
			slope = lsf.c1();
		}
		return slope;
	}

	public DoublePlus getVolOfDist() {
		DoublePlus vol = injection.calculateVolumeInjected(true);
		double inter = getIntercept();
		return vol.div(inter);
	}

	/**
	 * Calculate and retrieve the volume of distribution.
	 * @return The volume of distribution.\\\\\\fs
	 */
	private DoublePlus volumeOfDistribution() {

		if (Double.isNaN(volOfDist.value())) {
			double intcpt = getIntercept();
			volOfDist = new DoublePlus();
			volOfDist.setValue(injection.calculateVolumeInjected(true).value()
					/ intcpt);

		}
		return volOfDist;
	}

}
