/*
 * 20554 ADVANCED JAVA
 * MMN12 - QUESTION 2
 * Ronen Lapushner, Dec. 2019
 */

/** A class that represents the date of birth of a worker. */
public class DateOfBirth {
	// Constants
	private int DAY_MAX = 31;
	private int MONTH_MAX = 12;
	private int YEAR_MIN = 0;
	
    // Properties
    private int day, month, year;
    
    // Constructor
    public DateOfBirth(int day, int month, int year) throws IllegalArgumentException {
        // We call the setters since they perform bound checks.
        this.setDay(day);
        this.setMonth(month);
        this.setYear(year);
    }

    // Gets the day
    public int getDay() {
        return day;
    }

    // Gets the month
    public int getMonth() {
        return month;
    }

    // Gets the year
    public int getYear() {
        return year;
    }
    
    // Sets the day
    public void setDay(int value) throws IllegalArgumentException {
    	// Check bounds
    	if (value <= 0 || value > DAY_MAX)
    		throw new IllegalArgumentException("Day value must be between 1 and 31.");
    	
		this.day = value;
	}

    // Sets the month
	public void setMonth(int value) throws IllegalArgumentException {
		// Check bounds
    	if (value < 0 || value > MONTH_MAX)
    		throw new IllegalArgumentException("Month value must be between 1 and 12.");
    	
		this.month = value;
	}

	// Sets the year
	public void setYear(int value) throws IllegalArgumentException {
		// Check bounds
    	if (value < YEAR_MIN)
    		throw new IllegalArgumentException("Year value must be positive.");
    	
		this.year = value;
	}

	// Format is dd/mm/yy.
	@Override
    public String toString() {
    	return day + "/" + month + "/" + year;
    }
}
