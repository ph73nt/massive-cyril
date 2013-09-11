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
package couk.nucmedone.massivecyril.shared.labtest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import couk.nucmedone.common.patient.Patient;
import couk.nucmedone.common.patient.PatientName;
import couk.nucmedone.massivecyril.shared.labtest.AbstractCountingTube;
import couk.nucmedone.massivecyril.shared.labtest.CorrectedCounts;
import couk.nucmedone.massivecyril.shared.labtest.Counts;
import couk.nucmedone.massivecyril.shared.labtest.DoublePlus;
import couk.nucmedone.massivecyril.shared.labtest.Injection;
import couk.nucmedone.massivecyril.shared.labtest.SampleTube;
import couk.nucmedone.massivecyril.shared.labtest.Standard;

public class BNMS_2001_Audit {

	private static final int AUDIT_NUMBER = 10;
	private static final SimpleDateFormat countDate = new SimpleDateFormat(
			"DD-MMM-YYYY HH:mm:ss");

	private static Injection[] injections;
	private static SampleTube[] samples;
	private static Standard[] standards;
	private static Patient[] patients;

	public static Injection[] getInjections() {
		if (injections == null) {
			// When?
			Calendar cal = Calendar.getInstance();
			cal.set(2001, Calendar.FEBRUARY, 01, 9, 0);
			injections = new Injection[] { new Injection(10010d, 10d, 10d,
					4.535, 6.573, cal) };
		}
		return injections;
	}

	public static Standard[] getStandards() throws ParseException {
		if (standards == null) {

			// The count date
			Calendar date = Calendar.getInstance();
			date.setTime(countDate.parse("06-Feb-2001 18:46:46"));
			date.set(Calendar.YEAR, 2000);

			standards = new Standard[AUDIT_NUMBER];
			standards[0] = new Standard("");

			// Count date
			standards[0].setCountDate(date);
			// Just make reference date the same as the count date
			standards[0].setRefDate(date);
			// Count volume
			standards[0].setCountVolume(new DoublePlus(5, AbstractCountingTube.VOLUME_ERROR));
			// Counts
			standards[0].setCounts(new CorrectedCounts(35281, 0, 1200));
			// Weights
			standards[0].setEmptyWeight(new DoublePlus(4, AbstractCountingTube.WEIGHT_ERROR));
			standards[0].setFullWeight(new DoublePlus(4.5, AbstractCountingTube.WEIGHT_ERROR));
		}

		return standards;
	}

	public static SampleTube[] getSamples() {

		if (samples == null) {
			SampleTube sample1 = new SampleTube("One");

			// Sample time
			Calendar cal = Calendar.getInstance();
			cal.set(2001, Calendar.FEBRUARY, 01, 11, 0);
			sample1.setSampleTime(cal);
			// Count time
			cal.set(Calendar.HOUR_OF_DAY, 10);
			cal.set(Calendar.MINUTE, 41);
			sample1.setCountDate(cal);
			// Empty weight
			sample1.setEmptyWeight(new DoublePlus(1d, AbstractCountingTube.WEIGHT_ERROR));
			// Full weight
			sample1.setFullWeight(new DoublePlus(3d, AbstractCountingTube.WEIGHT_ERROR));
			// Set sample counts & background
			sample1.setCounts(new CorrectedCounts(new Counts(4600, 3600),
					new Counts(10, 3600)));

			samples = new SampleTube[] { sample1 };
		}

		return samples;
	}

	public static Patient[] getPatients() {
		if (patients == null) {
			Patient patient1 = new Patient();
			// Name
			patient1.setPatientName(new PatientName("AUDIT2001", "ONE", "", "",
					""));
			// Date of birth
			Calendar dob = Calendar.getInstance();
			dob.set(Calendar.YEAR, 1970);
			patient1.setDateOfBirth(dob);
			// height and weight
			patient1.setHeight(1.78);
			patient1.setWeight(70);
			// ID
			patient1.setPrimaryID("audit2001-1");

			patients = new Patient[] { patient1 };
		}
		return patients;
	}
}
