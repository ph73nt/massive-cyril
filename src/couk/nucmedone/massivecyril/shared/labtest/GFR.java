package couk.nucmedone.massivecyril.shared.labtest;

import java.util.ArrayList;

import couk.nucmedone.common.patient.Patient;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.TimeTooShortFromAdminException;

public abstract class GFR {

	public double correlation;
	public double covariance;
	public double errEqn;
	public double errMeas;
	public double intercept;
	public double interceptSD;
	public double tHhalf;
	public double tHalfSD;
	public double volDist;
	public double volDistSD;

	public static final int OKAY = 0;
	public static final int WARNING = 1;
	public static final int ERROR = 2;
	
	// TODO: Set properly through GUI
	public double allowableStdDiff = 2; //%
	
	protected DoublePlus halftime;
	protected DoublePlus volOfDist;
	protected DoublePlus clearanceTime;
	protected AbstractCurveFit curve;
	protected DoublePlus gfr; 
	protected double height;
	protected double weight;
	protected Injection injection;
	private Patient patient;
	private ArrayList<Standard> list = new ArrayList<Standard>();
	
	protected String[] warnings = null;
	
	protected SampleTube[] samples;
	
	public GFR() throws TimeTooShortFromAdminException {
		
	}

	public GFR(String obsolete) throws TimeTooShortFromAdminException {

		// TODO: populate injFracAssess with boolean property.
		boolean injFracAssess = true;

		Injection inj = new Injection();

		// TODO: get number of standards from somewhere...
		int noStds = 2;
		Standard[] stds = new Standard[noStds];

		for (int i = 0; i < noStds; i++) {
			// Initialise all standards.
			stds[i] = new Standard("S" + i);

			// TODO: set volumes and stuff!

			// Test for errors
			if (!stds[i].tube.isGoodVolume()) {
				addWarning("Volume of tracer in the standard, " + "vol"
						+ ", is below the minumum value of " + "vol");
			}
		}

		// get mean values and dtuff for the standards
		DoublePlus meanSensitivity = stds[0].sensitivity();
		DoublePlus minSensitivity = meanSensitivity;
		DoublePlus maxSensitivity = meanSensitivity;

		for (int i = 1; i < noStds; i++) {
			// get sum of sensitivities (for mean)
			meanSensitivity.plus(stds[i].sensitivity());
			// get max and min for error caculations
			minSensitivity = stds[i].sensitivity().value() < minSensitivity.value() ? stds[i].sensitivity() : minSensitivity;
			maxSensitivity = stds[i].sensitivity().value() > maxSensitivity.value() ? stds[i].sensitivity() : maxSensitivity;
		}

		// Calculate mean
		meanSensitivity = meanSensitivity.div(noStds);
		// Get worst result
		DoublePlus relDiff = maxSensitivity.minus(minSensitivity);
		relDiff = relDiff.div(meanSensitivity);
		relDiff = relDiff.times(100d); // Percent!
		relDiff.setValue(Math.abs(relDiff.value())); // Must be positive
		
		if (relDiff.value() > allowableStdDiff) {
			//throw some exception and possible allow user to choose a sample to drop
		}
		
		// Do curve fit
//		int len = samples.length;

		// Check if there's three or more samples.
		// Two is bad practice as the "curve fit" is a straight line fit and no
		// error checking is possible.
		// One sample is awful and no curve fit can be done at all.
//		if (len < 3) {
//			String message = "At least three samples are required to produce an accurate exponential curve fit.";
//			throw new LowSampleNumberException(message);
//		}
		
	}
	
	public void addStandard(Standard standard){
		list.add(standard);
	}

	/**
	 * Test the GFR calculations
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	protected DoublePlus extraCellularVolume(){
		return new DoublePlus();
	}

	// TODO: Find a better way of tracing errors - possibly custom exception
	// handling + try/catch
	private void addWarning(String warning) {

		if (!warnings.equals(null) && warnings.length > 0) {
			int len = warnings.length;
			int newLen = warnings.length + 1;
			String[] temp = new String[newLen];
			System.arraycopy(warnings, 0, temp, 0, len);
			temp[len] = warning;
		} else {
			warnings = new String[1];
			warnings[0] = warning;
		}
	}
	
	abstract public void setHalftime();
	
	public DoublePlus getHalftime() {
		return halftime;
	}
	
	abstract public void setClearance();
	
	public DoublePlus getClearance() {
		return clearanceTime;
	}
	
	abstract public void setVolOfDist();
	
	public DoublePlus getVolOfDist() {
		return volOfDist;
	}
	
	abstract public void setCurve();
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void setSamples(SampleTube[] samples) {
		this.samples = samples;
	}
	
	public void setInjection(Injection inj) {
		this.injection = inj;
	}
	
	public DoublePlus gfr() throws TimeTooShortFromAdminException {
		return gfr;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
