package couk.nucmedone.massivecyril.shared.labtest;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.StandardSensitivityException;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.TimeTooShortFromAdminException;

public class MultiSampleGFRTest {
	
	MultiSampleGFR msgfr;

	@Before
	public void setUp() throws Exception {
		
		int patIndex = 0;

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
						
		} catch (ParseException | StandardSensitivityException
				| TimeTooShortFromAdminException e) {
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testVolumeOfDistribution() {

		DoublePlus vd = msgfr.getVolOfDist();
		System.out.println("Volume of distribution: " + vd);
		Assert.assertEquals(32154.550, vd.value(), 0.1);
	}
	
	@Test
	public void testIntercept() {
		
		double intercept = msgfr.getIntercept();
		System.out.println("Intercept: " + intercept);
		Assert.assertEquals(6.33813873495933e-5, intercept, 0.1e-5);		
	}
	
	@Test
	public void testSlope() {
		
		double slope = msgfr.getSlope();
		System.out.println("Slope: " + slope);
		Assert.assertEquals(-0.0054731628, slope, 0.1e-5);		
	}
	
	@Test
	public void testClearance() {
		
		DoublePlus clearance = msgfr.getClearance();
		System.out.println("Slope: " + clearance);
		Assert.assertEquals(175.9870879993, clearance.value(), 0.1e-5);		
	}
	
	@Test
	public void testGFR() {
		
		DoublePlus gfr = null;
		try {
			gfr = msgfr.gfr();
		} catch (TimeTooShortFromAdminException e) {
			Assert.fail(e.getMessage());
		}
		System.out.println("GFR: " + gfr);
		Assert.assertEquals(153.1087665594, gfr.value(), 0.1e-5);		
	}

}
