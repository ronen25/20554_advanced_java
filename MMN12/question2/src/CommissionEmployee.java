/*
 * 20554 ADVANCED JAVA
 * MMN12 - QUESTION 2
 * Ronen Lapushner, Dec. 2019
 */

/** A class that represents an employee.
 * Based on the class presented in figure 10.7 pp. 453 */
public class CommissionEmployee extends Employee {
    // Properties
    private double grossSales; // gross weekly sales
    private double commissionRate; // commission percentage
    
    // constructor
    public CommissionEmployee(String firstName, String lastName,
            String socialSecurityNumber, DateOfBirth dob, double grossSales,
            double commissionRate) {
        super(firstName, lastName, socialSecurityNumber, dob);
        if (commissionRate <= 0.0 || commissionRate >= 1.0) // validate
        {
            throw new IllegalArgumentException(
                    "Commission rate must be > 0.0 and < 1.0");
        }
        if (grossSales < 0.0) // validate
        {
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        }
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }
    
    // set gross sales amount
    public void setGrossSales(double grossSales) {
        if (grossSales < 0.0) // validate
        {
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        }
        this.grossSales = grossSales;
    }
    
    // return gross sales amount
    public double getGrossSales() {
        return grossSales;
    }
    
    // set commission rate
    public void setCommissionRate(double commissionRate) {
        if (commissionRate <= 0.0 || commissionRate >= 1.0) // validate
        {
            throw new IllegalArgumentException(
                    "Commission rate must be > 0.0 and < 1.0");
        }
        this.commissionRate = commissionRate;
    }
    
    // return commission rate
    public double getCommissionRate() {
        return commissionRate;
    }
    
    // calculate earnings; override abstract method earnings in Employee
    @Override
    protected double calculatePayment() {
        return getCommissionRate() * getGrossSales();
    }
    
    // return String representation of CommissionEmployee object
    @Override
    public String toString() {
        return String.format("%s: %s%n%s: $%,.2f; %s: %.2f",
                "commission employee", super.toString(),
                "gross sales", getGrossSales(),
                "commission rate", getCommissionRate());
    }
} // end class CommissionEmployee
