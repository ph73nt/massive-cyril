package couk.nucmedone.massivecyril.labtest;

import couk.nucmedone.massivecyril.labtest.exceptions.TimeTooShortFromAdminException;

public class TwoSampleGFR extends MultiSampleGFR {

	private int indexA = 0;
	private int indexB = 1;

	private double x1;
	private double y1;

	public TwoSampleGFR() throws TimeTooShortFromAdminException {
		super();
	}

	@Override
	public double getSlope() throws TimeTooShortFromAdminException {

		if (Double.isNaN(slope)){ //Double.NaN) {
			// Difference in y with respect to x... x is minutes, y is
			// concentration
			y1 = Math.log((samples[indexA].getConcentration()).value());
			double y2 = Math.log((samples[indexB].getConcentration()).value());
			x1 = samples[indexA].secondsFromAdmin() / 60;
			double x2 = samples[indexB].secondsFromAdmin() / 60;

			double dy = y2 - y1;
			double dx = x2 - x1;

			slope = dy / dx;
		}

		return slope;
	}

	@Override
	public double getIntercept() throws TimeTooShortFromAdminException {

		if (Double.isNaN(intercept)) {

			// simply y0 = mx0 + c
			// ... y1 = mx1 + c
			// m is the slope... what's that, then?
			double slp = getSlope();

			// Get c at some known values...
			double c = y1 - slp*x1;
			intercept = Math.exp(c);
		}

		return intercept;
	}

	/**
	 * As it may be possible to pick two samples from a potentially infinite
	 * array, allow the indices to be optionally set.
	 *
	 * @param a
	 *            The first index to consider in the sample set (defaults to
	 *            zero)
	 * @param b
	 *            The second (and final) index to consider in the sample set
	 *            (defaults to one)
	 */
	public void setIndices(int a, int b) {
		indexA = a;
		indexB = b;
	}
}
