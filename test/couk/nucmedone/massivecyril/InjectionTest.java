package couk.nucmedone.massivecyril;

import junit.framework.Assert;
import junit.framework.TestCase;
import couk.nucmedone.massivecyril.shared.labtest.DoublePlus;
import couk.nucmedone.massivecyril.shared.labtest.Injection;

public class InjectionTest extends TestCase {
    
	Injection inj;
	double tolerance;
	
	public InjectionTest(String s) {
        super(s);
        inj = new Injection();
        tolerance = 0.000000001;
	}

	public void testGetInjectedFraction_1(){
		double pre = 50000;
		double post = 4000;
		double bkg = 100;		
		DoublePlus res = inj.getInjectedFraction(pre, post, bkg, true);
		double exp1 = 0.921843687375;
		Assert.assertEquals(exp1, res.value(), tolerance);
	}
	
	public void testGetInjectedFraction_2(){
		// Test with erroneous pre counnts
		double pre = 1;
		double post = 4000;
		double bkg = 100;		

		DoublePlus res = inj.getInjectedFraction(pre, post, bkg, true);		
		double expected = Double.NaN;
		Assert.assertEquals(expected, res.value(), tolerance);
		
	}
	
	public void testGetInjectedFraction_3(){
		// Test with low post counts
		double pre = 50000;
		double post = 1;
		double bkg = 100;		

		DoublePlus res = inj.getInjectedFraction(pre, post, bkg, true);		
		double expected = 1;
		Assert.assertEquals(expected, res.value(), tolerance);
	}
	
	public void testGetInjectedFraction_4(){
		double pre = 50000;
		double post = 4000;
		double bkg = 100;
		double exp1 = 0.921843687375;

		// Calculate using plain method
		inj.setPreCounts(pre);
		inj.setPostCounts(post);
		inj.setBackgroundCounts(bkg);
		DoublePlus res = inj.getInjectedFraction(true);		
		Assert.assertEquals(exp1, res.value(), tolerance);
	}

	public void testCalculateVolumeInjected_1(){
		DoublePlus temp = inj.getInjectedFraction(51000, 26000, 1000, true);
		DoublePlus vol = inj.calculateVolumeInjected(new DoublePlus(100, 1), new DoublePlus(10, 1), true);
		Assert.assertEquals(45, vol.value(), tolerance);		
	}	
	
	public void testCalculateVolumeInjected_2(){
		DoublePlus temp = inj.getInjectedFraction(51000, 26000, 1000, true);
		inj.setFullWeight(200, 1);
		inj.setEmptyWeight(20, 1);
		DoublePlus vol = inj.calculateVolumeInjected(true);
		Assert.assertEquals(90, vol.value(), tolerance);		
	}
	
}
