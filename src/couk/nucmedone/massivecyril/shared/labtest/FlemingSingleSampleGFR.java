package couk.nucmedone.massivecyril.shared.labtest;

import couk.nucmedone.massivecyril.shared.labtest.exceptions.TimeTooShortFromAdminException;

public class FlemingSingleSampleGFR extends GFR {

	public FlemingSingleSampleGFR() throws TimeTooShortFromAdminException {
		// Reference:
		// J Fleming, et al, Nucl. Med. Commun., 2005(26):743
		
		// Get body surface area
		DoublePlus b = new DoublePlus(BSA.bodySurfaceArea(weight, height), Double.MIN_VALUE);
		
		// calculate time from injection
		double mins = samples[0].secondsFromAdmin() / 60;
		
		// Get the apparent volume
		DoublePlus vol = injection.calculateVolumeInjected(true).div(samples[0].concentration);
		vol = vol.div(1000d);
		
		gfr = b.times(1282d);
		gfr = gfr.plus(mins * 15.5 + 5862);
		gfr = gfr.times(DoublePlus.ln(vol));
		gfr = gfr.minus(b.times(4883).plus(41.9*mins + 11297));
		gfr = gfr.div(mins);
	}

	// @Override
	public void setHalftime() {
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

}
