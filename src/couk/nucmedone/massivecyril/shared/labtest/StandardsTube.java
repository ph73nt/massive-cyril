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


public class StandardsTube extends AbstractCountingTube {
	
	private DoublePlus tracerVolume;
	private DoublePlus sensitivity;
	
	public StandardsTube(String name) {
		super(name);
	}
	
	/**
	 * Set the tracer volume in the counted sample. 
	 * 
	 * @param volume
	 *            The volume, in millilitres, of tracer (not total volume)
	 *            within the counting tube.
	 */
	public void setTracerVolume(DoublePlus volume) {
		tracerVolume = volume;
	}
	
	/**
	 * Set the tracer volume in the counted sample, based on an input dilution
	 * and density of tracer in the parent flask.
	 * 
	 * @param dilution The dilution of tracer in the stock flask.
	 * @param density The density of stock tracer in the parent flask.
	 */
	public void setTracerVolume(DoublePlus dilution, double density) {
		DoublePlus temp = dilution.times(density);
		tracerVolume = getTubeContents().times(temp);
	}

	/**
	 * Get the tracer volume in the counted sample.
	 * 
	 * @return The volume, in millilitres, of tracer (not total volume) within
	 *         the counting tube.
	 */
	public DoublePlus getTracerVolume() {
		return tracerVolume;
	}
	
	/**
	 * Calculate the counts per second per millilitre of tracer
	 * 
	 * @return The counter sensitivity to this standard.
	 */
	public DoublePlus sensitivity() {
		sensitivity = getCPS().div(tracerVolume);
		return sensitivity;
	}

}
