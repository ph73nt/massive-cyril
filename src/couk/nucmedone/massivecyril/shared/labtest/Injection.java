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

/**
 * A class to hold parameters for nuclear counting test administrations such as
 * a syringe of Cr-51 EDTA.
 * 
 * @author Neil J Thomson
 * 
 */
public class Injection {

	private DoublePlus weightFull = new DoublePlus();
	private DoublePlus weightEmpty = new DoublePlus();
	private DoublePlus countsFull = new DoublePlus();
	private DoublePlus countsResidual = new DoublePlus();
	private DoublePlus countsBackground = new DoublePlus();
	private DoublePlus volumeFull = new DoublePlus();
	private DoublePlus volumeEmpty = new DoublePlus();
	private DoublePlus volumeInjected = new DoublePlus();
	private DoublePlus activityNominal = new DoublePlus();
	private DoublePlus injectedFraction;

	private Calendar adminTime;

	// TODO: Make syringe base error an accessible property
	private double syringeBaseErr = 0.005;

	public Injection() {
	}

	public Injection(double countsFull, double countsResidual,
			double countsBkg, double emptyWeight, double fullWeight,
			Calendar adminTime) {
		setPreCounts(countsFull);
		setPostCounts(countsResidual);
		setBackgroundCounts(countsBkg);
		setEmptyWeight(emptyWeight, syringeBaseErr);
		setFullWeight(fullWeight, syringeBaseErr);
		setAdminTime(adminTime);
	}

	/**
	 * Set the pre-injection counts of an assayed syringe containing a full
	 * patient dose.
	 * 
	 * @param counts
	 *            The pre-injection counts.
	 */
	public void setPreCounts(double counts) {
		countsFull.setValue(counts);
		countsFull.setSD(Math.sqrt(counts));
	}

	/**
	 * Get the pre-injection counts of an assayed syringe containing a full
	 * patient dose.
	 * 
	 * @return The pre-injection counts.
	 */
	public DoublePlus getPreCounts() {
		return countsFull;
	}

	/**
	 * Set the post-injection counts of an assayed syringe containing the
	 * residual of a used dose;
	 * 
	 * @param counts
	 *            The post-injection counts.
	 */
	public void setPostCounts(double counts) {
		countsResidual.setValue(counts);
		countsResidual.setSD(Math.sqrt(counts));
	}

	/**
	 * Get the post-injection counts of an assayed syringe containing the
	 * residual of a used dose;
	 * 
	 * @return The post-injection counts.
	 */
	public DoublePlus getPostCounts() {
		return countsResidual;
	}

	/**
	 * Set the background counts for a syringe assay.
	 * 
	 * @param counts
	 *            The background counts.
	 */
	public void setBackgroundCounts(double counts) {
		countsBackground.setValue(counts);
		countsBackground.setSD(Math.sqrt(counts));
	}

	/**
	 * Get the background counts for a syringe assay.
	 * 
	 * @return The background counts.
	 */
	public DoublePlus getBackgroundCounts() {
		return countsBackground;
	}

	/**
	 * Calculate fraction of radiopharmaceutical injected given assayed
	 * pre-injection, post-injection and background counts. Sets the value of
	 * public field injectedFraction as well as the field values of full and
	 * residual counts.
	 * 
	 * @param preCounts
	 *            Pre-injection assay.
	 * @param postCounts
	 *            Post injection assay.
	 * @param bkgCounts
	 *            background count assay.
	 * @return The fraction of injected activity. Returns Double.NaN when the
	 *         background is greater than the pre-injection counts (indicates an
	 *         assay error).
	 */
	public DoublePlus getInjectedFraction(double preCounts, double postCounts,
			double bkgCounts, boolean injFracAssess) {

		setPreCounts(preCounts);
		setPostCounts(postCounts);
		setBackgroundCounts(bkgCounts);
		return getInjectedFraction(injFracAssess);

	}

	/**
	 * Calculate fraction of radiopharmaceutical injected given assayed
	 * pre-injection, post-injection and background counts. Sets the value of
	 * public field injectedFraction.
	 * 
	 * @param bkgCounts
	 *            background count assay.
	 */
	public DoublePlus getInjectedFraction(boolean injFracAssess) {

		if (injectedFraction == null) {
			injectedFraction = new DoublePlus();
			if (injFracAssess) {
				// Pre-counts must be greater than the background - otherwise
				// there
				// is a problem with the assay
				DoublePlus denominator = countsFull.minus(countsBackground);
				if (denominator.value() >= 0) {					
					// Post-counts may be less than background by statistical
					// fluctuation - in which case full injection has been
					// achieved
					if (countsResidual.value() - countsBackground.value() < 0) {
						injectedFraction.setValue(1);
					} else {
						// do full minus residual to save subtracting quotient
						// from 1
						DoublePlus numerator = countsFull.minus(countsResidual);
						injectedFraction = numerator.div(denominator);
					}
				}
			} else {
				injectedFraction.setValue(1);
				injectedFraction.setSD(0);
			}
		}

		return injectedFraction;

	}

	/**
	 * Calculate volume injected from the injected fraction, full weight and
	 * empty weight.
	 * 
	 * @param fullWeight
	 *            Weight of syringe (in grammes) containing full patient dose.
	 * @param emptyWeight
	 *            Weight of syringe (in grammes) before adding tracer.
	 */
	public DoublePlus calculateVolumeInjected(DoublePlus fullWeight,
			DoublePlus emptyWeight, boolean injFracAssess) {
		weightFull = fullWeight;
		weightEmpty = emptyWeight;
		return calculateVolumeInjected(injFracAssess);
	}

	/**
	 * Calculate volume injected from the injected fraction, full weight and
	 * empty weight. Results is in millilitres, assuming 1g/ml
	 * 
	 */
	public DoublePlus calculateVolumeInjected(boolean injFracAssess) {
		volumeInjected = weightFull.minus(weightEmpty);
		volumeInjected = volumeInjected.times(getInjectedFraction(injFracAssess));
		return volumeInjected;
	}

	/**
	 * Set the full (pre-injection) weight of the syringe containing the
	 * radionuclide for injection.
	 * 
	 * @param weight
	 *            The syringe weight in grammes.
	 */
	public void setFullWeight(double weight, double weightError) {
		weightFull.setValue(weight);
		weightFull.setSD(weightError);
	}

	/**
	 * Get the full (pre-injection) weight of the syringe containing the
	 * radionuclide for injection.
	 * 
	 * @return The syringe weight in grammes.
	 */
	public DoublePlus getFullWeight() {
		return weightFull;
	}

	/**
	 * Set the weight of empty syringe and needle before drawing-up tracer
	 * volume.
	 * 
	 * @param weight
	 *            The weight of the syringe assembly.
	 */
	public void setEmptyWeight(double weight, double weightError) {
		weightEmpty.setValue(weight);
		weightEmpty.setSD(weightError);
	}

	/**
	 * Get the weight of empty syringe and needle before drawing-up tracer
	 * volume.
	 * 
	 * @return The weight of the syringe assembly.
	 */
	public DoublePlus getEmptyWeight() {
		return weightEmpty;
	}

	/**
	 * Set the date and time of the tracer administration.
	 * 
	 * @param dateTime
	 *            the date and time of administration.
	 */
	public void setAdminTime(Calendar dateTime) {
		this.adminTime = dateTime;
	}

	/**
	 * get a calendar object representing the date and time of tracer
	 * administration.
	 * 
	 * @return The date and time (as Calendar).
	 */
	public Calendar getTime() {
		return adminTime;
	}
}
