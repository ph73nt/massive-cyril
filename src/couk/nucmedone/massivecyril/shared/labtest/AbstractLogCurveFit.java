package com.pukkaj.labtest;

import com.pukkaj.labtest.exceptions.TimeTooShortFromAdminException;

public abstract class AbstractLogCurveFit extends AbstractCurveFit {
	
	protected DoublePlus[] logConcs;

	public AbstractLogCurveFit(SampleTube[] samples) throws TimeTooShortFromAdminException {
		super(samples);
	}
	
	protected void init() throws TimeTooShortFromAdminException {
		// Initialise logConcs
		logConcs = new DoublePlus[samples.length];

		// Get the natural log of concentrations
		logConcentrations();
		
		fitLine();
	}

	private void logConcentrations() {

		for (int i = 0; i < logConcs.length; i++) {
			logConcs[i] = DoublePlus.ln(samples[i].getConcentration());
		}

	}

}