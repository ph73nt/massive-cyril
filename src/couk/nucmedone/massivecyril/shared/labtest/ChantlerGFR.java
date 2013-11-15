package couk.nucmedone.massivecyril.shared.labtest;

import couk.nucmedone.common.base.DoublePlus;

public class ChantlerGFR {

	public static final double MIX_PHASE_CORRECTION = 0.87;
	
	private ChantlerGFR() {
	}
	
	public static DoublePlus gfr(MultiSampleGFR msgfr) {
		return msgfr.getClearance().times(MIX_PHASE_CORRECTION);
	}

}
