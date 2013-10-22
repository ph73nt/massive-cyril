package couk.nucmedone.massivecyril.shared.labtest;

import couk.nucmedone.common.base.DoublePlus;


public class StandardsTube extends AbstractCountingTube {
	
	private DoublePlus tracerVolume;
	
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
		return getCPS().div(tracerVolume);
	}

}
