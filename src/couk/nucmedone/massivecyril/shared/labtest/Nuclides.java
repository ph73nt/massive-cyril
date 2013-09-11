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

class Nuclides {
	
	private double halfLife;
	private String name;
	private String code;
	
	public Nuclides(String code, String name, double halfLife){
		this.code = code;
		this.name = name;
		this.halfLife = halfLife;
	}
	
	/**
	 * Half-life of the nuclide in seconds.
	 * 
	 * @return The half-life (s)
	 */
	public double halfLife(){
		return halfLife;
	}
	
	public String name(){
		return name;
	}
	
	public String code(){
		return code;
	}
	
	public String toString() {
		return name;
	}

}