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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import couk.nucmedone.common.base.Counts;
import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.common.patient.Patient;
import couk.nucmedone.common.patient.PatientName;
import couk.nucmedone.massivecyril.labtest.AbstractCountingTube;
import couk.nucmedone.massivecyril.labtest.CorrectedCounts;
import couk.nucmedone.massivecyril.labtest.Injection;
import couk.nucmedone.massivecyril.labtest.SampleTube;
import couk.nucmedone.massivecyril.labtest.Standard;
import couk.nucmedone.massivecyril.labtest.exceptions.StandardSensitivityException;

public class BNMS_2001_Audit {

	private static final int AUDIT_NUMBER = 10;
	private static final SimpleDateFormat countDate = new SimpleDateFormat(
			"DD-MMM-YYYY HH:mm:ss");

	private static Injection[] injections;
	private static SampleTube[][] samples;
	private static Standard[] standards;
	private static Patient[] patients;

	public static Injection[] getInjections() {
		if (injections == null) {
			// When?
			Calendar cal = Calendar.getInstance();
			cal.set(2001, Calendar.FEBRUARY, 01, 9, 0);
			injections = new Injection[] {
					new Injection(10010d, 10d, 10d, 4.535, 6.573, cal),
					new Injection(10010d, 10d, 10d, 4.505, 6.616, cal) };
		}
		return injections;
	}

	public static Standard[] getStandards() throws ParseException {
		if (standards == null) {

			standards = new Standard[AUDIT_NUMBER];

			/* Patient 1 */

			// The count date
			Calendar date1 = Calendar.getInstance();
			date1.setTime(countDate.parse("06-Feb-2001 18:46:46"));
			date1.set(Calendar.YEAR, 2000);

			standards[0] = new Standard("Audit1", 0.5, 500);

			// Count date
			standards[0].setCountDate(date1);
			// Just make reference date the same as the count date
			standards[0].setRefDate(date1);
			// Count volume
			standards[0].setCountVolume(new DoublePlus(5,
					AbstractCountingTube.VOLUME_ERROR));
			// Counts
			standards[0].setCounts(new CorrectedCounts(35281, 0, 1200));
			// Weights
			standards[0].setWeights(new DoublePlus(4.456,
					AbstractCountingTube.WEIGHT_ERROR), new DoublePlus(4.962,
					AbstractCountingTube.WEIGHT_ERROR));

			/* Patient 2 */

			// The count date
			Calendar date2 = Calendar.getInstance();
			date2.setTime(countDate.parse("06-Feb-2001 18:46:46"));
			date2.set(Calendar.YEAR, 2000);

			standards[1] = new Standard("Audit2", 0.5, 500);

			// Count date
			standards[1].setCountDate(date2);
			// Just make reference date the same as the count date
			standards[1].setRefDate(date2);
			// Count volume
			standards[1].setCountVolume(new DoublePlus(5,
					AbstractCountingTube.VOLUME_ERROR));
			// Counts
			standards[1].setCounts(new CorrectedCounts(20020, 0, 1200));
			// Weights
			standards[1].setWeights(new DoublePlus(4.621,
					AbstractCountingTube.WEIGHT_ERROR), new DoublePlus(5.12,
					AbstractCountingTube.WEIGHT_ERROR));

		}

		return standards;
	}

	public static SampleTube[][] getSamples() throws ParseException,
			StandardSensitivityException {

		if (samples == null) {

			/* Patient 1 */
			SampleTube sample11 = new SampleTube("One");

			// Sample time
			Calendar sampleTime11 = Calendar.getInstance();
			sampleTime11.set(2001, Calendar.FEBRUARY, 1, 11, 0);
			sample11.setSampleTime(sampleTime11);
			// Admin time
			Calendar adminTime11 = Calendar.getInstance();
			adminTime11.setTime(sampleTime11.getTime());
			adminTime11.set(Calendar.HOUR_OF_DAY, 9);
			sample11.setAdminTime(adminTime11);
			// Count time
			Calendar countTime = Calendar.getInstance();
			countTime.setTime(sampleTime11.getTime());
			countTime.set(Calendar.HOUR_OF_DAY, 10);
			countTime.set(Calendar.MINUTE, 41);
			sample11.setCountDate(countTime);
			// Empty weight
			sample11.setEmptyWeight(new DoublePlus(1d,
					AbstractCountingTube.WEIGHT_ERROR));
			// Full weight
			sample11.setFullWeight(new DoublePlus(3d,
					AbstractCountingTube.WEIGHT_ERROR));
			// Set sample counts & background
			sample11.setCounts(new CorrectedCounts(new Counts(4600, 1200),
					new Counts(10, 1200)));

			SampleTube sample12 = sample11.clone();
			// Sample time
			Calendar sampleTime12 = Calendar.getInstance();
			sampleTime12.set(2001, Calendar.FEBRUARY, 1, 12, 0);
			sample12.setSampleTime(sampleTime12);
			// Set sample counts & background
			sample12.setCounts(new CorrectedCounts(new Counts(3300, 1200),
					new Counts(10, 1200)));

			SampleTube sample13 = sample11.clone();
			// Sample time
			Calendar sampleTime13 = Calendar.getInstance();
			sampleTime13.set(2001, Calendar.FEBRUARY, 1, 13, 0);
			sample13.setSampleTime(sampleTime13);
			// Set sample counts & background
			sample13.setCounts(new CorrectedCounts(new Counts(2390, 1200),
					new Counts(10, 1200)));

			SampleTube[] patient1 = new SampleTube[] { sample11, sample12,
					sample13, };

			/* Patient 2 */

			SampleTube sample21 = new SampleTube("One");

			// Sample time
			Calendar sampleTime21 = Calendar.getInstance();
			sampleTime21.set(2001, Calendar.FEBRUARY, 1, 11, 0);
			sample21.setSampleTime(sampleTime21);
			// Admin time
			Calendar adminTime21 = Calendar.getInstance();
			adminTime21.setTime(sampleTime21.getTime());
			adminTime21.set(Calendar.HOUR_OF_DAY, 9);
			sample21.setAdminTime(adminTime21);
			// Count time
			Calendar countTime21 = Calendar.getInstance();
			countTime21.setTime(sampleTime21.getTime());
			countTime21.set(Calendar.HOUR_OF_DAY, 10);
			countTime21.set(Calendar.MINUTE, 41);
			sample21.setCountDate(countTime21);
			// Empty weight
			sample21.setEmptyWeight(new DoublePlus(1d,
					AbstractCountingTube.WEIGHT_ERROR));
			// Full weight
			sample21.setFullWeight(new DoublePlus(3d,
					AbstractCountingTube.WEIGHT_ERROR));
			// Set sample counts & background
			sample21.setCounts(new CorrectedCounts(new Counts(5480, 1200),
					new Counts(10, 1200)));

			SampleTube sample22 = sample21.clone();
			// Sample time
			Calendar sampleTime22 = Calendar.getInstance();
			sampleTime22.set(2001, Calendar.FEBRUARY, 1, 12, 0);
			sample22.setSampleTime(sampleTime22);
			// Set sample counts & background
			sample22.setCounts(new CorrectedCounts(new Counts(3690, 1200),
					new Counts(10, 1200)));

			SampleTube sample23 = sample21.clone();
			// Sample time
			Calendar sampleTime23 = Calendar.getInstance();
			sampleTime23.set(2001, Calendar.FEBRUARY, 1, 13, 0);
			sample23.setSampleTime(sampleTime23);
			// Set sample counts & background
			sample23.setCounts(new CorrectedCounts(new Counts(2830, 1200),
					new Counts(10, 1200)));

			SampleTube[] patient2 = new SampleTube[] { sample21, sample22,
					sample23, };

			samples = new SampleTube[][] { patient1, patient2 };
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
			Calendar dob1 = Calendar.getInstance();
			dob1.set(Calendar.YEAR, 1970);
			patient1.setDateOfBirth(dob1);
			// height and weight
			patient1.setHeight(1.78);
			patient1.setWeight(70);
			// ID
			patient1.setPrimaryID("audit2001-1");

			Patient patient2 = new Patient();
			patient2.setPatientName(new PatientName("AUDIT2001", "TWO", "", "",
					""));
			// Date of birth
			Calendar dob2 = Calendar.getInstance();
			dob2.set(Calendar.YEAR, 1996);
			patient2.setDateOfBirth(dob2);
			// height and weight
			patient2.setHeight(1.6);
			patient2.setWeight(81);
			// ID
			patient2.setPrimaryID("audit2001-2");

			patients = new Patient[] { patient1, patient2 };
		}
		return patients;
	}
}
