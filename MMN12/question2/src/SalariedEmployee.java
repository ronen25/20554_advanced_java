/*
 * 20554 ADVANCED JAVA
 * MMN12 - QUESTION 2
 * Ronen Lapushner, Dec. 2019
 */

/** A class that represents an employee.
 * Based on the class presented in figure 10.5 pp. 450 */
public class SalariedEmployee extends Employee {
    // Properties
    private double weeklySalary;
    
    // constructor
    public SalariedEmployee(String firstName, String lastName,
            String socialSecurityNumber, DateOfBirth dob, double weeklySalary) throws IllegalArgumentException {
        super(firstName, lastName, socialSecurityNumber, dob);
        if (weeklySalary < 0.0) {
            throw new IllegalArgumentException(
                    "Weekly salary must be >= 0.0");
        }
        this.weeklySalary = weeklySalary;
    }

    // set salary
    public void setWeeklySalary(double weeklySalary) throws IllegalArgumentException {
        if (weeklySalary < 0.0) {
            throw new IllegalArgumentException(
                    "Weekly salary must be >= 0.0");
        }
        this.weeklySalary = weeklySalary;
    }
    
    // return salary
    public double getWeeklySalary() {
        return weeklySalary;
    }
    
    // calculate earnings; override abstract method earnings in Employee
    @Override
    protected double calculatePayment() {
        return getWeeklySalary();
    }
    
    // return String representation of SalariedEmployee object
    @Override
    public String toString() {
        return String.format("salaried employee: %s%n%s: $%,.2f",
                super.toString(), "weekly salary", getWeeklySalary());
    }
} // end class SalariedEmployee
