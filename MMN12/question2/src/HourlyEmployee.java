/*
 * 20554 ADVANCED JAVA
 * MMN12 - QUESTION 2
 * Ronen Lapushner, Dec. 2019
 */

/** A class that represents an employee.
 * Based on the class presented in figure 10.6 pp. 451 */
public class HourlyEmployee extends Employee {
	// Constants
	private double MAX_HOURS = 168.0;
	private double MIN_HOURS = 0.0;
	private double MIN_HOURLY_WAGE = 0.0; // Really illegal but that's how it's in the book...
	private int OVERTIME_THERSHOLD = 40;
	private double OVERTIME_MULTIPLIER = 1.5;
	
    // Properties
    private double wage; // wage per hour
    private double hours; // hours worked for week
    
    // constructor
    public HourlyEmployee(String firstName, String lastName,
            String socialSecurityNumber, DateOfBirth dob, double wage, double hours) 
            throws IllegalArgumentException {
        super(firstName, lastName, socialSecurityNumber, dob);
        if (wage < MIN_HOURLY_WAGE) // validate wage
        {
            throw new IllegalArgumentException(
                    "Hourly wage must be >= 0.0");
        }
        if ((hours < MIN_HOURS) || (hours > MAX_HOURS)) // validate hours
        {
            throw new IllegalArgumentException(
                    "Hours worked must be >= 0.0 and <= 168.0");
        }
        
        this.wage = wage;
        this.hours = hours;
    }
    
    // set wage
    public void setWage(double wage) throws IllegalArgumentException {
        if (wage < MIN_HOURLY_WAGE) // validate wage
        {
            throw new IllegalArgumentException(
                    "Hourly wage must be >= 0.0");
        }
        this.wage = wage;
    }
    
    // return wage
    public double getWage() {
        return wage;
    }
    
    // set hours worked
    public void setHours(double hours) throws IllegalArgumentException {
        if ((hours < MIN_HOURS) || (hours > MAX_HOURS)) // validate hours
        {
            throw new IllegalArgumentException(
                    "Hours worked must be >= 0.0 and <= 168.0");
        }
        this.hours = hours;
    }
    
    // return hours worked
    public double getHours() {
        return hours;
    }
    
    // calculate earnings; override abstract method earnings in Employee
    @Override
    protected double calculatePayment() {
        if (getHours() <= OVERTIME_THERSHOLD) // no overtime
        {
            return getWage() * getHours();
        } else {
            return OVERTIME_THERSHOLD * getWage() 
            		+ (getHours() - OVERTIME_THERSHOLD) * (getWage() * OVERTIME_MULTIPLIER);
        }
    }
    
    //return String representation of HourlyEmployee object
    @Override
    public String toString() {
        return String.format("hourly employee: %s%n%s: $%,.2f; %s: %,.2f",
                super.toString(), "hourly wage", getWage(),
                "hours worked", getHours());
    }
} // end class HourlyEmployee
