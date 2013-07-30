package couk.nucmedone.massivecyril.shared.labtest;

import couk.nucmedone.massivecyril.shared.labtest.exceptions.TimeTooShortFromAdminException;

public class TwoPointLogFit extends AbstractLogCurveFit {

	public TwoPointLogFit(SampleTube[] samples) throws TimeTooShortFromAdminException {
		super(samples);
	}
	
	protected void fitLine() throws TimeTooShortFromAdminException {
		// Calculate gradient.. what did teacher say? Difference in y with respec' to x
		DoublePlus numerator = logConcs[1].minus(logConcs[0]);
		double denominator = samples[1].secondsFromAdmin() - samples[0].secondsFromAdmin();
		grad = numerator.div(denominator);
		
		// Y-intercept.. pretty trivial stuff
		root = logConcs[0].minus(grad.times(samples[0].secondsFromAdmin()));
	}
	
	public double Correlation() {
		return 1;
	}
	
}
