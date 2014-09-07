package couk.nucmedone.massivecyril.shared.labtest;

import static org.junit.Assert.fail;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.StandardSensitivityException;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.TimeTooShortFromAdminException;

public class ChantlerGFRTest {

	@Test
	public void test() {

		int patIndex = 0;

		MultiSampleGFR msgfr;
		try {
			
			msgfr = new MultiSampleGFR();
			// Add injection
			msgfr.setInjection(BNMS_2001_Audit.getInjections()[patIndex]);
			// Add sample tube
			msgfr.setSamples(BNMS_2001_Audit.getSamples()[patIndex]);
			// Add patient
			msgfr.setPatient(BNMS_2001_Audit.getPatients()[patIndex]);
			// Add standard
			msgfr.addStandard(BNMS_2001_Audit.getStandards()[patIndex]);
			
			DoublePlus gfr = ChantlerGFR.gfr(msgfr);
			System.out.println("Chantler corrected GFR: " + gfr.value());
			Assert.assertEquals(153.109, gfr.value(), 0.1);
			
		} catch (ParseException | StandardSensitivityException
				| TimeTooShortFromAdminException e) {
			fail(e.getMessage());
		}

	}

}
