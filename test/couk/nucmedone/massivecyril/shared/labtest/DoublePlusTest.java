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

import junit.framework.Assert;
import junit.framework.TestCase;
import couk.nucmedone.common.base.DoublePlus;

public class DoublePlusTest extends TestCase {

	double dOne = 100;
	double dTwo = 76.9;
	double SDOne = 1;
	double SDTwo = 2;
	double tolerance = 0.00000001;
	
	DoublePlus one;
	DoublePlus two;
	DoublePlus plus;
	DoublePlus minus;
	DoublePlus times;
	DoublePlus timesTwo;
	DoublePlus div;
	DoublePlus divTwo;
	
	public DoublePlusTest(String name) {
		super(name);		
		one = new DoublePlus(dOne, SDOne);
		two = new DoublePlus(dTwo, SDTwo);
		plus = one.plus(two);
		minus = one.minus(two);
		times = one.times(two);
		timesTwo = one.times(dTwo);
		div = one.div(two);
		divTwo = one.div(dTwo);
	}
	
	public void testPlusVal() {
		double val = plus.value();
		double exp = dOne + dTwo;
		Assert.assertEquals(exp, val, tolerance);
	}
	
	public void testMinusVal() {
		double val = minus.value();
		double exp = dOne - dTwo;
		Assert.assertEquals(exp, val, tolerance);
	}

	public void testDivVal() {
		double val = div.value();
		double exp = dOne/dTwo;
		Assert.assertEquals(exp, val, tolerance);
	}
	
	public void testDivTwoVal() {
		double val = divTwo.value();
		double exp = dOne/dTwo;
		Assert.assertEquals(exp, val, tolerance);
	}

	public void testTimesVal() {
		double val = times.value();
		double exp = dOne * dTwo;
		Assert.assertEquals(exp, val, tolerance);
	}
	
	public void testTimesTwoVal() {
		double val = timesTwo.value();
		double exp = dOne * dTwo;
		Assert.assertEquals(exp, val, tolerance);
	}

	public void testPlusSD() {
		double val = plus.SD();
		double exp = Math.pow(SDOne, 2);
		exp += Math.pow(SDTwo, 2);
		exp = Math.sqrt(exp);		
		Assert.assertEquals(exp, val, tolerance);
	}
	
	public void testMinusSD() {
		double val = minus.SD();
		double exp = Math.pow(SDOne, 2);
		exp += Math.pow(SDTwo, 2);
		exp = Math.sqrt(exp);		
		Assert.assertEquals(exp, val, tolerance);
	}
	
	public void testDivSD() {
		double val = div.SD();
		double exp = Math.pow(SDOne/dOne, 2);
		exp += Math.pow(SDTwo/dTwo, 2);
		exp *= Math.pow(dOne/dTwo, 2);
		exp = Math.sqrt(exp);
		Assert.assertEquals(exp, val, tolerance);
	}
	
	public void testDivTwoSD() {
		double val = divTwo.SD();
		double exp = SDOne / dTwo;
		Assert.assertEquals(exp, val, tolerance);
	}
	
	public void testTimesSD() {
		double val = times.SD();
		double exp = Math.pow(SDOne/dOne, 2);
		exp += Math.pow(SDTwo/dTwo, 2);
		exp *= Math.pow(dOne*dTwo, 2);
		exp = Math.sqrt(exp);
		Assert.assertEquals(exp, val, tolerance);
	}
	
	public void testTimesTwoSD() {
		double val = timesTwo.SD();
		double exp = SDOne * dTwo;
		Assert.assertEquals(exp, val, tolerance);
	}
	
}