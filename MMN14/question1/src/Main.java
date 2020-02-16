/**
 * Ronen Lapushner - 313116584
 * MMN14
 * Question 1
 */

import java.util.*;

public class Main {
    // Constants
    private static final int COUNT_SETS = 3;
    private static final int RAND_MAX = 100;
    private static final int NUM_COUNT = 10;
    private static final int FIRST_SET = 0;
    private static final int SECOND_SET = 1;
    private static final int THIRD_SET = 2;
    private static final int AMOUNT_NUMBERS = 2;

    /** Essentially implements everything in Question 1 section 3 */
    private static void personGroupOperations(Scanner scanner) {
        // Local constant since it's only used here
        final int NUM_PERSONS = 5;

        // Create 5 person group
        GenericSet<Person> persons = new GenericSet<>();
        for (int i = 0; i < NUM_PERSONS; i++) {
            // Input some details about the people
            int id;
            String firstName, lastName;

            System.out.println("Input person " + (i + 1) + "'s ID:");
            id = scanner.nextInt();

            System.out.println("Input person " + (i + 1) + "'s first name:");
            firstName = scanner.next();

            System.out.println("Input person " + (i + 1) + "'s last name:");
            lastName = scanner.next();

            // Create a new person with all the details, then add it to the set
            Person person = new Person(id, firstName, lastName);
            persons.insert(person);
        }

        // Display the group
        System.out.println("Group:\n" + persons.toString());

        // Apply the minValue method and display the result
        Person minPerson = GenericUtils.minValue(persons);
        System.out.println("Minimal person found: " + minPerson.toString());
    }

    /** Creates the fourth group, as described in section 2 */
    private static GenericSet<Integer> createFourthGroup(Scanner scanner) {
        Integer[] nums = new Integer[AMOUNT_NUMBERS];

        System.out.println("Input two numbers: ");
        nums[0] = scanner.nextInt();
        nums[1] = scanner.nextInt();

        // Create the group from the numbers and return it
        return new GenericSet<Integer>(nums);
    }

    /** Applies the various operations on the sets from section 2 */
    private static void numberOperations(Scanner scanner, ArrayList<GenericSet<Integer>> sets) {
        int number;

        // Input number from user
        System.out.println("Input a number: ");
        number = scanner.nextInt();

        // Is in first set?
        boolean isInFirstSet = sets.get(FIRST_SET).isMember(number);
        System.out.println("Number is in first set = " + isInFirstSet);

        // Add to second set, print the set
        GenericSet<Integer> set2 = sets.get(SECOND_SET);
        set2.insert(number);
        System.out.println("Set 2 after adding number: " + set2.toString());

        // Remove from set 3, print the set
        GenericSet<Integer> set3 = sets.get(THIRD_SET);
        set3.delete(number);
        System.out.println("Set 3 after removing number: " + set3.toString());
    }

    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<GenericSet<Integer>> sets = new ArrayList<>(COUNT_SETS);
        Scanner scanner = new Scanner(System.in);

        // Create the sets
        System.out.println("Initializing sets...");
        for (int i = 0; i < COUNT_SETS; i++) {
            GenericSet<Integer> set = new GenericSet<>();

            // Put random values in set
            for (int ii = 0; ii < NUM_COUNT; ii++)
                set.insert(rand.nextInt(RAND_MAX));

            // Put set in arraylist
            sets.add(set);
        }

        // Display all sets
        System.out.println("Sets are: ");
        for (int i = 0; i < COUNT_SETS; i++)
            System.out.println("SET " + (i + 1) + ": " + sets.get(i).toString());

        // Union sets 1 and 2
        System.out.println("Union with set 1 and 2:");
        GenericSet<Integer> set1 = sets.get(FIRST_SET), set2 = sets.get(SECOND_SET);
        GenericSet<Integer> unionSet = new GenericSet<>(set1);
        unionSet.union(set2);

        // unionSet = union of set1 and set2
        System.out.println(unionSet.toString());

        // Intersection with first and third sets
        GenericSet<Integer> set3 = sets.get(THIRD_SET);
        GenericSet<Integer> interSet = new GenericSet<>(set1);

        System.out.println("Intersection with set 1 and 3:");
        interSet.intersect(set3);

        System.out.println(interSet.toString());

        // Input two numbers, create 4th group, check if the 4th set is a subset of one of the groups.
        GenericSet<Integer> set4 = createFourthGroup(scanner);
        sets.add(set4);

        // Check if this set is a subset of the other sets
        for (int i = 0; i < COUNT_SETS - 1; i++) {
            if (set4.isSubset(sets.get(i)))
                System.out.println("Set 4 is a subset of set " + (i + 1));
        }

        // The rest of the number operations
        numberOperations(scanner, sets);

        // NOTE: Because section 3 of the question is so different, we do everything
        // in a separate function, to keep everything clean and concise.
        // (Although we DO reuse the scanner, since there's no point in reinitializing a new one)
        personGroupOperations(scanner);
    }
}
