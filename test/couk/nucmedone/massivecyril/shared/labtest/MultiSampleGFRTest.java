package couk.nucmedone.massivecyril.shared.labtest;

import static org.junit.Assert.fail;

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

		DoublePlus vd = null;
		try {
			vd = msgfr.getVolOfDist();
		} catch (TimeTooShortFromAdminException e) {
			fail(e.getMessage());
		}
		System.out.println("Volume of distribution: " + vd);
		Assert.assertEquals(32154.550, vd.value(), 0.1);
	}

	@Test
	public void testCorrectedVolumeOfDistribution() {

		try {
			msgfr.relative();
		} catch (TimeTooShortFromAdminException e) {
			Assert.fail(e.getMessage());
		}

		DoublePlus vd = null;
		try {
			vd = msgfr.getVolOfDist();
		} catch (TimeTooShortFromAdminException e) {
			fail(e.getMessage());
		}
		System.out.println("Corrected volume of distribution: " + vd);
		Assert.assertEquals(24479.972, vd.value(), 0.1);
	}

	@Test
	public void testIntercept() {

		double intercept = Double.NaN;
		try {
			intercept = msgfr.getIntercept();
		} catch (TimeTooShortFromAdminException e) {
			fail(e.getMessage());
		}
		System.out.println("Intercept: " + intercept);
		Assert.assertEquals(6.33813873495933e-5, intercept, 0.1e-5);
	}

	@Test
	public void testSlope() {

		double slope = Double.NaN;
		try {
			slope = msgfr.getSlope();
		} catch (TimeTooShortFromAdminException e) {
			fail(e.getMessage());
		}
		System.out.println("Slope: " + slope);
		Assert.assertEquals(-0.0054731628, slope, 0.1e-5);
	}

	@Test
	public void testClearance() {

		DoublePlus clearance = null;
		try {
			clearance = msgfr.getClearance();
		} catch (TimeTooShortFromAdminException e) {
			fail(e.getMessage());
		}
		System.out.println("Clearance: " + clearance);
		Assert.assertEquals(175.9870879993, clearance.value(), 0.1e-3);
	}

	@Test
	public void testGFR() {

		DoublePlus gfr = null;
		try {
			gfr = msgfr.relative();
		} catch (TimeTooShortFromAdminException e) {
			Assert.fail(e.getMessage());
		}
		System.out.println("Relative GFR: " + gfr + " ml/min/1.73m^2");
		Assert.assertEquals(124.6679708825, gfr.value(), 1e-4);
	}

}
