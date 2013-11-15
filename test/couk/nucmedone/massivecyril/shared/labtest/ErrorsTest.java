/**
 * @author Neil J Thomson (njt@fishlegs.co.uk)
 *
 * Copyright (C) 2013 Neil J Thomson
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or (at
 * your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 */
package couk.nucmedone.massivecyril.shared.labtest;

import org.junit.Assert;
import junit.framework.TestCase;
import couk.nucmedone.common.base.Errors;

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