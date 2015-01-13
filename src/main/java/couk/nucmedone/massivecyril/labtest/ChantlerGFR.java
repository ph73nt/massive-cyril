package couk.nucmedone.massivecyril.labtest;

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.massivecyril.labtest.exceptions.TimeTooShortFromAdminException;

public class ChantlerGFR {

	public static final double MIX_PHASE_CORRECTION = 0.87;

	private ChantlerGFR() {
	}

	public static DoublePlus gfr(MultiSampleGFR msgfr) throws TimeTooShortFromAdminException {
		return msgfr.getClearance().times(MIX_PHASE_CORRECTION);
	}

}
