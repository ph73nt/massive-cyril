package com.pukkaj.labtest;

import java.util.Calendar;

import com.pukkaj.labtest.exceptions.StandardSensitivityException;

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

	// TODO: Enable proper setting of stock solution (eg EDTA) density
	public double stockDensity = 1; /* g/ml */

	// TODO: Enable property setting for expected contents
	public double expectedTubeContents = 0.5; // ml

	// TODO: Enable method for setting expected sensitivity
	public DoublePlus expectedSensitivity = new DoublePlus(200000, 20000); // c/s/ml

	private Calendar refDate, countDate;

	// TODO: Proper method to set nuclide
	private Nuclides nuc = Isotopes.cr51;

	public Standard(String name) {
		this.name = name;
		flask = new Flask();
		tube = new StandardsTube("T" + name);
	}

	public void init() {

		// Set the EDTA volume in the counting tube
		// ... first get dilution of stock in flask...
		DoublePlus dil = flask.getTracerVolume().div(flask.getFlaskVolume());
		// ... now scale to counting tube
		tube.setTracerVolume(dil, stockDensity);

		// Compare expected volume...
	}

	public DoublePlus sensitivity() {
		return tube.sensitivity();
	}

	public void setCountDate(Calendar date) {
		countDate = date;
	}

	public DoublePlus referenceSensitivity(Calendar refDate)
			throws StandardSensitivityException {
		this.refDate = refDate;

		// Get time difference in milliseconds
		double decayTime = countDate.getTimeInMillis()
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

}
