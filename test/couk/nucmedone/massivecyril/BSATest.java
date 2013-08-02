package couk.nucmedone.massivecyril;

import junit.framework.Assert;
import junit.framework.TestCase;
import couk.nucmedone.massivecyril.shared.labtest.BSA;

public class BSATest extends TestCase {
    
	// Average man
	double height = 1.74;
	double weight = 70;
	double dubois = 1.840480395066;
	double haycock = 1.842585900415;
	double tolerance;
	
	public BSATest(String s) {
        super(s);
		tolerance = 0.000000000001;
    }

	public void testBodySurfaceArea_1(){		
		// Default to BNMS = haycock
		double actual = BSA.bodySurfaceArea(weight, height);
		Assert.assertEquals(haycock, actual, tolerance);		
	}
	
	public void testBodySurfaceArea_2(){
		// Force test BNMS method
		double actual = BSA.bodySurfaceArea(weight, height, BSA.BNMS);
		Assert.assertEquals(haycock, actual, tolerance);		
	}

	public void testBodySurfaceArea_3(){
		// Test haycock methods
		double actual = BSA.bodySurfaceArea(weight, height, BSA.HAYCOCK);
		Assert.assertEquals(haycock, actual, tolerance);
	}

	public void testBodySurfaceArea_4(){
		// Test haycock methods
		double actual = BSA.haycockSurfaceArea(weight, height);
		Assert.assertEquals(haycock, actual, tolerance);
	}
	
	public void testBodySurfaceArea_5(){
		// Test duBois method
		double actual = BSA.bodySurfaceArea(weight, height, BSA.DUBOIS);
		Assert.assertEquals(dubois, actual, tolerance);
	}

	public void testBodySurfaceArea_6(){
		// Test duBois method
		double actual = BSA.duBoisSurfaceArea(weight, height);
		Assert.assertEquals(dubois, actual, tolerance);
	}


}