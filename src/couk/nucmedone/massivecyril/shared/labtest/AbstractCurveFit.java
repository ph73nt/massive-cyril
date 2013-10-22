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
 * Produce an exponential curve fit against labtest samples.
 * 
 * @author neil
 * 
 */
public abstract class AbstractCurveFit {

	protected SampleTube[] samples;
	
	protected DoublePlus grad;
	protected DoublePlus root;
	protected double correlation;
	
	// TODO: let admin user set this
	public double systematicError = 0.005; // 0.5% as a guess

	public AbstractCurveFit(SampleTube[] samples) throws TimeTooShortFromAdminException {

		this.samples = samples;
		init();

	}
	
	protected void init() throws TimeTooShortFromAdminException {
		fitLine();
	}
	
	abstract protected void fitLine() throws TimeTooShortFromAdminException;
	
	private DoublePlus gradient() {
		return grad;
	}
	
	private DoublePlus root() {
		return root;
	}
	
	public double correleation() {
		return correlation;
	}
}
