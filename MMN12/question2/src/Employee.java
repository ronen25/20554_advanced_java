/*
 * 20554 ADVANCED JAVA
 * MMN12 - QUESTION 2
 * Ronen Lapushner, Dec. 2019
 */

/** A class that represents an employee.
 * Based on the class presented in figure 10.4 pp. 448 */
public abstract class Employee {
    // Properties
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    private DateOfBirth dateOfBirth;
    private double bonus;

    // constructor
    public Employee(String firstName, String lastName,
            String socialSecurityNumber,
            DateOfBirth dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.dateOfBirth = dob;
        this.bonus = 0;
    }

    // return first name
    public String getFirstName() {
        return firstName;
    }
    
    // return last name
    public String getLastName() {
        return lastName;
    }
    
    // return social security number
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }
    
    // return date of birth
    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }
    
    // return String representation of Employee object
    @Override
    public String toString() {
        return String.format("%s %s%nsocial security number: %s",
                getFirstName(), getLastName(), getSocialSecurityNumber());
    }
    
    // Method that adds a bonus to the worker's total earnings
    public void setBonus(double bonus) throws IllegalArgumentException {
    	// Bonus must be positive
    	if (bonus < 0)
    		throw new IllegalArgumentException("Employee bonus cannot be negative!");
    	
    	this.bonus = bonus;
    }
    
    // Getter for the bonus
    public double getBonus() {
    	return bonus;
    }
    
    // Abstract method to calculate the base payment (no bonuses) that the worker deserves.
    // Bonus calculation is outside of the scope of the concrete classes; we handle it
    // ourselves in the earnings() method.
    protected abstract double calculatePayment();
    
    // Earnings is the actual salary that the worker has.
    // It's a sum of the calculated payment and the bonus.
    // We make this final so that overriding methods will not "forget" or mess around
    // with the bonus calculation - only with the basic salary calculation. We'll take care of
    // the bonus handling ourselves.
    public final double earnings() {
    	return calculatePayment() + this.bonus;
    }
}
