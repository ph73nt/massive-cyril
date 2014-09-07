package couk.nucmedone.massivecyril.shared.labtest;

import couk.nucmedone.common.base.DoublePlus;

public class Corrections {

	private Corrections(){
		// Not for constructing
	}

	public static DoublePlus brochnerMortensen(DoublePlus dp) {

		// 1.0004*dp - 0.00146*(dp^2)
		DoublePlus left = dp.times(1.0004);

		DoublePlus right = dp.times(dp);
		right = right.times(0.00146);

		DoublePlus result = left.minus(right);

		return result;

	}

}
