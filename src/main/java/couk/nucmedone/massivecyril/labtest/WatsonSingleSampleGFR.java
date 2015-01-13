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

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.massivecyril.labtest.exceptions.TimeTooShortFromAdminException;


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
public class WatsonSingleSampleGFR extends SingleSampleGFR {

	public WatsonSingleSampleGFR() {

	}

	protected void calculateGFR() throws TimeTooShortFromAdminException {

		double mins = samples[sampleIndex].secondsFromAdmin() / 60;

		// Solve the famous quadratic equation!
		// (-b + sqrt(b^2 - 4ac))/2a.... check if sqrt is OK
		DoublePlus a = new DoublePlus(mins * (0.0000017 * mins - 0.0012),
				Double.MIN_VALUE);
		DoublePlus b = new DoublePlus((1.31 - 0.000775 * mins) * mins,
				Double.MIN_VALUE);
		// var3 a bit more complex:
		// log(ExCellVol/(vol/conc)) * ECV
		DoublePlus exCellVol = distributionVolume();
		DoublePlus c = injection.calculateVolumeInjected(true).div(
				samples[sampleIndex].getConcentration());
		c = exCellVol.div(c);
		c = DoublePlus.ln(c);
		c = c.times(exCellVol);

		System.out.println("A: " + a);
		System.out.println("B: " + b);
		System.out.println("C: " + c);

		DoublePlus b2 = b.times(b);
		DoublePlus fourAC = a.times(c).times(4);
		DoublePlus b2Minus4AC = b2.minus(fourAC);
		DoublePlus rootPart = DoublePlus.sqrt(b2Minus4AC);
		DoublePlus minusBPlusRootPart = rootPart.minus(b);
		DoublePlus twoA = a.times(2);

		DoublePlus answer = minusBPlusRootPart.div(twoA);
		System.out.println("(-b+(b^2 - 4ac)^0.5)/2a = " + answer);

		// Only need to consider -b + sqrt(b^2 etc) (ignore -b - sqrt(b^2 etc)):
		// The first item on the right-hand side of the equation is positive and
		// greater than 650 ml/min for t > 180 min, i.e., unphysiologically high
		// for GFR. Therefore, since a is negative, only the positive value
		// (J.Nucl.Med 1996; 37:1883-1890).
		absoluteGFR = answer;

		relativeGFR = relative(absoluteGFR);

	}

	protected DoublePlus distributionVolume() {

		return new DoublePlus(8116.6 * BSA.bodySurfaceArea(getPatient()
				.getWeight(), getPatient().getHeight()) - 28.2,
				Double.MIN_NORMAL);

	}

}
