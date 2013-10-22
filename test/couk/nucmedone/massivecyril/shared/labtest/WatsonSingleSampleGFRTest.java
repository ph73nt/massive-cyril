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

import couk.nucmedone.massivecyril.shared.labtest.WatsonSingleSampleGFR;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.StandardSensitivityException;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.TimeTooShortFromAdminException;

import junit.framework.Assert;

import junit.framework.TestCase;

public class WatsonSingleSampleGFRTest extends TestCase {
	
	public void testWatsonSingleSampleGFR() {
		try {
			// Empty GFR object
			WatsonSingleSampleGFR wsgfr = new WatsonSingleSampleGFR();
			// Add injection
			wsgfr.setInjection(BNMS_2001_Audit.getInjections()[0]);
			// Add sample tube
			wsgfr.setSamples(BNMS_2001_Audit.getSamples());
			// Add patient
			wsgfr.setPatient(BNMS_2001_Audit.getPatients()[0]);
			// Add standard
			wsgfr.addStandard(BNMS_2001_Audit.getStandards()[0]);
			wsgfr.gfr();
		} catch (TimeTooShortFromAdminException e) {
			Assert.fail();
		} catch (ParseException pe){
			Assert.fail("Failure parsing counting date");
		} catch (StandardSensitivityException e) {
			Assert.fail("Unexpecting count sensitivity");
		}
	}

}
