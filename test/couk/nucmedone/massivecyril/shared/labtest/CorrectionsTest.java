package couk.nucmedone.massivecyril.shared.labtest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import couk.nucmedone.common.base.DoublePlus;

public class CorrectionsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void brochnerMortensenTest() {

		DoublePlus input = new DoublePlus(163.7519188854, Double.NaN);
		DoublePlus output = Corrections.brochnerMortensen(input);

		double expected = 124.6679708825;

		assertEquals(expected, output.value(), 1e-10);

	}

}
