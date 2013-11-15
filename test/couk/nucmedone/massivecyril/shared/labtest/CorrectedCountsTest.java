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