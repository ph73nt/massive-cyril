/**
 * @author Neil J Thomson (njt@fishlegs.co.uk)
 *
 * Copyright (C) 2013 Neil J Thomson
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or (at
 * your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 */
package couk.nucmedone.massivecyril.shared.labtest;

/**
 * Class to represent double +/- standard deviation.
 */
public class DoublePlus {

	private double value = Double.NaN;
	private double variance = Double.NaN;

	public DoublePlus() {
	}

	public DoublePlus(double value, double SD) {
		this.value = value;
		variance = Math.pow(SD, 2);
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public double value() {
		return value;
	}

	public void setSD(double SD) {
		variance = Math.pow(SD, 2);
	}

	public double SD() {
		return Math.sqrt(variance);
	}

	/**
	 * Calculate the value and standard deviation of this variable multiplied by
	 * the argumnet, which should be a constant with no (or negligible) error.
	 * 
	 * @param constant
	 *            A multiplying constant.
	 * @return The DoublePlus representing result +/- error
	 */
	public DoublePlus times(double constant) {
		DoublePlus dp = new DoublePlus();
		dp.value = value * constant;
		dp.variance = Errors.var_aA(constant, Math.sqrt(variance));
		return dp;
	}

	/**
	 * Calculate the value and standard deviation of this variable multiplied by
	 * an input argument and calculate the resulting standard deviation, given
	 * the standard deviation of the input multiplier.
	 * 
	 * @param x
	 *            The number to multiply this value by.
	 * @param x_SD
	 *            The standard deviation of the argument value.
	 * @return The DoublePlus representing result +/- error
	 */
	public DoublePlus times(double x, double x_SD) {
		DoublePlus dp = new DoublePlus();
		dp.value = value * x;
		dp.variance = Errors.var_AB(value, Math.sqrt(variance), x, x_SD, dp.value);
		return dp;
	}
	
	public DoublePlus times(DoublePlus dp) {
		return times(dp.value(), dp.SD());
	}

	/**
	 * Calculate the value and standard deviation of this variable divided by an
	 * input argument and calculate the resulting standard deviation, given the
	 * standard deviation of the input multiplier.
	 * 
	 * @param x
	 *            The number to divide this value by.
	 * @param x_SD
	 *            The standard deviation of the argument value.
	 * @return The DoublePlus representing result +/- error
	 */
	public DoublePlus div(double x, double x_SD) {
		DoublePlus dp = new DoublePlus();
		dp.value = value / x;
		dp.variance = Errors.var_AB(value, Math.sqrt(variance), x, x_SD, dp.value);
		return dp;
	}
	
	public DoublePlus div(DoublePlus dp) {
		return div(dp.value(), dp.SD());
	}
	
	public DoublePlus div(int constant) {
		return div((double) constant);
	}
	
	public DoublePlus div(double constant) {
		DoublePlus dp = new DoublePlus();
		dp.value = value / constant;
		dp.variance = Errors.var_aA(1/constant, Math.sqrt(variance));
		return dp;
	}


	/**
	 * Calculate the value and standard deviation of this variable added to an
	 * input argument and calculate the resulting standard deviation, given the
	 * standard deviation of the input value.
	 * 
	 * @param x
	 *            The number to add to this value.
	 * @param x_SD
	 *            The standard deviation of the argument value.
	 * @return The DoublePlus representing result +/- error
	 */
	public DoublePlus plus(double x, double x_SD) {
		DoublePlus dp = new DoublePlus();
		dp.value = value + x;
		dp.variance = Errors.var_A_plusMinus_B(Math.sqrt(variance), x_SD);
		return dp;
	}
	
	public DoublePlus plus(DoublePlus dp) {
		return plus(dp.value(), dp.SD());
	}
	
	public DoublePlus plus(double constant) {
		return plus(constant, Double.MIN_VALUE);
	}

	/**
	 * Calculate the value and standard deviation of this an input argument
	 * subtracted from this value and calculate the resulting standard
	 * deviation, given the standard deviation of the input value.
	 * 
	 * @param x
	 *            The number to subtract from this value.
	 * @param x_SD
	 *            The standard deviation of the argument value.
	 * @return The DoublePlus representing result +/- error
	 */
	public DoublePlus minus(double x, double x_SD) {
		DoublePlus dp = new DoublePlus();
		dp.value = value - x;
		dp.variance = Errors.var_A_plusMinus_B(Math.sqrt(variance), x_SD);
		return dp;
	}
	
	public DoublePlus minus(DoublePlus dp) {
		return minus(dp.value(), dp.SD());
	}
	
	public DoublePlus minus(double constant) {
		return minus(constant, Double.MIN_VALUE);
	}

	
	public static DoublePlus ln(DoublePlus dp) {
		DoublePlus dplus = new DoublePlus();
		dplus.value = Math.log(dp.value);
		dplus.variance = Errors.ln_A(dp.value, dp.SD());
		return dplus;
	}
	
	public static DoublePlus exp(DoublePlus dp) {
		DoublePlus dplus = new DoublePlus();
		dplus.value = Math.exp(dp.value);
		dplus.variance = Errors.exp_A(dp.value, dp.SD());
		return dplus;		
	}
	
	public static DoublePlus sqrt(DoublePlus dp) {
		DoublePlus dplus = new DoublePlus();
		dplus.value = Math.sqrt(dp.value);
		dplus.variance = Errors.var_sqrt_A(dp.value, dp.SD());
		return dplus;
	}

}
