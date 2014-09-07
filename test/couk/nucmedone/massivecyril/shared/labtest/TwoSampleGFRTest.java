package couk.nucmedone.massivecyril.shared.labtest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.TimeTooShortFromAdminException;

public class TwoSampleGFRTest {

	TwoSampleGFR tsg;

	@Before
	public void setUp() throws Exception {

		int patIndex = 0;

		tsg = new TwoSampleGFR();

		// Add injection
		tsg.setInjection(BNMS_2001_Audit.getInjections()[patIndex]);
		// Add sample tube
		tsg.setSamples(BNMS_2001_Audit.getSamples()[patIndex]);
		// Add patient
		tsg.setPatient(BNMS_2001_Audit.getPatients()[patIndex]);
		// Add standard
		tsg.addStandard(BNMS_2001_Audit.getStandards()[patIndex]);


	}

	@Test
	public void testSlope() {

		double slope = Double.NaN;
		try {
			slope = tsg.getSlope();
		} catch (TimeTooShortFromAdminException e) {
			fail(e.getMessage());
		}
		System.out.println("Slope = " + slope);

		assertEquals(-0.0055498743, slope, 1e-8);
	}

	@Test
	public void testIntercept() {

		double intercept = Double.NaN;
		try {
			intercept = tsg.getIntercept();
		} catch (TimeTooShortFromAdminException e) {
			fail(e.getMessage());
		}
		System.out.println("Intercept: " + intercept);

		assertEquals(6.4065747195e-005, intercept, 1e-10);

	}

	@Test
	public void testRelative() {

		DoublePlus gfr = null;
		try {
			gfr = tsg.relative();
		} catch (TimeTooShortFromAdminException e) {
			Assert.fail(e.getMessage());
		}
		System.out.println("Relative GFR: " + gfr + " ml/min/1.73m^2");
		Assert.assertEquals(124.939870956, gfr.value(), 1e-5);

	}

}
