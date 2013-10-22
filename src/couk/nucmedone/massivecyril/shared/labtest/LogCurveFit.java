package couk.nucmedone.massivecyril.shared.labtest;

import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.massivecyril.shared.labtest.exceptions.TimeTooShortFromAdminException;

public class LogCurveFit extends AbstractLogCurveFit {

	public LogCurveFit(SampleTube[] samples) throws TimeTooShortFromAdminException {
		super(samples);
	}

	protected void fitLine() throws TimeTooShortFromAdminException {
		// Fit_Line(timefrominj(), LogConc(), labtest.NoSamples, DLogConc(),
		// UseErrors, Gradient, Intercept, DGradient, DIntercept, Covariance,
		// Chisq, Correlation, DebugFlag)
		
		// timefrominj() >> x
		// LogConc >> Y
		
		DoublePlus wtSum = new DoublePlus();
		DoublePlus timeSum = new DoublePlus();
		DoublePlus timeSqrSum = new DoublePlus();
		DoublePlus concSum = new DoublePlus();
		DoublePlus concSqrSum = new DoublePlus();
		DoublePlus timeConcSum = new DoublePlus();
		
		for (int i=0; i < samples.length; i++) {
			
			// Weight of errors in calculations
			DoublePlus wt = logConcs[i].times(logConcs[i]);
			
			// Weight the sum of weights (!) on this value
			wtSum = wtSum.plus(wtSum);			
            timeSum = timeSum.plus(wt.times(samples[i].secondsFromAdmin()));
            timeSqrSum = timeSqrSum.plus(wt.times(Math.pow(samples[i].secondsFromAdmin(), 2)));
            concSum = concSum.plus(logConcs[i].times(wt));
            concSqrSum = concSqrSum.plus(wt.times(logConcs[i]).times(logConcs[i]));            
            timeConcSum = timeConcSum.plus(logConcs[i].times(samples[i].secondsFromAdmin()).times(wt));
			
		}
		
		DoublePlus d = wtSum.times(timeSqrSum);
		d = d.minus(timeSum.times(timeSum));
		
		root = concSum.times(timeSqrSum).minus(timeSum.times(timeConcSum));
		root = root.div(d);
		
		grad = wtSum.times(timeConcSum).minus(timeSum.times(concSum));
		grad = grad.div(d);
		
		correlation = wtSum.value() * timeConcSum.value();
		correlation -= timeSum.value() * concSum.value();
		correlation /= Math.sqrt(d.value());
		correlation /= Math.sqrt(wtSum.value()*concSqrSum.value() - Math.pow(concSum.value(), 2));
	}

}
