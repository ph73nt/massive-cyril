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
package couk.nucmedone.massivecyril.labtest;

import org.junit.Assert;

import junit.framework.TestCase;
import couk.nucmedone.massivecyril.labtest.BSA;

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