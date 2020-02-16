/*
 * 20554 ADVANCED JAVA
 * MMN12 - QUESTION 2
 * Ronen Lapushner, Dec. 2019
 */

import java.util.*;

/** Main class */
public class Main {
	// Constants
	private static final int EMPLOYEE_BIRTHDAY_BONUS = 200;
	
    public static void main(String[] args) {
    	ArrayList<Employee> employees = new ArrayList<Employee>();
    	
    	// Create a few employees of different types
		// TODO: Add exception handling here
    	employees.add(new BasePlusCommissionEmployee("BasePlusCommission", "1", 
    			"111222", 
    			new DateOfBirth(22, 11, 1996), 120.0, 0.3, 5300.0));
    	employees.add(new HourlyEmployee("Hourly", "1", "123456",
    			new DateOfBirth(22, 12, 1995), 10.0, 40.0));
    	employees.add(new CommissionEmployee("Commission", "1", "999888",
    			new DateOfBirth(22, 5, 1994),
    			100.0, 0.2));
    	employees.add(new HourlyEmployee("Hourly", "2", "777637",
    			new DateOfBirth(22, 9, 1995), 29.12, 43.0));
    	employees.add(new SalariedEmployee("Salaried", "1", "777810", 
    			new DateOfBirth(21, 11, 1990), 900.0));
    	employees.add(new PieceWorker("Piece", "1", "726463", 
    			new DateOfBirth(10, 11, 1993), 900, 67.0));
    	
    	// Print employee details, and add a birthday bonus of 200 NIS
    	// for employees that have their birthdays this month.
    	int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
    	for (Employee employeeInfo: employees) {
    		// Print title
    		System.out.println("EMPLOYEE DETAILS\n=====================\n" + employeeInfo.toString());
    		
    		// Check if the employee should receive a birthday bonus
    		if (employeeInfo.getDateOfBirth().getMonth() == currentMonth) {
    			// Print a nice greeting
    			System.out.println("\nHAPPY BIRTHDAY to " + employeeInfo.getFirstName());
    			System.out.println("Bonus: " + EMPLOYEE_BIRTHDAY_BONUS + "\n");
    			
    			// Add the bonus to the employee
    			employeeInfo.setBonus(EMPLOYEE_BIRTHDAY_BONUS);
    		}
    		
    		// Print the total earnings the employee has.
    		System.out.println(employeeInfo.getFirstName() + "'s earnings: $" + employeeInfo.earnings() + "\n\n");
    	}
    }
}
