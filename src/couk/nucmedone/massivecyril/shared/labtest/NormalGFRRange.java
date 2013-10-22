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

/**
 * Holds the normal GFR for the patients given age.
 * 
 * @author neil
 *
 */
public class NormalGFRRange {
	
	public DoublePlus gfr;
	private int age;
	
	/**
	 * Makes a normal range object for a given patient age.
	 * 
	 * @param age The age of the patient on the date of the test.
	 */
	public NormalGFRRange(int age) {
		
		this.age = age;
		
		gfr = new DoublePlus(mean(), sd());
		
	}
	
	/**
	 * Calculate the standard deviation of the normal range for the given age.
	 * 
	 * @return The standard deviation of the normal range.
	 */
	private double sd() {

		if (age <= 17) {
			return 17;
		} else if (age <= 20) {
			// Perform linear interpolation for SD normals at age 17 (17) and age 20 (12.5)
			return 17 + (4.5 * (17 - age) / 3); 
		} else {
			return 12.5;
		}
		
	}

	/**
	 * Calculate the mean value of the normal range for this age.
	 * 
	 * @return The mean of the normal range.
	 */
	private double mean() {
		
		if (age <= 17) {
			return 107 - (62 * Math.pow(0.985, (100 * age)));
		} else if (age <= 20) {
			// Perform linear interpolation for mean normals at age 17 and age 20
			return 107 + (2 * (age - 17) / 3); 
		} else if (age <= 50) {
			return 116 - (0.35 * age);
		} else {
			return 148 - age;
		}
		
	}

}
