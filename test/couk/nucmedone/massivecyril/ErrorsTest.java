package couk.nucmedone.massivecyril;

import junit.framework.Assert;
import junit.framework.TestCase;
import couk.nucmedone.massivecyril.shared.labtest.Errors;

public class ErrorsTest extends TestCase {
		
	double tolerance = 0.0000001;
	
	public ErrorsTest(String Name){
		super(Name);
	}
	
	public void test_var_aA(){
		double a = 20;
		double sd_A = 2;
		double expected = 1600;
		double actual = Errors.var_aA(a, sd_A);
		Assert.assertEquals(expected, actual, tolerance);
	}

	public void test_aA(){
		double a = 20;
		double sd_A = 2;
		double expected = 40;
		double actual = Errors.aA(a, sd_A);
		Assert.assertEquals(expected, actual, tolerance);
	}
	
	public void test_var_aA_plusMinus_bB(){
		double a = 2;
		double sd_A = 3;
		double b = 4;
		double sd_B = 5;
		double expected = 436;
		double actual = Errors.var_aA_plusMinus_bB(a, sd_A, b, sd_B);
		Assert.assertEquals(expected, actual, tolerance);
	}
	
	public void test_aA_plusMinus_bB(){
		double a = 2;
		double sd_A = 3;
		double b = 4;
		double sd_B = 5;
		double expected = 20.88061302;
		double actual = Errors.aA_plusMinus_bB(a, sd_A, b, sd_B);
		Assert.assertEquals(expected, actual, tolerance);
	}

}