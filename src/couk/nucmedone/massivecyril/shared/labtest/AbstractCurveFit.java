package com.pukkaj.labtest;

import com.pukkaj.labtest.exceptions.LowSampleNumberException;
import com.pukkaj.labtest.exceptions.TimeTooShortFromAdminException;

/**
 * Produce an exponential curve fit against labtest samples.
 * 
 * @author neil
 * 
 */
public abstract class AbstractCurveFit {

	protected SampleTube[] samples;
	
	protected DoublePlus grad;
	protected DoublePlus root;
	protected double correlation;
	
	// TODO: let admin user set this
	public double systematicError = 0.005; // 0.5% as a guess

	public AbstractCurveFit(SampleTube[] samples) throws TimeTooShortFromAdminException {

		this.samples = samples;
		init();

	}
	
	protected void init() throws TimeTooShortFromAdminException {
		fitLine();
	}
	
	abstract protected void fitLine() throws TimeTooShortFromAdminException;
	
	private DoublePlus gradient() {
		return grad;
	}
	
	private DoublePlus root() {
		return root;
	}
	
	public double correleation() {
		return correlation;
	}
}
