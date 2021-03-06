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

/**
 * Java 1.4 compatible enum class for methods to calculate body surface area.
 * 
 * @author neil
 * 
 */
public class BSA {

	public static final BSA BNMS = new BSA("BNMS");
	public static final BSA HAYCOCK = new BSA("Haycock");
	public static final BSA DUBOIS = new BSA("DuBois");
	
	private String name;
	
	private BSA(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

	/**
	 * Calculate body surface area as recommended by the British Nuclear
	 * Medicine Society (BNMS) according to input height and weight.
	 * 
	 * @param weight
	 *            Patient weight in kilogrammes.
	 * @param height
	 *            Patient height in metres.
	 * @return The BNMS recommended BSA.
	 */
	public static double bodySurfaceArea(double weight, double height){
		// Default to BNMS recommended method of BSA calculation
		return bodySurfaceArea(weight, height, BSA.BNMS);
	}

	/**
	 * Calculate body surface area as a function of weight and height. Currently
	 * two methods are supported; DuBois and Haycock (recommended by the BNMS).
	 * 
	 * @param weight
	 *            Patient weight in kilogrammes.
	 * @param height
	 *            Patient height in metres.
	 * @param m
	 *            BNMS, Haycock or DuBois methods
	 * @return The body surface area result.
	 */
	public static double bodySurfaceArea(double weight, double height, BSA m){
		
		if (m.equals(BSA.DUBOIS)) {
			return duBoisSurfaceArea(weight, height);
		} else if (m.equals(BSA.BNMS) || m.equals(BSA.HAYCOCK)) {
			return haycockSurfaceArea(weight, height);
		}
		return 0d;
	}

	/**
	 * Calculates the body surface area of a patient as a function of height and
	 * weight according to the DuBois method. DuBois et al, A formula to
	 * estimate the approximate surface area if height and weight be known,
	 * Arch. Inter. Med. 17, 863-871 (1916) (Cited in ICRP 23, Reference Man)
	 * 
	 * @param weight
	 *            Patient weight in kilogrammes.
	 * @param height
	 *            Patient height in metres.
	 * @return The body surface area according to the DuBois method.
	 */
	public static double duBoisSurfaceArea(double weight, double height){		
		return 0.007184 * Math.pow(weight, 0.425) * Math.pow(100*height, 0.725);
	}

	/**
	 * Calculates the body surface area of a patient as a function of height and weight according to the Haycock method.
	 * @param weight
	 *            Patient weight in kilogrammes.
	 * @param height
	 *            Patient height in metres.
	 * @return The body surface area according to the Haycock method.
	 */
	public static double haycockSurfaceArea(double weight, double height){
		return 0.024265 * Math.pow(weight, 0.5378) * Math.pow(100*height, 0.3964);
	}
	
}
