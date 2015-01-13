/**
 * @author Neil J Thomson (njt@fishlegs.co.uk)
 *
 * Copyright (C) 2013 Neil J Thomson
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or (at
 * your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 */
package couk.nucmedone.massivecyril.labtest;

import java.util.Calendar;

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.common.nuclides.Decay;
import couk.nucmedone.common.nuclides.Isotopes;
import couk.nucmedone.common.nuclides.Nuclides;
import couk.nucmedone.massivecyril.labtest.exceptions.StandardSensitivityException;

/**
 * A class to hold parameter for counting reference standards in nuclear
 * counting tests such as EDTA GFR and RCM. A Standard is a tracer volume in a
 * Flask object that has some total volume. A StandardsTube has a further
 * dilution of the tracer and some total volume. <br />
 * <br />
 * A typical EDTA standard would contain 0.5ml EDTA stock solution diluted to
 * 1l. The StandardsTube would then contain 0.5ml of this dilution further
 * diluted to 5ml for counting.
 * 
 * 
 * @author Neil J Thomson
 * 
 */
public class Standard {

	public Flask flask;
	public StandardsTube tube;
	public String name = "";

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

	/**
	 * Create a new Standards object initialised with an identifying name, the
	 * initial volume of tracer added and the total flask volume.
	 * 
	 * @param name An identifying name for the standard
	 * @param tracerVol The initial tracer volume added to the flask (e.g. 0.5ml EDTA)
	 * @param flaskVol The total flask volume (e.g. 500ml)
	 */
	public Standard(String name, double tracerVol, double flaskVol) {
		this.name = name;
		flask = new Flask(tracerVol, flaskVol);
		tube = new StandardsTube("T" + name);
	}

	public DoublePlus sensitivity() {
		return tube.cpsPerMl();
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
	public void setCountVolume(DoublePlus volume) {
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

	public void setWeights(DoublePlus emptyWeight, DoublePlus fullWeight) {
		tube.setFullWeight(fullWeight);
		tube.setEmptyWeight(emptyWeight);
		setTracerVolume();
	}

	private void setTracerVolume() {
		// Set the EDTA volume in the counting tube
		// ... first get dilution of stock in flask...
		DoublePlus dil = flask.getTracerVolume().div(flask.getFlaskVolume());
		// ... now scale to counting tube
		tube.setTracerVolume(dil, stockDensity);

		// Compare expected volume...

	}

}
