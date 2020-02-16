/*
 * 20554 ADVANCED JAVA
 * MMN12 - QUESTION 2
 * Ronen Lapushner, Dec. 2019
 */

/**
 * The PolynomItem class represents a single element in the Polynom.
 */
public class PolynomItem {
	// Constants
	private final int POLYNOMITEM_OP_PLUS = 1;
	private final int POLYNOMITEM_OP_MINUS = 2;

	// Properties
	private double value;
	private int power;

	// Constants
	private static final char VARIABLE = 'x';

	/**
	 * Constructs a new PolynomItem from a value and a power.
	 */
	public PolynomItem(double value, int power) {
		this.value = value;
		this.power = power;
	}

	/**
	 * Returns the derivative of the current item.
	 */
	public PolynomItem derivative() {
		// Edge case: power is 0. If so, the derivative is 0.
		if (this.power == 0) {
			return new PolynomItem(0, 0);
		}

		// derivative = a*x^i => i*a * x^(i-1)
		return new PolynomItem(this.value * this.power, this.power - 1);
	}

	/**
	 * Performs a certain operation on this and some other PolynomItem, returning
	 * the result of the operation. Operations possible: Addition -
	 * POLYNOMITEM_OP_PLUS - Returns the addition of the items Minus -
	 * POLYNOMITEM_OP_MINUS - Returns the subtraction of the items
	 */
	private PolynomItem performOp(PolynomItem other, int op) 
		throws IllegalArgumentException {
		// Cannot add items with different powers (since this should return the
		// more complex Polynom object).
		if (this.power != other.getPower())
			throw new IllegalArgumentException("Attempted to perform operations on PolynomItems of different powers!");
		
		// Perform the operation
		if (op == POLYNOMITEM_OP_MINUS)
			return new PolynomItem(this.value - other.getValue(), this.power);
		else if (op == POLYNOMITEM_OP_PLUS)
			return new PolynomItem(this.value + other.getValue(), this.power);
		else
			throw new IllegalArgumentException("Illegal operation");
	}

	/**
	 * Adds this item with a different item OF THE SAME POWER, returning a new item.
	 */
	public PolynomItem plus(PolynomItem other) throws IllegalArgumentException {
		return performOp(other, POLYNOMITEM_OP_PLUS);
	}

	/**
	 * Subtracts this item from the other item (BOTH OF THE SAME POWER), returning a
	 * new item.
	 */
	public PolynomItem minus(PolynomItem other) throws IllegalArgumentException {
		return performOp(other, POLYNOMITEM_OP_MINUS);
	}

	/** Sets the value */
	public void setValue(double value) {
		this.value = value;
	}

	/** Sets the power */
	public void setPower(int power) {
		this.power = power;
	}

	/** Returns the value of this Polynom */
	public double getValue() {
		return value;
	}

	/** Returns the power of this Polynom */
	public int getPower() {
		return power;
	}

	/**
	 * Compares two PolynomItems. Returns false if the powers and/or values are not
	 * equal, Other is null, or Other is not a PolynomItem. Otherwise returns true.
	 */
	@Override
	public boolean equals(Object other) {
		// Check if the correct object type was provided.
		if (other == null || !(other instanceof PolynomItem))
			return false;

		PolynomItem otherItem = (PolynomItem) other;

		// The PolynomItems are equal only if they have the same value and the same
		// power.
		return otherItem.getValue() == this.value && otherItem.getPower() == this.power;
	}
	
	/**
	 * Returns this PolynomItem as a string in the following format:
	 * "[value]x^[power]
	 *
	 * If the power is zero, the following format is used: [value]
	 *
	 * If the value is 1 and the power is NOT ZERO, the following format is used:
	 * x^[power]
	 *
	 * If the value is 0, regardless of power, the string returned is "0".
	 * 
	 * If "formatted" is true, it is assumed that the PolynomItem is printed
	 * as part of a Polynom - the value sign will therefore be printed
	 * in a space.
	 */
	public String toString(boolean formatted) {
		String strValue = "";

		// Check if the value is 0 - if it is, it's trivial.
		if (this.value == 0) {
			return "0";
		}
		
		// If formatted - put a sign regardless.
		if (formatted) {
			if (this.value <= -1)
				strValue = " - ";
			else
				strValue = " + ";
		}
		else {
			// Put the sign close to the value, since we're not formatting.
			if (this.value <= -1)
				strValue += " -";
		}
		
		// Display value (if needed)
		if (this.value > 1 || this.value < -1 || this.power == 0)
			strValue += Double.toString(Math.abs(this.value));

		// Check the power, and append it to the string accordingly, if needed.
		if (this.power != 0) {
			strValue += VARIABLE;

			// If power is not one, append it to the string.
			if (this.power > 1) {
				strValue += "^" + this.power;
			}
		}

		return strValue;
	}

	
	@Override
	public String toString() {
		return this.toString(false);
	}
}
