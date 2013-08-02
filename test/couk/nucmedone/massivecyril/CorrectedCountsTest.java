package couk.nucmedone.massivecyril;

import junit.framework.Assert;
import junit.framework.TestCase;
import couk.nucmedone.massivecyril.shared.labtest.CorrectedCounts;

public class CorrectedCountsTest extends TestCase {
	
	double bkg = 100;
	double bkTime = 60;	
	double cnts = 50000;
	double time = 1200;
	double tolerance = 0.0000001;
	
	public CorrectedCountsTest(String Name){
		super(Name);
	}
	
	public void testCounts_1(){
		// Test call for bk & main count time same
		CorrectedCounts c = new CorrectedCounts(cnts, bkg, time);
		double expected = 49900;
		double actual = c.counts().value();
		Assert.assertEquals(expected, actual, tolerance);
	}
	
	public void testCounts_2(){
		// Test call for bk & main count time different
		CorrectedCounts c = new CorrectedCounts(cnts, time, bkg, bkTime);
		double expected = 48000;
		double actual = c.counts().value();
		Assert.assertEquals(expected, actual, tolerance);
	}
	
	public void testCPS_1(){
		// Test call for bk & main count time same
		CorrectedCounts c = new CorrectedCounts(cnts, bkg, time);
		double expected = 41.58333333;
		double actual = c.cps().value();
		Assert.assertEquals(expected, actual, tolerance);
	}

	public void testCPS_2(){
		// Test call for bk & main count time different
		CorrectedCounts c = new CorrectedCounts(cnts, time, bkg, bkTime);
		double expected = 40;
		double actual = c.cps().value();
		Assert.assertEquals(expected, actual, tolerance);
	}
	
	public void testCpsSD(){
		CorrectedCounts c = new CorrectedCounts(cnts, bkg, time);
		double expected = 0.186525244;
		double actual = c.cps().SD();
		Assert.assertEquals(expected, actual, tolerance);
	}
	
}