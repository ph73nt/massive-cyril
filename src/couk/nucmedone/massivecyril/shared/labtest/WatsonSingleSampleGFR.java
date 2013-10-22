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

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.TimeTooShortFromAdminException;

/**
 * Calculate an estimation of glomererular filtration rate based on a single
 * plasma sample. <br /><br />Reference:<br />A simple method of estimating glomerular filtration
 * rate, WS Watson, Eur. J. Nucl. Med. 1992(19):827
 * 
 * @author Neil J Thomson
 * 
 */
public class WatsonSingleSampleGFR extends GFR {

	public WatsonSingleSampleGFR() throws TimeTooShortFromAdminException {

	}

	public void setHalftime() {
		// TODO Auto-generated method stub

	}

	public void setClearance() {
		// TODO Auto-generated method stub

	}

	public void setVolOfDist() {
		// TODO Auto-generated method stub

	}

	public void setCurve() {
		// TODO Auto-generated method stub

	}

	public DoublePlus gfr() throws TimeTooShortFromAdminException {
		double mins = samples[0].secondsFromAdmin() / 60;

		// Solve the famous quadratic equation!
		// -b/(2a) + (b^2 - 4ac).... then check if sqrt is OK
		DoublePlus a = new DoublePlus(mins * (0.0000017 * mins - 0.0012),
				Double.MIN_VALUE);
		DoublePlus b = new DoublePlus((1.31 - 0.000775 * mins) * mins,
				Double.MIN_VALUE);
		// var3 a bit more complex:
		// log(ExCellVol/(vol/conc)) * ECV
		DoublePlus c = injection.calculateVolumeInjected(true).div(
				samples[0].getConcentration());
		DoublePlus exCellVol = extraCellularVolume();
		c = exCellVol.div(c);
		c = DoublePlus.ln(c);
		c = c.times(exCellVol);

		// Part 1 is -b/2a
		DoublePlus part1 = b.times(-1d).div(a.times(2));
		// Part 2 b^2 - 4ac
		DoublePlus part2 = b.times(b).minus(a.times(c).times(4));
		// Add together... but must be positive in the REAL world (no imaginary components of kidney function in this universe!)
		gfr = part1.plus(part2);
		// get NaN for -ve number
		gfr = DoublePlus.sqrt(gfr);
		gfr = gfr.div(a.times(2));
		return gfr;
	}
	
	protected DoublePlus extraCellularVolume(){
		return new DoublePlus(8116.6 * BSA.bodySurfaceArea(getPatient().getWeight(), getPatient().getHeight()) - 28.2, Double.MIN_NORMAL);
	}

}
