package couk.nucmedone.massivecyril.shared.labtest;

import java.util.Calendar;

import couk.nucmedone.common.base.DoublePlus;

/**
 * A class to hold parameters for counting tubes used in counting tests such as
 * EDTA GFR and RCM.
 * 
 * @author neil@pukka-j.com.
 * 
 */
public abstract class AbstractCountingTube {
	
	protected DoublePlus weightFull;
	protected DoublePlus weightEmpty;
	protected DoublePlus countingVolume;
	protected DoublePlus countRate;
	protected DoublePlus counterSensitivity;
	
	protected Calendar countDateTime;	

	// TODO: Enable proper method of setting weight error
	public static final double WEIGHT_ERROR = 0.0005; //g
	
	//TODO: Enable proper method for setting tube volume error
	public static final double VOLUME_ERROR = 0.005; //ml

	protected CorrectedCounts counts;

	// TODO: Enable proper method of setting minimum volume
	protected double minimumVolume = 0.5; // ml
	
	protected String name = "";

	public AbstractCountingTube(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

	/**
	 * Set the number of counts, interval and background information collected
	 * for this sample.
	 * 
	 * @param counts
	 *            The total number of counts.
	 */
	public void setCounts(CorrectedCounts counts) {
		this.counts = counts;
	}

	/**
	 * Get the total number of counts for this sample.
	 * 
	 * @return the number of counts.
	 */
	public DoublePlus getCounts() {
		return counts.counts();
	}

	/**
	 * Get the corrected rate of acquired counts in counts per second (cps).
	 * 
	 * @return The counts per second.
	 */
	public DoublePlus getCPS() {
		return counts.cps();
	}

	/**
	 * Set the weight (in grammes) of the counting tube and a full sample for
	 * assay.
	 * 
	 * @param weight
	 *            The sample (and tube) weight in grammes.
	 */
	public void setFullWeight(DoublePlus weight) {
		weightFull = weight;
	}

	/**
	 * Get the weight (in grammes) of the counting tube and a full sample for
	 * assay.
	 * 
	 * @return The sample (and tube) weight in grammes.
	 */
	public DoublePlus getFullWeight() {
		return weightFull;
	}

	/**
	 * Set the weight (in grammes) of the empty counting tube to be used for
	 * assay.
	 * 
	 * @param weight
	 *            The empty tube weight in grammes.
	 */
	public void setEmptyWeight(DoublePlus weight) {
		weightEmpty = weight;
	}

	/**
	 * Get the weight (in grammes) of the empty counting tube to be used for
	 * assay.
	 * 
	 * @return The empty tube weight in grammes.
	 */
	public DoublePlus getEmptyWeight() {
		return weightEmpty;
	}

	/**
	 * Set the final volume used for counting the sample. The volume, for
	 * instance, could be a nominal volume always used or an exact volume
	 * measured. An exact volume implies a volume correction to be applied to
	 * the counting results.
	 * 
	 * @param volume
	 *            The volume (in millilitres) used for counting.
	 */
	public void setCountingVolume(DoublePlus volume) {
		countingVolume = volume;
	}

	/**
	 * Get the final volume used for counting the sample. The volume, for
	 * instance, might be a nominal volume, or an exact volume measured. An
	 * exact volume implies a volume correction is to be applied to the counting
	 * results.
	 * 
	 * @return The volume (in millilitres) used for counting.
	 */
	public DoublePlus getCountingVolume() {
		return countingVolume;
	}

	public DoublePlus getTubeContents() {
		return weightFull.minus(weightEmpty);
	}
	
	public boolean isGoodVolume() {
		return (minimumVolume < getTubeContents().value());
	}
	
	/**
	 * Set the date and time of the sample assay.
	 * @param dateTime the date and time of sample assay.
	 */
	public void setCountDate(Calendar dateTime) {
		this.countDateTime = dateTime;
	}
	
	/**
	 * get a calendar object representing the date and time of sample assay.
	 * @return The date and time (as Calendar).
	 */
	public Calendar getCountDate() {
		return countDateTime;
	}
	
	/**
	 * Get the time between input time and assay date/time of the sample.
	 * 
	 * @param time
	 *            Calendar object as time to calculate difference from.
	 * @return The time difference in seconds.
	 */
	public long timeFromAssay(Calendar time) {
		long timeFromAssay = time.getTimeInMillis() - countDateTime.getTimeInMillis();
		timeFromAssay *= 1000;
		return timeFromAssay;
	}
	
	public DoublePlus cpsPerMl() {
		return this.getCPS().div(getTubeContents());
	}
	
	public DoublePlus getConcentration() {
		return cpsPerMl().div(counterSensitivity);
	}	
	
	public void setCounterSensitivity(DoublePlus counterSensitivity) {
		this.counterSensitivity = counterSensitivity;
		
	}

}
