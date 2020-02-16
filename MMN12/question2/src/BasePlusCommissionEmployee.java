/*
 * 20554 ADVANCED JAVA
 * MMN12 - QUESTION 2
 * Ronen Lapushner, Dec. 2019
 */

/** A class that represents an employee.
 * Based on the class presented in figure 10.8 pp. 455 */
public class BasePlusCommissionEmployee extends CommissionEmployee {
    // Properties
    private double baseSalary; // base salary per week
    
    // constructor
    public BasePlusCommissionEmployee(String firstName, String lastName,
            String socialSecurityNumber, DateOfBirth dob, double grossSales,
            double commissionRate, double baseSalary) {
        super(firstName, lastName, socialSecurityNumber, dob,
                grossSales, commissionRate);
        if (baseSalary < 0.0) // validate baseSalary
        {
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        }
        this.baseSalary = baseSalary;
    }
    
    // set base salary
    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0.0) // validate baseSalary
        {
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        }
        this.baseSalary = baseSalary;
    }
    
    // return base salary
    public double getBaseSalary() {
        return baseSalary;
    }
    
    // calculate earnings; override method earnings in CommissionEmployee
    @Override
    protected double calculatePayment() {
        return getBaseSalary() + super.calculatePayment();
    }
    
    // return String representation of BasePlusCommissionEmployee object
    @Override
    public String toString() {
        return String.format("%s %s; %s: $%,.2f",
                "base-salaried", super.toString(),
                "base salary", getBaseSalary());
    }
} // end class BasePlusCommissionEmployee
