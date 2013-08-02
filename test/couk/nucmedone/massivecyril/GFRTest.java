package couk.nucmedone.massivecyril;

import junit.framework.Assert;
import junit.framework.TestCase;
import couk.nucmedone.massivecyril.shared.labtest.GFR;

public class GFRTest extends TestCase {
    
	// Average man
	double height = 1.74;
	double weight = 70;
	double dubois = 1.840480395066;
	double haycock = 1.842585900415;
	
	GFR gfr;
	double tolerance;
	
	public GFRTest(String s) {
        super(s);
//		gfr = new GFR();
		tolerance = 0.000000000001;
    }

	public void testNothing() {
		Assert.assertEquals(true, true);
	}

}