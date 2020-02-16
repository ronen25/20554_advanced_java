import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/** Main class for the application. */
public class Main {
    // Constants
    private static final int MAX_RANDOM_BOUND = 100;

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int arrLen, numThreads;

        // Input data from user
        System.out.println("Enter the length of the array: ");
        arrLen = scanner.nextInt();

        System.out.println("Enter the amount of sorting threads: ");
        numThreads = scanner.nextInt();

        // Prime the array
        for (int i = 0; i < arrLen; i++) {
            int rand = random.nextInt(MAX_RANDOM_BOUND) + 1; // 1..100
            arr.add(rand);
        }

        // Print the array
        System.out.println("Array before sorting: ");
        for (int item: arr)
            System.out.print(item + ", ");
        System.out.println();

        // Spawn the manager and begin the sorting
        MergeManager mergeManager = new MergeManager(arr, numThreads);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(mergeManager);
        executor.shutdown();

        // We're not blocking explicitly; instead, we're using the merge manager's
        // getSortedArray() method, which would block if the result is not done yet.
        ArrayList<Integer> sortedArray = mergeManager.getSortedArray();

        System.out.println("\nArray AFTER sorting:");

        for (int item: sortedArray)
            System.out.print(item + ", ");
        System.out.println();
    }
}
