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
package couk.nucmedone.massivecyril.labtest;

import java.util.ArrayList;
import java.util.Iterator;

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.common.patient.Patient;
import couk.nucmedone.massivecyril.labtest.exceptions.TimeTooShortFromAdminException;

public abstract class GFR {

	/**
	 * Test the GFR calculations
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// TODO: Set properly through GUI
	public double allowableStdDiff = 2; // %
	protected DoublePlus relativeGFR = null;
	protected DoublePlus absoluteGFR = null;
	protected DoublePlus meanSensitivity = null;
	protected Injection injection;
	protected Patient patient;

	private ArrayList<Standard> standards = new ArrayList<Standard>();

	protected String[] warnings = null;

	protected SampleTube[] samples;

	public GFR() {

	}

	public DoublePlus absolute() throws TimeTooShortFromAdminException {

		if(absoluteGFR == null){
			calculateGFR();
		}

		return absoluteGFR;
	}

	public void addStandard(Standard standard) {
		// One more!
		standards.add(standard);

		// Update the mean sensitivity of the standards
		setMeanSensitivity();
	}

	// TODO: Find a better way of tracing errors - fire an event to an
	// Interface?
	@Deprecated
	private void addWarning(String warning) {

		if (!warnings.equals(null) && warnings.length > 0) {
			int len = warnings.length;
			int newLen = warnings.length + 1;
			String[] temp = new String[newLen];
			System.arraycopy(warnings, 0, temp, 0, len);
			temp[len] = warning;
		} else {
			warnings = new String[1];
			warnings[0] = warning;
		}
	}

	protected abstract void calculateGFR() throws TimeTooShortFromAdminException;

	protected double getBSA() {

		final double w = patient.getWeight();
		final double h = patient.getHeight();
		double bsa = BSA.bodySurfaceArea(w, h, BSA.BNMS);

		return bsa;
	}

	public Patient getPatient() {
		return patient;
	}

	public DoublePlus relative() throws TimeTooShortFromAdminException {

		if(relativeGFR == null){
			calculateGFR();
		}

		return relativeGFR;
	}

	protected DoublePlus relative(DoublePlus clearance) {

		double bsa = getBSA();

		return relative(clearance, bsa);

	}

	protected DoublePlus relative(DoublePlus clearance, double bsa) {

		DoublePlus relative = clearance.div(bsa);
		relative = relative.times(BSA.STD_BSA);

		return relative;

	}

	public void setInjection(Injection inj) {
		this.injection = inj;
	}

	// public DoublePlus gfr() throws TimeTooShortFromAdminException {
	//
	// // TODO: populate injFracAssess with boolean property.
	// boolean injFracAssess = true;
	//
	// Injection inj = new Injection();
	//
	// // TODO: get number of standards from somewhere...
	// int noStds = 2;
	// Standard[] stds = new Standard[noStds];
	//
	// for (int i = 0; i < noStds; i++) {
	// // Initialise all standards.
	// stds[i] = new Standard("S" + i, 0.5, 500);
	//
	// // TODO: set volumes and stuff!
	//
	// // Test for errors
	// if (!stds[i].tube.isGoodVolume()) {
	// addWarning("Volume of tracer in the standard, " + "vol"
	// + ", is below the minumum value of " + "vol");
	// }
	// }
	//
	// // get mean values and dtuff for the standards
	// DoublePlus meanSensitivity = stds[0].sensitivity();
	// DoublePlus minSensitivity = meanSensitivity;
	// DoublePlus maxSensitivity = meanSensitivity;
	//
	// for (int i = 1; i < noStds; i++) {
	// // get sum of sensitivities (for mean)
	// meanSensitivity.plus(stds[i].sensitivity());
	// // get max and min for error caculations
	// minSensitivity = stds[i].sensitivity().value() < minSensitivity
	// .value() ? stds[i].sensitivity() : minSensitivity;
	// maxSensitivity = stds[i].sensitivity().value() > maxSensitivity
	// .value() ? stds[i].sensitivity() : maxSensitivity;
	// }
	//
	// // Calculate mean
	// meanSensitivity = meanSensitivity.div(noStds);
	// // Get worst result
	// DoublePlus relDiff = maxSensitivity.minus(minSensitivity);
	// relDiff = relDiff.div(meanSensitivity);
	// relDiff = relDiff.times(100d); // Percent!
	// relDiff.setValue(Math.abs(relDiff.value())); // Must be positive
	//
	// if (relDiff.value() > allowableStdDiff) {
	// // throw some exception and possible allow user to choose a sample
	// // to drop
	// }
	//
	// // Do curve fit
	// // int len = samples.length;
	//
	// // Check if there's three or more samples.
	// // Two is bad practice as the "curve fit" is a straight line fit and no
	// // error checking is possible.
	// // One sample is awful and no curve fit can be done at all.
	// // if (len < 3) {
	// // String message =
	// //
	// "At least three samples are required to produce an accurate exponential curve fit.";
	// // throw new LowSampleNumberException(message);
	// // }
	//
	// return gfr;
	// }

	/**
	 * Best practice to use multiple counting standards. Calculate the mean
	 * sensitivity and propagate to the samples.
	 */
	private void setMeanSensitivity() {
		Iterator<Standard> it = standards.iterator();
		meanSensitivity = it.next().sensitivity();
		int len = standards.size();
		if (len > 1) {
			while (it.hasNext()) {
				meanSensitivity.plus(it.next().sensitivity());
			}
			meanSensitivity.div(len);
		}

		// Update samples, too
		for (int i = 0; i < samples.length; i++) {
			samples[i].setMeanSensitivity(meanSensitivity);
		}
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setSamples(SampleTube[] samples) {
		this.samples = samples;
	}

}
