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

import couk.nucmedone.common.base.Counts;
import couk.nucmedone.common.base.DoublePlus;


/**
 * Class to hold count assay parameters such as interval, background, counts,
 * SD, etc.
 * 
 * @author Neil J Thomson
 * 
 */
public class CorrectedCounts {
	
	private Counts background;
	private Counts sample;
	private DoublePlus counts, cps;
	
	public CorrectedCounts() {
		this(Double.NaN, Double.NaN, Double.NaN);
	}
	
	public static void main(String[] args) {
		double bkg = 100;
		double cnts = 50000;
		double time = 1200;
		
		CorrectedCounts c = new CorrectedCounts(cnts, bkg, time);
		double expected = 0.186525244;
		double actual = c.cps().SD();
		System.out.println(actual + " " + expected);
	}
	
	public CorrectedCounts(Counts cnts, Counts bkg) {
		background = bkg;
		sample = cnts;		
		
		// Correct for background and normalise
		cps = sample.getCPS();
		cps = cps.minus(background.getCPS());
		//cps = sample.getCPS().minus(background.getCPS());
		counts = cps.times(sample.getInterval());		
	}
	
	public CorrectedCounts(double counts, double bkg, double interval) {
		this(new Counts(counts, interval), new Counts(bkg, interval));
	}
	
	public CorrectedCounts(double cnt, double cntTime, double bkg, double bkgTime) {
		this(new Counts(cnt, cntTime), new Counts(bkg, bkgTime));
	}
	
	/**
	 * Get the background corrected counts for a sample.
	 * @return The counts.
	 */
	public DoublePlus counts() {
		return counts;
	}
	
	/**
	 * Get the background-corrected normalised counts for a sample.
	 * @return Counts per second.
	 */
	public DoublePlus cps() {
		return cps;
	}

}
