import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A class that executes all the merge workers and returns the merged-sorted array
 * to the user.
 */
public class MergeManager implements Runnable {
    // Properties
    private ArrayList<Integer> initialArray;
    private ArrayRepository repository;
    private final Object resultLock;
    private int numThreads;

    // Constants
    private final long WAIT_TIMEOUT = 10;

    // Cnstr.
    public MergeManager(ArrayList<Integer> initialArray, int numThreads) {
        this.numThreads = numThreads;
        this.initialArray = initialArray;

        // Initialize lock object.
        resultLock = new Object();

        // Initialize the repository
        repository = new ArrayRepository(initialArray);
    }

    /** Main worker process. */
    @Override
    public void run() {
        // Initialize executor
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        // Make sure we acquire our lock for sorting.
        synchronized (resultLock) {
            // Sorting loop stops when the workers determine that they are done.
            // We just wait using the awaitTermination() method, the workers
            // themselves know when to finish the work and terminate the threads.
            for (int i = 0; i < numThreads; i++) {
                executor.execute(new MergeWorker(repository));
            }

            // Wait for the workers to finish.
            executor.shutdown();
            try {
                executor.awaitTermination(WAIT_TIMEOUT * initialArray.size(), TimeUnit.SECONDS);
            }
            catch (InterruptedException ex) {
                System.out.println("InterruptedException caught:");
                ex.printStackTrace();
            }

            // Notify whoever that we're done
            resultLock.notify();
        }
    }

    /**
     * Gets the final sorted array.
     * Note: This method may BLOCK the caller until the sorting actually finishes!
     */
    public ArrayList<Integer> getSortedArray() {
        System.out.println("Waiting for result...");

        // Acquire the sorting lock.
        // This will block if the sorting is not done yet (because run() is holding the lock object).
        synchronized (resultLock) {
            try {
                // Wait until sorting is done. We're capping the time to WAIT_TIMEOUT,
                resultLock.wait();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            System.out.println("Done");
        }

        // By this point we are positive that there's only one array here.
        ArrayList<Integer> result = repository.pop();

        return result;
    }

    /**
     * Returns the initial array that the worker had started with.
     */
    public ArrayList<Integer> getInitialArray() {
        return this.initialArray;
    }
}
