package couk.nucmedone.massivecyril.shared.labtest;

/**
 * Holds the normal GFR for the patients given age.
 * 
 * @author neil
 *
 */
public class NormalGFRRange {
	
	public DoublePlus gfr;
	private int age;
	
	/**
	 * Makes a normal range object for a given patient age.
	 * 
	 * @param age The age of the patient on the date of the test.
	 */
	public NormalGFRRange(int age) {
		
		this.age = age;
		
		gfr = new DoublePlus(mean(), sd());
		
	}
	
	/**
	 * Calculate the standard deviation of the normal range for the given age.
	 * 
	 * @return The standard deviation of the normal range.
	 */
	private double sd() {

		if (age <= 17) {
			return 17;
		} else if (age <= 20) {
			// Perform linear interpolation for SD normals at age 17 (17) and age 20 (12.5)
			return 17 + (4.5 * (17 - age) / 3); 
		} else {
			return 12.5;
		}
		
	}

	/**
	 * Calculate the mean value of the normal range for this age.
	 * 
	 * @return The mean of the normal range.
	 */
	private double mean() {
		
		if (age <= 17) {
			return 107 - (62 * Math.pow(0.985, (100 * age)));
		} else if (age <= 20) {
			// Perform linear interpolation for mean normals at age 17 and age 20
			return 107 + (2 * (age - 17) / 3); 
		} else if (age <= 50) {
			return 116 - (0.35 * age);
		} else {
			return 148 - age;
		}
		
	}

}
