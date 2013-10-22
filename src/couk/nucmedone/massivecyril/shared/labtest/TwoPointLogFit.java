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

public class TwoPointLogFit extends AbstractLogCurveFit {

	public TwoPointLogFit(SampleTube[] samples) throws TimeTooShortFromAdminException {
		super(samples);
	}
	
	protected void fitLine() throws TimeTooShortFromAdminException {
		// Calculate gradient.. what did teacher say? Difference in y with respec' to x
		DoublePlus numerator = logConcs[1].minus(logConcs[0]);
		double denominator = samples[1].secondsFromAdmin() - samples[0].secondsFromAdmin();
		grad = numerator.div(denominator);
		
		// Y-intercept.. pretty trivial stuff
		root = logConcs[0].minus(grad.times(samples[0].secondsFromAdmin()));
	}
	
	public double Correlation() {
		return 1;
	}
	
}
