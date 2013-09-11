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
 * A class to hold parameter for counting reference standard flasks
 * in nuclear counting tests such as EDTA GFR and RCM.
 * 
 * @author Neil J Thomson
 * 
 */
public class Flask {

	private DoublePlus flaskVolume, tracerVolume;
	
	// TODO: Set the flask volume error
	private double flaskError = 0.5; //ml
	
	// TODO: Set tracer volume error
	private double tracerError = 0.05; //ml
	
	public Flask(){}
	
	/**
	 * Set the volume (in millilitres) of total fluid in the flask the standard
	 * is contained in.
	 * 
	 * @param vol The container volume in millilitres.
	 */
	public void setFlaskVolume(double vol) {
		flaskVolume = new DoublePlus(vol, flaskError);
	}
	
	/**
	 * Get the volume (in millilitres) of total fluid in the flask the standard
	 * is contained in.
	 * 
	 * @return The container volume in millilitres.
	 */
	public DoublePlus getFlaskVolume(){
		return flaskVolume;
	}	
	
	/**
	 * Set the volume (in millilitres) of active tracer added to the standard
	 * flask.
	 * 
	 * @param vol
	 *            The volume of active tracer in millilitres.
	 */
	public void setTracerVolume(double vol) {
		tracerVolume = new DoublePlus(vol, tracerError);
	}

	/**
	 * Get the volume (in millilitres) of active tracer added to the standard
	 * flask.
	 * 
	 * @return The volume of active tracer in millilitres.
	 */
	public DoublePlus getTracerVolume() {
		return tracerVolume;
	}
	
}
