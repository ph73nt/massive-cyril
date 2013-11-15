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