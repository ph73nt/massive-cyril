package com.pukkaj.labtest;

import java.util.Calendar;

import com.pukkaj.labtest.exceptions.TimeTooShortFromAdminException;

public class SampleTube extends AbstractCountingTube {

	private double density = Double.NaN;
	private Calendar adminTime;
	public double minimumSampleInterval = 60 * 60; // 1h in seconds!
	
	public SampleTube(String name) {
		super(name);
	}
	
	// TODO: should this be seconds from admin to sample - or both?
	public double secondsFromAdmin() throws TimeTooShortFromAdminException {
		double secondsSinceAdmin = countDateTime.getTimeInMillis();
		secondsSinceAdmin -= adminTime.getTimeInMillis();
		secondsSinceAdmin *= 1000;

		// Warn if the interval is less than some threshold... which will also
		// be thrown for silly errors like a negative time.
		if (secondsSinceAdmin < minimumSampleInterval) {
			String message = "Sample " + name
					+ " has short time between administration and counting ("
					+ secondsSinceAdmin + "s)"; 
			throw new TimeTooShortFromAdminException(message);
		}
		
		return secondsSinceAdmin;
	}
	
	public void setAdminTime(Calendar adminTime) {
		this.adminTime = adminTime;
	}

	public void setDensity(double density) {
		this.density = density;
	}
	
	public double getDensity() {
		return density;
	}
	
	public DoublePlus getTubeContents() {
		DoublePlus contents = weightFull.minus(weightEmpty);
		contents = contents.times(density);
		return contents;
	}

}
