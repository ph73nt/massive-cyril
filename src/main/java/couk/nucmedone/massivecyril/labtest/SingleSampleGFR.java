package couk.nucmedone.massivecyril.labtest;

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.massivecyril.labtest.exceptions.TimeTooShortFromAdminException;

public abstract class SingleSampleGFR extends GFR {

	protected int sampleIndex = 0;

	public SingleSampleGFR() {
		super();
	}

	/**
	 * For GFRs where more than one sample has been provided, the single sample
	 * GFR can be calculated for any or all samples. The default index is zero.
	 * Use this method to set an alternative sample.
	 *
	 * @param index
	 */
	public void setSampleIndex(int index) {
		sampleIndex = index;
	}

	public int getSampleIndex(){
		return sampleIndex;
	}

}