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

public class Decay {

	/**
	 * Calculate the multiplying factor of a decaying radioactove sample, given
	 * the number of seconds after the sampling has taken place.
	 * 
	 * @param nuclide The nuclide being assayed.
	 * @param seconds The time post assay (s).
	 * @return The scaling factor.
	 */
	public static double postFactor(Nuclides nuclide, double seconds) {
		double decayConst = nuclide.halfLife() / seconds;
		return Math.exp(-1 * decayConst * seconds);
	}
	
	/**
	 * Calculate the multiplying factor of a decaying radioactive sample, given
	 * the number of seconds before the sampling has taken place.
	 * 
	 * @param nuclide The nuclide being assayed.
	 * @param seconds The time pre-assay (s).
	 * @return The scaling factor.
	 */
	public static double preFactor(Nuclides nuclide, double seconds) {
		double decayConst = Math.log(2) / nuclide.halfLife();
		return Math.exp(decayConst * seconds);
	}


}
