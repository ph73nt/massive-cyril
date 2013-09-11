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
import couk.nucmedone.massivecyril.shared.labtest.AbstractCountingTube;
import couk.nucmedone.massivecyril.shared.labtest.DoublePlus;
import couk.nucmedone.massivecyril.shared.labtest.StandardsTube;

public class StandardsTubeTest extends TestCase {
	
	private StandardsTube tube;
	private double tolerance = 0.0000001;
	
	public StandardsTubeTest(String s){
		super(s);
		tube = new StandardsTube("Tube");
	}
	
	public void testSetTracerVolume(){
		tube.setEmptyWeight(new DoublePlus(5.6, AbstractCountingTube.WEIGHT_ERROR));
		tube.setFullWeight(new DoublePlus(6.1, AbstractCountingTube.WEIGHT_ERROR));
		DoublePlus dil = new DoublePlus(0.5 / 500, 1);
		double dens = 1;
		tube.setTracerVolume(dil, dens);
		double actual = tube.getTracerVolume().value();
		double expected = 0.0005;
		Assert.assertEquals(expected, actual, tolerance);
	}
}