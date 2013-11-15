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

public class FlemingSingleSampleGFR extends GFR {

	public FlemingSingleSampleGFR() throws TimeTooShortFromAdminException {

	}

	// @Override
	public void getHalftime() {
		// TODO Auto-generated method stub

	}

	// @Override
	public void setClearance() {
		// TODO Auto-generated method stub

	}

	// @Override
	public void setVolOfDist() {
		// TODO Auto-generated method stub

	}

	// @Override
	public void setCurve() {
		// TODO Auto-generated method stub

	}

	@Override
	public DoublePlus gfr() throws TimeTooShortFromAdminException {
		// Reference:
		// J Fleming, et al, Nucl. Med. Commun., 2005(26):743
		
		// Get body surface area
		double w = patient.getWeight();
		double h = patient.getHeight();
		DoublePlus b = new DoublePlus(BSA.bodySurfaceArea(w, h), Double.MIN_VALUE);
		
		// calculate time from injection
		double mins = samples[0].secondsFromAdmin() / 60;
		
		// Get the apparent volume
		DoublePlus vol = injection.calculateVolumeInjected(true).div(samples[0].getConcentration());
		vol = vol.div(1000d);
		
		gfr = b.times(1282d);
		gfr = gfr.plus(mins * 15.5 + 5862);
		gfr = gfr.times(DoublePlus.ln(vol));
		gfr = gfr.minus(b.times(4883).plus(41.9*mins + 11297));
		gfr = gfr.div(mins);
		return gfr;
	}

}
