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

public abstract class AbstractLogCurveFit extends AbstractCurveFit {
	
	protected DoublePlus[] logConcs;

	public AbstractLogCurveFit(SampleTube[] samples) throws TimeTooShortFromAdminException {
		super(samples);
	}
	
	protected void init() throws TimeTooShortFromAdminException {
		// Initialise logConcs
		logConcs = new DoublePlus[samples.length];

		// Get the natural log of concentrations
		logConcentrations();
		
		fitLine();
	}

	private void logConcentrations() {

		for (int i = 0; i < logConcs.length; i++) {
			logConcs[i] = DoublePlus.ln(samples[i].getConcentration());
		}

	}

}
