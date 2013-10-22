package couk.nucmedone.massivecyril.shared.labtest;

import java.util.Calendar;

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.common.nuclides.Decay;
import couk.nucmedone.common.nuclides.Isotopes;
import couk.nucmedone.common.nuclides.Nuclides;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.StandardSensitivityException;

/**
 * A class to hold parameter for counting reference standards in nuclear
 * counting tests such as EDTA GFR and RCM.
 * 
 * @author neil@pukka-j.com
 * 
 */
public class Standard {

	public Flask flask;
	public StandardsTube tube;
	public String name = "";
	
	private DoublePlus flaskVolume;
	private DoublePlus tracerVolume;

	// TODO: Enable proper setting of stock solution (eg EDTA) density
	public double stockDensity = 1; /* g/ml */

	// TODO: Enable property setting for expected contents
	public double expectedTubeContents = 0.5; // ml

	// TODO: Enable method for setting expected sensitivity
	public DoublePlus expectedSensitivity = new DoublePlus(200000, 20000); // c/s/ml

	private Calendar refDate;

	// TODO: Proper method to set nuclide
	private Nuclides nuc = Isotopes.cr51;

	public Standard(String name, DoublePlus flaskVolume, DoublePlus tracerVolume) {
		this.name = name;
		flask = new Flask(flaskVolume, tracerVolume);
		tube = new StandardsTube("T" + name);
	}
	
	public void setEmptyWeight(DoublePlus weight){
		tube.setEmptyWeight(weight);
		setTracerVolume();
	}

	public DoublePlus sensitivity() {
		return tube.sensitivity();
	}

	public void setCountDate(Calendar date) {
		tube.setCountDate(date);
	}

	public DoublePlus referenceSensitivity()
			throws StandardSensitivityException {

		// Get time difference in milliseconds
		double decayTime = tube.getCountDate().getTimeInMillis()
				- refDate.getTimeInMillis();
		decayTime *= 1000; // convert to seconds

		// Find sensitivity on reference date
		double decayFactor = Decay.preFactor(nuc, decayTime);

		DoublePlus corSensitivity = sensitivity().times(decayFactor);
		double expSD = expectedSensitivity.SD();
		double exp = expectedSensitivity.value();
		double low = exp - expSD;
		double high = exp + expSD;
		if (corSensitivity.value() < low) {
			String mess = "The decay corrected sensitivity in standard " + name
					+ " of " + corSensitivity.value()
					+ " is below the expected threshold of " + exp + "+/-"
					+ expSD;
			throw new StandardSensitivityException(mess);
		} else if (corSensitivity.value() > high) {
			String mess = "The decay corrected sensitivity in standard " + name
					+ " of " + corSensitivity.value()
					+ " is above the expected threshold of " + exp + "+/-"
					+ expSD;
			throw new StandardSensitivityException(mess);
		}

		return corSensitivity;
	}

	public Calendar getRefDate() {
		return refDate;
	}

	public void setRefDate(Calendar refDate) {
		this.refDate = refDate;
	}
	
	/**
	 * Set the final volume used for counting the sample. The volume, for
	 * instance, could be a nominal volume always used or an exact volume
	 * measured. An exact volume implies a volume correction to be applied to
	 * the counting results.
	 * 
	 * @param volume
	 *            The volume (in millilitres) used for counting.
	 */
	public void setCountVolume(DoublePlus volume){
		tube.setCountingVolume(volume);
	}

	/**
	 * Set the number of counts, interval and background information collected
	 * for this sample.
	 * 
	 * @param counts
	 *            The total number of counts.
	 */
	public void setCounts(CorrectedCounts correctedCounts) {
		tube.setCounts(correctedCounts);
	}

	public void setFullWeight(DoublePlus weight) {
		tube.setFullWeight(weight);
		setTracerVolume();
	}

	public DoublePlus getTracerVolume() {
		return tracerVolume;
	}

	private void setTracerVolume() {
		if(tube.getFullWeight() != null && tube.getEmptyWeight() != null){
			// Set the EDTA volume in the counting tube
			// ... first get dilution of stock in flask...
			DoublePlus dil = flask.getTracerVolume().div(flask.getFlaskVolume());
			// ... now scale to counting tube
			tube.setTracerVolume(dil, stockDensity);

			// TODO: compare expected volume...
			
		}
	}

}
