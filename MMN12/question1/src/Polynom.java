/*
 * 20554 ADVANCED JAVA
 * MMN12 - QUESTION 2
 * Ronen Lapushner, Dec. 2019
 */

import java.util.ArrayList;

/**
 * This class represents a new Polynom.
 */
public class Polynom {
	// Constants
	private final int POLYNOM_OP_PLUS = 1;
	private final int POLYNOM_OP_MINUS = 2;

	// Properties
	private ArrayList<PolynomItem> items;

	/** Constructs a new Polynom from an array of values and an array of powers. */
	public Polynom(double[] values, int[] powers) throws Exception {
		// Check if array sizes are equal
		if (values.length != powers.length)
			throw new Exception("Values and Powers arrays are not equal in length");

		// Initialize the items array
		items = new ArrayList<PolynomItem>();

		// Iterate through the arrays to create the items in the Polynom
		for (int i = 0; i < values.length; i++) {
			// Create a new polynom item from the information
			PolynomItem item = new PolynomItem(values[i], powers[i]);

			// Edge case: If value is 0, the item has no point.
			if (item.getValue() == 0)
				continue;

			// Add the item
			addItem(item);
		}

		// Sort the items array based on the powers, as requested.
		this.sortItemsByPowers();
	}

	/**
	 * A private constructor that is used internally to quickly create Polynoms from
	 * an already-existing array of polynom items.
	 */
	private Polynom(ArrayList<PolynomItem> polyItems) {
		items = new ArrayList<>();

		// Add the items, taking care of duplicates
		for (PolynomItem item: polyItems)
			this.addItem(item);

		// Sorting might not be needed (depending on what function called this),
		// but we do it anyways.
		this.sortItemsByPowers();
	}

	/**
	 * Sorts the items array based on the power - biggest powers first (selection
	 * sort).
	 */
	private void sortItemsByPowers() {
		for (int i = 0; i < items.size() - 1; i++) {
			int maxPowerItemIndex = i;

			// Go through the rest of the array
			for (int ii = i + 1; ii < items.size(); ii++) {
				if (items.get(ii).getPower() > items.get(maxPowerItemIndex).getPower())
					maxPowerItemIndex = ii;
			}

			// Swap the two items
			PolynomItem temp = items.get(i);
			items.set(i, items.get(maxPowerItemIndex));
			items.set(maxPowerItemIndex, temp);
		}
	}

	/** Find a PolynomItem by power, or return -1 if one does not exist. */
	private int findIndexByPower(int power) {
		// Lookup for the item
		for (int i = 0; i < items.size(); i++) {
			if (this.items.get(i).getPower() == power)
				return i;
		}

		// Not found
		return -1;
	}

	/** Adds an item to this Polynom, making sure to take care of items that already exist. */
	private void addItem(PolynomItem item) {
		// Edge case: trying to add a zero - no point in doing that.
		if (item.getValue() == 0)
			return;

		// Before adding the item to the array, we must make sure it doesn't
		// already exist (user might've input the same power twice).
		// If it does, we just add the two together (which returns a new item -
		// so we'll replace that one with the one we got).
		// NOTE: Minus is also covered, since we're adding positive
		// or negative values.
		int prevIndex = this.findIndexByPower(item.getPower());
		if (prevIndex != -1) {
			PolynomItem prevItem = items.get(prevIndex);

			// Calculate the new item
			PolynomItem resultItem = prevItem.plus(item);

			// If it's not zero, add the result instead of the previous item.
			// If it is, means that the previous item must be removed.
			if (resultItem.getValue() == 0)
				items.remove(prevIndex);
			else
				items.set(prevIndex, resultItem);
		} else {
			// Not found so add the item.
			items.add(item);
		}
	}

	/** Returns a PolynomItem based on an index. */
	public PolynomItem getItem(int index) throws IndexOutOfBoundsException {
		// Check if index is in bounds
		if (index < 0 || index > items.size())
			throw new IndexOutOfBoundsException("Item index must be between 0 and " + (items.size() - 1));

		return items.get(index);
	}

	/** Returns how many items there are in the polynom. */
	public int itemCount() {
		return this.items.size();
	}

	/** Returns a derivative of the current polynom */
	public Polynom derivative() {
		// For every item in this Polynom, we take its derivative
		// and put it in a new Polynom.
		ArrayList<PolynomItem> itemsRaw = new ArrayList<>();

		for (PolynomItem item : this.items) {
			// Create derivative item
			PolynomItem dItem = item.derivative();

			// Edge case: If value is 0, no point in adding the item.
			if (dItem.getValue() != 0)
				itemsRaw.add(dItem);
		}

		// Return the new polynom using the private constructor
		return new Polynom(itemsRaw);
	}

	/**
	 * Performs a certain operation on this and some other PolynomItem, returning
	 * the result of the operation. Operations possible: 
	 * Addition - POLYNOM_OP_PLUS - Returns the addition of the items 
	 * Minus - POLYNOM_OP_MINUS - Returns the subtraction of the items
	 */
	private Polynom performOp(Polynom other, int op) throws IllegalArgumentException {
		// Check if the other polynom is not null
		if (other == null)
			throw new IllegalArgumentException("other Polynom must not be null");

		// Check operation
		if (op != POLYNOM_OP_MINUS && op != POLYNOM_OP_PLUS)
			throw new IllegalArgumentException("Invalid Polynom operation");

		// Algorithm: For starters, dump all items from all polynoms into the Raw array.
		// We don't care about duplicates at this point; the constructors take care of them.
		ArrayList<PolynomItem> itemsRaw = new ArrayList<>();

		for (int i = 0; i < this.itemCount(); i++)
			itemsRaw.add(this.getItem(i));

		for (int ii = 0; ii < other.itemCount(); ii++) {
			PolynomItem originalItem = other.getItem(ii);

			// If OP is minus, add the items as a negative.
			if (op == POLYNOM_OP_MINUS) {
				PolynomItem minusItem = new PolynomItem(
						originalItem.getValue() * -1.0,
						originalItem.getPower()
				);

				itemsRaw.add(minusItem);
			}
			else
				itemsRaw.add(originalItem);
		}

		// Return the new polynom using the private constructor
		return new Polynom(itemsRaw);
	}

	/** Adds this Polynom with the provided Polynom, returning a new Polynom. */
	public Polynom plus(Polynom other) {
		return performOp(other, POLYNOM_OP_PLUS);
	}

	/** Subtracts the other Polynom from this Polynom, returning a new Polynom. */
	public Polynom minus(Polynom other) {
		return performOp(other, POLYNOM_OP_MINUS);
	}

	/**
	 * Compares two Polynoms to see if they are equal. Returns false if the items
	 * are different, otherPolynomObject is null, or otherPolynomObject is not a Polynom.
	 * Otherwise returns true.
	 * NOTE: All Polynoms are ordered by definition, so we check item by item.
	 */
	@Override
	public boolean equals(Object otherPolynomObject) {
		// Check if the object provided is indeed a Polynom.
		if (otherPolynomObject == null || !(otherPolynomObject instanceof Polynom))
			return false;

		// Cast the other object for easier use.
		Polynom other = (Polynom)otherPolynomObject;

		// Edge case: Polynoms do not have the same amount of items.
		// Instant false.
		if (this.itemCount() != other.itemCount())
			return false;

		// Now for every item, check if they have the same power and the same value.
		for (int i = 0; i < itemCount(); i++) {
			PolynomItem item1 = this.getItem(i);
			PolynomItem item2 = other.getItem(i);

			if (item1.getPower() != item2.getPower() || item1.getValue() != item2.getValue())
				return false;
		}

		// Passed everything
		return true;
	}

	/** Returns this Polynom as a String. */
	@Override
	public String toString() {
		String retString = "";

		// Edge case: empty polynoms simply print "0"
		if (this.items.size() == 0)
			return "0";

		// First item is printed as-is
		retString += this.items.get(0).toString();

		// The rest of the items are printed
		for (int i = 1; i < this.items.size(); i++) {
			// Append the formatted version of the PolynomItem (see PolynomItem::toString).
			PolynomItem item = this.items.get(i);
			retString += item.toString(true);
		}

		return retString;
	}
}
