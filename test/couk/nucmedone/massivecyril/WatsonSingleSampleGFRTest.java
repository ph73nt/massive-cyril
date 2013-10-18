package couk.nucmedone.massivecyril;

import java.text.ParseException;

import couk.nucmedone.massivecyril.shared.labtest.DoublePlus;
import couk.nucmedone.massivecyril.shared.labtest.WatsonSingleSampleGFR;
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
			// Calculate!
			DoublePlus gfr = wsgfr.gfr();
			System.out.println(gfr.value() + " +/- " + gfr.SD());
		} catch (TimeTooShortFromAdminException e) {
			Assert.fail();
		} catch (ParseException pe){
			Assert.fail("Failure parsing counting date");
		}
	}

}
