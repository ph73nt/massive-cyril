package couk.nucmedone.massivecyril.shared.labtest;

/**
 * Class to hold counting parameters.
 * 
 * @author neil@pukka-j.com
 *
 */
public class Counts {
	
	private DoublePlus counts;
	private double interval;
	private DoublePlus cps;
	
	public Counts(double counts, double interval){
		this.counts = new DoublePlus(counts, Math.sqrt(counts));
		this.interval = interval;
		cps = this.counts.div(interval);
	}
	
	public DoublePlus getCounts() {
		return counts;
	}
	
	public DoublePlus getCPS() {
		return cps;
	}
	
	public double getInterval() {
		return interval;
	}

}
