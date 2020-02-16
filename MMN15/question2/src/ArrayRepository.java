import java.util.*;

/** A class that stores mini-arrays in a thread-safe manner. */
public class ArrayRepository {
    // Properties
    private ArrayList<ArrayList<Integer>> arrays;
    private int initialSize;

    // Cnstr.
    public ArrayRepository() {
        this.arrays = new ArrayList<>();
    }

    // Cnstr that takes an existing array and creates a repository from it.
    public ArrayRepository(ArrayList<Integer> rawArray) {
        // Initialize properties
        this();

        // Initialize from array
        initFromRawArray(rawArray);
    }

    /** Create a repository from a raw array. */
    private void initFromRawArray(ArrayList<Integer> rawArray) {
        // For every element, create an ArrayList of a single element,
        // and push that to our arrays vector.
        for (int item: rawArray) {
            ArrayList<Integer> singleArray = new ArrayList<>();
            singleArray.add(item);

            arrays.add(singleArray);
        }

        // Set initial size for later reference
        this.initialSize = arrays.size();
    }

    /** Gets the initial size of the array. */
    public int getInitialSize() {
        return this.initialSize;
    }

    /**
     * Retrieves one array from the repository.
     * Note: If no arrays are available, a null is returned.
     */
    public synchronized ArrayList<Integer> pop() {
        // Special case: nothing to pop
        if (arrays.size() == 0)
            return null;

        return arrays.remove(0);
    }

    /** Puts an array into the repository. */
    public synchronized void put(ArrayList<Integer> array) {
        arrays.add(array);
    }

    /** Returns the size of the array repository. */
    public synchronized int size() {
        return arrays.size();
    }

    /** Returns the first array in the repository, or null if there's none. */
    public synchronized ArrayList<Integer> peek() {
        // Special case: nothing to pop
        if (arrays.size() == 0)
            return null;

        return arrays.get(0);
    }

    /**
     * Returns the repository as an integer array of values.
     * Caution: This array is not guaranteed to be sorted in any particular order.
     */
    public synchronized ArrayList<Integer> toArrayList() {
        ArrayList<Integer> interim = new ArrayList<>();
        for (ArrayList<Integer> item: arrays) {
            // For every item in the array, throw it into the interim array.
            interim.addAll(item);
        }

        return interim;
    }
}
