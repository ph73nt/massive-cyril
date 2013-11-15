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

import junit.framework.TestCase;

import org.junit.Assert;

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.StandardSensitivityException;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.TimeTooShortFromAdminException;

public class WatsonSingleSampleGFRTest extends TestCase {

	public void testWatsonSingleSampleGFR() {
		try {
			// 2001 audit.
			// Patient 1, sample 1
			DoublePlus gfr = singleSampleGFR(0, 0);
			System.out.println("Patient 1, sample 1: " + gfr);
			Assert.assertEquals(169.238, gfr.value(), 0.01);

			// Patient 1, sample 2
			gfr = singleSampleGFR(0, 1);
			System.out.println("Patient 1, sample 2: " + gfr);
			Assert.assertEquals(139.792, gfr.value(), 0.01);

			// Patient 1, sample 3
			gfr = singleSampleGFR(0, 2);
			System.out.println("Patient 1, sample 3: " + gfr);
			Assert.assertEquals(126.961, gfr.value(), 0.01);

			// Patient 2, sample 1
			gfr = singleSampleGFR(1, 0);
			System.out.println("Patient 2, sample 1: " + gfr);
			Assert.assertEquals(78.203, gfr.value(), 0.01);

			// Patient 2, sample 2
			gfr = singleSampleGFR(1, 1);
			System.out.println("Patient 2, sample 2: " + gfr);
			Assert.assertEquals(85.736, gfr.value(), 0.01);

			// Patient 2, sample 3
			gfr = singleSampleGFR(1, 2);
			System.out.printf("Patient 2, sample 3: " + gfr);
			Assert.assertEquals(82.823, gfr.value(), 0.01);

		} catch (TimeTooShortFromAdminException e) {
			Assert.fail();
		} catch (ParseException pe) {
			Assert.fail("Failure parsing counting date");
		} catch (StandardSensitivityException e) {
			Assert.fail("Unexpecting count sensitivity");
		}
	}

	private DoublePlus singleSampleGFR(int patIndex, int sampleIndex)
			throws ParseException, StandardSensitivityException,
			TimeTooShortFromAdminException {
		// Empty GFR object
		WatsonSingleSampleGFR wsgfr = new WatsonSingleSampleGFR();
		// Add injection
		wsgfr.setInjection(BNMS_2001_Audit.getInjections()[patIndex]);
		// Add sample tube
		wsgfr.setSamples(BNMS_2001_Audit.getSamples()[patIndex]);
		// Add patient
		wsgfr.setPatient(BNMS_2001_Audit.getPatients()[patIndex]);
		// Add standard
		wsgfr.addStandard(BNMS_2001_Audit.getStandards()[patIndex]);
		// Which sample?
		wsgfr.setSampleIndex(sampleIndex);

		return wsgfr.gfr();
	}

}
