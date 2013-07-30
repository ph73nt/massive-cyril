package couk.nucmedone.massivecyril.shared.labtest;

public class Decay {

	/**
	 * Calculate the multiplying factor of a decaying radioactove sample, given
	 * the number of seconds after the sampling has taken place.
	 * 
	 * @param nuclide The nuclide being assayed.
	 * @param seconds The time post assay (s).
	 * @return The scaling factor.
	 */
	public static double postFactor(Nuclides nuclide, double seconds) {
		double decayConst = nuclide.halfLife() / seconds;
		return Math.exp(-1 * decayConst * seconds);
	}
	
	/**
	 * Calculate the multiplying factor of a decaying radioactive sample, given
	 * the number of seconds before the sampling has taken place.
	 * 
	 * @param nuclide The nuclide being assayed.
	 * @param seconds The time pre-assay (s).
	 * @return The scaling factor.
	 */
	public static double preFactor(Nuclides nuclide, double seconds) {
		double decayConst = Math.log(2) / nuclide.halfLife();
		return Math.exp(decayConst * seconds);
	}


}
