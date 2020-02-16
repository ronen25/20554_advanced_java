/*
 * 20554 ADVANCED JAVA
 * MMN12 - QUESTION 2
 * Ronen Lapushner, Dec. 2019
 */

/** This class represents a worker that has his salary calculated based on how many things he sells. */
public class PieceWorker extends Employee {
	// Properties
	private int itemsSold;
	private double payPerItem;

	// Constructor
	public PieceWorker(String firstName, String lastName, String socialSecurityNumber, DateOfBirth dob, int itemsSold,
			double payPerItem) throws IllegalArgumentException {
		super(firstName, lastName, socialSecurityNumber, dob);

		// Use the setters for bound checks
		this.setItemsSold(itemsSold);
		this.setPayPerItem(payPerItem);
	}

	/** Returns the amount of items sold. */
	public int getItemsSold() {
		return itemsSold;
	}

	/** Sets the amount of items sold. */
	public void setItemsSold(int itemsSold) throws IllegalArgumentException {
		// Check argument
		if (itemsSold < 0)
			throw new IllegalArgumentException("Items sold cannot be less than 0");

		this.itemsSold = itemsSold;
	}

	/** Gets the amount of payment per item. */
	public double getPayPerItem() {
		return payPerItem;
	}

	/** Sets the amount of payment per item. */
	public void setPayPerItem(double payPerItem) throws IllegalArgumentException {
		// Check argument
		if (payPerItem < 0.0)
			throw new IllegalArgumentException("Pay per item cannot be less than 0");

		this.payPerItem = payPerItem;
	}
	
	// return String representation of PieceWorker object
    @Override
    public String toString() {
        return String.format("%s: %s%n%s: $%d; %s: %.2f",
                "pieceworker employee", super.toString(),
                "items sold", getItemsSold(),
                "pay per item", getPayPerItem());
    }

    /** Calculates the total earnings for this particular employee. */
	@Override
	protected double calculatePayment() {
		return this.itemsSold * this.payPerItem;
	}
}
