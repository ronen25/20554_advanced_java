/*
 * 20554 ADVANCED JAVA
 * MMN12 - QUESTION 2
 * Ronen Lapushner, Dec. 2019
 */
import java.util.*;

/** Main class for MMN12 question 1 */
public class Main {
	// Constants
	private static final int AMOUNT_POLYNOMS = 2;
	private static final String ANSWER_YES = "y";
	
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	Polynom[] polynoms = new Polynom[AMOUNT_POLYNOMS];
    	boolean hasError = false;
    	
    	// Input values/powers for AMOUNT_POLYNOMS polynoms
    	for (int i = 0; i < AMOUNT_POLYNOMS; i++) {
    		// We use dynamic arraylists to store the powers
    		ArrayList<Integer> powers = new ArrayList<>();
        	ArrayList<Double> values = new ArrayList<>();
        	
        	// Print a nice greeting to the user.
    		System.out.println("INPUT VALUES FOR POLYNOM " + (i + 1));
    		System.out.println("==============================================");
    		
    		// Input pairs of a value and a power.
    		// MMN allows us to assume the input is correct.
    		while (true) {
    			System.out.println("Enter value and power: ");
    			
    			// Input value
    			double value = scanner.nextDouble();
    			
    			// Input power
    			int power = scanner.nextInt();
    			
    			// Add the pair to their respective arrays
    			values.add(value);
    			powers.add(power);
    			
    			// Ask the user if he wishes to input more
    			System.out.println("\nMore for Polynom " + (i + 1) + "? (y/n) ");
    			String ans = scanner.next();
    			if (!ans.toLowerCase().equals(ANSWER_YES))
    				break;
    		}
    		
    		// Initialize a Polynom with the values and the powers
    		// we've gathered from the user.
    		double[] valuesArr = new double[values.size()];
    		int[] powersArr = new int[powers.size()];
    		
    		// Copy over elements from the arraylists to the raw arrays
    		for (int ii = 0; ii < valuesArr.length; ii++) {
    			valuesArr[ii] = values.get(ii);
    			powersArr[ii] = powers.get(ii);
    		}
    		
    		try {
    			// Create polynom
    			polynoms[i] = new Polynom(valuesArr, powersArr);
    		}
    		catch (Exception e) {
    			System.out.println("\nERROR CREATING POLYNOM: " + e.toString());
    			hasError = true;
    			break;
    		}
    	}
    	
    	// Check if an error occurred; if it did, we quit.
    	if (!hasError) {
    		// Print the two polynoms.
    		for (int i = 0; i < AMOUNT_POLYNOMS; i++)
    			System.out.println("Polynom " + (i + 1) + ": " + polynoms[i].toString());
    		
    		// Demonstrate equals functionality
    		System.out.println("Are polynoms equal: " + polynoms[0].equals(polynoms[1]));
    		
    		// Demonstrate derivation
    		for (int i = 0; i < AMOUNT_POLYNOMS; i++)
    			System.out.println("Polynom " + (i + 1) + " derivative: " + polynoms[i].derivative().toString());
    		
    		// Perform various operations on the polynoms.
    		Polynom plusPolynom = polynoms[0].plus(polynoms[1]);
    		Polynom minusPolynom = polynoms[0].minus(polynoms[1]);
    		
    		System.out.println("p1 + p2 = " + plusPolynom.toString());
    		System.out.println("p1 - p2 = " + minusPolynom.toString());
    	}
    	else {
    		// We have encountered some error earlier, which we have already printed,
    		// so now we can just quit.
    		System.out.println("Error encountered; quitting the applicaiton.");
    	}
    }
}
