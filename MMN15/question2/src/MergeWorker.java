import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.Condition;

/**
 * One single-threaded worker that takes two arrays from the repository.
 */
public class MergeWorker implements Runnable {
    // Properties
    private final ArrayRepository repoRef;

    // Cnstr.
    public MergeWorker(ArrayRepository repoRef) {
        this.repoRef = repoRef;
    }

    /** Main worker process. */
    @Override
    public void run() {
        // Loop's stop condition is the provided condition variable.
        while (!this.isDoneWorking()) {
            // Start by retrieving two arrays from the repository.
            ArrayList<Integer> arr1, arr2;
            arr1 = repoRef.pop();
            arr2 = repoRef.pop();

            // Special case: One array is null. Maybe we're done sorting (so the last array that remains
            // is the full, already-sorted one) or the other threads took everything.
            // Either way there's nothing for this worker to do.
            // Note: Even if all workers have arrays and there are still sorts to be done,
            // the manager will spawn more workers, so
            if (arr1 == null && arr2 == null) {
                continue;
            }
            else if (arr1 == null && arr2 != null) {
                repoRef.put(arr2);
                continue;
            }
            else if (arr1 != null && arr2 == null) {
                repoRef.put(arr1);
                continue;
            }

            // We take one array (doesn't really matter which; O(n) complexity for both)
            // and dump all data from the other one.
            arr2.addAll(arr1);

            // Dump the second array to a raw array that we can easily sort.
            int[] rawArray = unboxArray(arr2);
            Arrays.sort(rawArray);

            // Create a new array from the sorted raw array, put that into the repository.
            ArrayList<Integer> finalList = new ArrayList<>(rawArray.length);
            for (int item : rawArray)
                finalList.add(item);

            repoRef.put(finalList);
        }
    }

    /**
     * Determines whether the sorting is done.
     * With our system, the sorting is done when:
     * 1. There's only one array in the repository.
     * 2. That array is the size of the one complete array.
     *
     * Note: All repository accesses are internally synchronized by the repository
     * class itself.
     */
    private boolean isDoneWorking() {
        ArrayList<Integer> onlyArray = repoRef.peek();
        return (repoRef.size() == 1 && onlyArray != null && onlyArray.size() == repoRef.getInitialSize());
    }

    /** Unboxes an ArrayList<Integer> to an int[]. */
    private int[] unboxArray(ArrayList<Integer> array) {
        int[] raw = new int[array.size()];
        for (int i = 0; i < raw.length; i++)
            raw[i] = array.get(i);

        return raw;
    }
}
