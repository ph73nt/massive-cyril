package com.pukkaj.labtest;

import com.pukkaj.labtest.exceptions.TimeTooShortFromAdminException;

public class WatsonSingleSampleGFR extends GFR {

	public WatsonSingleSampleGFR() throws TimeTooShortFromAdminException {

		super();
		
		// Reference:
		// A simple method of estimating glomerular filtration rate, WS Watson,
		// Eur. J. Nucl. Med. 1992(19):827

		double mins = samples[0].secondsFromAdmin() / 60;

		// Solve the famous quadratic equation!
		// -b/(2a) + (b^2 - 4ac).... then check if sqrt is OK
		DoublePlus a = new DoublePlus(mins * (0.0000017 * mins - 0.0012),
				Double.MIN_VALUE);
		DoublePlus b = new DoublePlus((1.31 - 0.000775 * mins) * mins,
				Double.MIN_VALUE);
		// var3 a bit more complex:
		// log(ExCellVol/(vol/conc)) * ECV
		DoublePlus c = injection.volumeInjected.div(samples[0].concentration);
		c = ExCellVol.div(c);
		c = DoublePlus.ln(c);
		c = c.times(ExCellVol);

		DoublePlus part1 = b.times(-1d).div(a.times(2));
		DoublePlus part2 = b.times(b).minus(a.times(c).times(4));
		gfr = part1.plus(part2);

		// get NaN for -ve number
		gfr = DoublePlus.sqrt(gfr);
		gfr = gfr.div(a.times(2));
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

}
