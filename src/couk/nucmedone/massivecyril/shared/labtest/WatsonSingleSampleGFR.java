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
 * plasma sample. <br />
 * <br />
 * Reference:<br />
 * A simple method of estimating glomerular filtration rate, WS Watson, Eur. J.
 * Nucl. Med. 1992(19):827
 * 
 * @author Neil J Thomson
 * 
 */
public class WatsonSingleSampleGFR extends GFR {

	private int sampleIndex = 0;

	public WatsonSingleSampleGFR() throws TimeTooShortFromAdminException {

	}

	public void getHalftime() {
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

		double mins = samples[sampleIndex].secondsFromAdmin() / 60;

		// Solve the famous quadratic equation!
		// (-b + sqrt(b^2 - 4ac))/2a.... check if sqrt is OK
		DoublePlus a = new DoublePlus(mins * (0.0000017 * mins - 0.0012),
				Double.MIN_VALUE);
		DoublePlus b = new DoublePlus((1.31 - 0.000775 * mins) * mins,
				Double.MIN_VALUE);
		// var3 a bit more complex:
		// log(ExCellVol/(vol/conc)) * ECV
		DoublePlus exCellVol = extraCellularVolume();
		DoublePlus c = injection.calculateVolumeInjected(true).div(
				samples[sampleIndex].getConcentration());
		c = exCellVol.div(c);
		c = DoublePlus.ln(c);
		c = c.times(exCellVol);

		gfr = b.times(b).minus(a.times(c).times(4));
		gfr = DoublePlus.sqrt(gfr);
		// Only need to consider -b + sqrt(b^2 etc) (ignore -b - sqrt(b^2 etc)):
		// The first item on the right-hand side of the equation is positive and
		// greater than 650 ml/min for t > 180 min, i.e., unphysiologically high
		// for GFR. Therefore, since a is negative, only the positive value
		// (J.Nucl.Med 1996; 37:1883-1890).
		gfr = gfr.minus(b);
		gfr = gfr.div(a.times(2));
		return gfr;
	}

	protected DoublePlus extraCellularVolume() {
		return new DoublePlus(8116.6 * BSA.bodySurfaceArea(getPatient()
				.getWeight(), getPatient().getHeight()) - 28.2,
				Double.MIN_NORMAL);
	}

	/**
	 * For GFRs where more than one sample has been provided, the single sample
	 * GFR can be calculated for any or all samples. The default index is zero.
	 * Use this method to set an alternative sample.
	 * 
	 * @param index
	 */
	public void setSampleIndex(int index) {
		sampleIndex = index;
	}

}
