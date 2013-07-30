package couk.nucmedone.massivecyril.shared.labtest;

/**
 * Class to hold (largely static) method for error propogation.
 * @author neil
 *
 */
public class Errors {

	/**
	 * Empty constructor - do not construct
	 */
	private Errors(){}
	
	/**
	 * Calculate the standard deviation in the product of constant a (negligible
	 * error) and variable A.
	 * 
	 * @param a
	 *            Constant a.
	 * @param sd_A
	 *            The standard deviation in variable A.
	 * @return The standard deviation of the function f = aA.
	 */
	public static double aA(double a, double sd_A) {
		return a * sd_A;
	}

	/**
	 * Calculate the variance in the product of constant a (negligible error)
	 * and variable A.
	 * 
	 * @param a
	 *            Constant a.
	 * @param sd_A
	 *            The standard deviation in variable A.
	 * @return The variance of the function f = aA.
	 */
	public static double var_aA(double a, double sd_A) {
		return a*a * sd_A*sd_A;
	}
	
	/**
	 * Calculate the variance in the function f where f = aA +/- bB, from the
	 * constants a and b and the standard deviations of variables A and B. There
	 * must be no co-variance between A and B.
	 * 
	 * @param a
	 *            Constant a.
	 * @param sd_A
	 *            Standard deviation of variable A.
	 * @param b
	 *            Constant b.
	 * @param sd_B
	 *            Standard deviation of variable B.
	 * @return The variance in the function f = aA +/- bB.
	 */
	public static double var_aA_plusMinus_bB(double a, double sd_A, double b, double sd_B) {
		return var_aA(a, sd_A) + var_aA(b, sd_B);
	}
	
	/**
	 * Calculate the variance in the function f where f = A +/- B,
	 * from the standard deviations of variables A and B. There must be no
	 * co-variance between A and B.
	 * 
	 * @param sd_A
	 * @param sd_B
	 * @return The variance in f = A +/- B  
	 */
	public static double var_A_plusMinus_B(double sd_A, double sd_B) {
		return var_aA_plusMinus_bB(1, sd_A, 1, sd_B);
	}
	
	/**
	 * Calculate the standard deviation in the function f where f = A +/- B,
	 * from the standard deviations of variables A and B. There must be no
	 * co-variance between A and B.
	 * 
	 * @param sd_A
	 * @param sd_B
	 * @return The standard deviation in f = A +/- B  
	 */
	public static double A_plusMinus_B(double sd_A, double sd_B) {
		return aA_plusMinus_bB(1, sd_A, 1, sd_B);
	}

	/**
	 * Calculate the standard deviation in the function f where f = aA +/- bB,
	 * from the constants a and b and the standard deviations of variables A and
	 * B. There must be no co-variance between A and B.
	 * 
	 * @param a
	 *            Constant a.
	 * @param sd_A
	 *            Standard deviation of variable A.
	 * @param b
	 *            Constant b.
	 * @param sd_B
	 *            Standard deviation of variable B.
	 * @return The standard deviation in the function f = aA +/- bB.
	 */
	public static double aA_plusMinus_bB(double a, double sd_A, double b, double sd_B) {
		return Math.sqrt(var_aA_plusMinus_bB(a, sd_A, b, sd_B));
	}
	
	/**
	 * Calculate the standard deviation in the function f = AB or f = A/B, when
	 * there is no correlation between A and B.
	 * 
	 * @param A The value of A.
	 * @param sd_A The standard deviation of A.
	 * @param B The value of B.
	 * @param sd_B The standard deviation of B.
	 * @param f The value of the function f = AB (or f = A/B).
	 * @return The standard deviation.
	 */
	public static double AB(double A, double sd_A, double B, double sd_B, double f) {
		return Math.sqrt(var_AB(A, sd_A, B, sd_B, f));
	}
	
	public static double var_AB(double A, double sd_A, double B, double sd_B, double f){
		double errA = Math.pow(sd_A/A, 2);
		double errB = Math.pow(sd_B/B, 2);
		return Math.pow(f, 2) * (errA + errB);
	}
	
	/**
	 * Get the standard deviation in the calculation on ln(A +/- sd_A)
	 * @param A The value being "logged"
	 * @param sd_A The standard deviation in A 
	 * @return 
	 */
	public static double ln_A(double A, double sd_A) {
		// TODO: unit test
		return sd_A/A;
	}
	
	/**
	 * get the standard deviation in the calculation of exp(bA).
	 * 
	 * @param A Variable A
	 * @param sd_A Standard deviation of variable A
	 * @param b Constant B
	 * @return Standard deviation
	 */
	public static double exp_bA(double A, double sd_A, double b) {
		// TODO: unit test
		return b * sd_A * Math.exp(b * A);
	}
	
	/**
	 * Get the standard deviation in the calculation of exp(A)
	 * @param A Variable A
	 * @param sd_A Standard deviation of variable A
	 * @return Standard deviation
	 */
	public static double exp_A(double A, double sd_A) {
		return exp_bA(A, sd_A, 1);
	}

	/**
	 * Calculate the variance in the square root of A +/- sd_A.
	 * @param A The value being rooted.
	 * @param sd_A The standard deviation in A.
	 * @return Variance of (A+/- sd_A)^0.5
	 */
	public static double var_sqrt_A(double A, double sd_A) {
		return 0.5 * sd_A / Math.sqrt(A);
	}
	
}
