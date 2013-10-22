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

import java.util.Calendar;

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.TimeTooShortFromAdminException;

public class SampleTube extends AbstractCountingTube {

	// TODO: Set some sort of proper density
	private double density = 1d;
	private Calendar adminTime;
	private Calendar sampleTime;
	public double minimumSampleInterval = 60 * 60; // 1h in seconds!
	
	public SampleTube(String name) {
		super(name);
	}
	
	// TODO: should this be seconds from admin to sample - or both?
	public double secondsFromAdmin() throws TimeTooShortFromAdminException {
		double secondsSinceAdmin = countDateTime.getTimeInMillis();
		secondsSinceAdmin -= adminTime.getTimeInMillis();
		secondsSinceAdmin /= 1000;

		// Warn if the interval is less than some threshold... which will also
		// be thrown for silly errors like a negative time.
		if (secondsSinceAdmin < minimumSampleInterval) {
			String message = "Sample " + name
					+ " has short time between administration and counting ("
					+ secondsSinceAdmin + "s)"; 
			throw new TimeTooShortFromAdminException(message);
		}
		
		return secondsSinceAdmin;
	}
	
	public void setAdminTime(Calendar adminTime) {
		this.adminTime = adminTime;
	}
	
	public void setSampleTime(Calendar sampleTime) {
		this.sampleTime = sampleTime;
	}

	public void setDensity(double density) {
		this.density = density;
	}
	
	public double getDensity() {
		return density;
	}
	
	public DoublePlus getTubeContents() {
		DoublePlus contents = weightFull.minus(weightEmpty);
		contents = contents.times(density);
		return contents;
	}
	
	public Calendar getSampleTime(){
		return sampleTime;
	}

}
