package couk.nucmedone.massivecyril.shared.labtest;


/**
 * Class to hold count assay parameters such as interval, background, counts,
 * SD, etc.
 * 
 * @author neil@pukka-j.com
 * 
 */
public class CorrectedCounts {
	
	private Counts background;
	private Counts sample;
	private DoublePlus counts, cps;
	
	public CorrectedCounts() {
		this(Double.NaN, Double.NaN, Double.NaN);
	}
	
	public static void main(String[] args) {
		double bkg = 100;
		double cnts = 50000;
		double time = 1200;
		
		CorrectedCounts c = new CorrectedCounts(cnts, bkg, time);
		double expected = 0.186525244;
		double actual = c.cps().SD();
		System.out.println(actual + " " + expected);
	}
	
	public CorrectedCounts(Counts cnts, Counts bkg) {
		background = bkg;
		sample = cnts;		
		
		// Correct for background and normalise
		cps = sample.getCPS();
		cps = cps.minus(background.getCPS());
		//cps = sample.getCPS().minus(background.getCPS());
		counts = cps.times(sample.getInterval());		
	}
	
	public CorrectedCounts(double counts, double bkg, double interval) {
		this(new Counts(counts, interval), new Counts(bkg, interval));
	}
	
	public CorrectedCounts(double cnt, double cntTime, double bkg, double bkgTime) {
		this(new Counts(cnt, cntTime), new Counts(bkg, bkgTime));
	}
	
	/**
	 * Get the background corrected counts for a sample.
	 * @return The counts.
	 */
	public DoublePlus counts() {
		return counts;
	}
	
	/**
	 * Get the background-corrected normalised counts for a sample.
	 * @return Counts per second.
	 */
	public DoublePlus cps() {
		return cps;
	}

}
