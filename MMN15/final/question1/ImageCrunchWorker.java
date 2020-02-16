import javax.swing.*;
import java.util.List;
import java.util.concurrent.*;

/** Worker that spawns sub-threads that crunch the image. */
public class ImageCrunchWorker extends SwingWorker<CellMatrix, CellMatrix> {
    // Properties
    private ExecutorService executor;
    private int numThreads, passes;
    private CellMatrix currentState;

    // References to UI elements
    private final ImageDisplay display;
    private final JButton btnGo;

    // Constants
    private final long MAX_TIMEOUT = 10;
    private final long WORKER_PASS_TIMEOUT = 700;
    private String BUTTON_STRING_WORKING = "WORKING...";
    private String BUTTON_STRING_GO = "Go";

    // Cnstr.
    public ImageCrunchWorker(ImageDisplay display, JButton btnGo, int passes, int numThreads) {
        // Initialize properties, including the display handle
        this.passes = passes;
        this.numThreads = numThreads;
        this.display = display;
        this.btnGo = btnGo;
    }

    @Override
    /**
     * Crunches an image for maxPasses passes.
     * Every time one pass is done, we publish() the result and suspend the worker for a certain
     * amount of time (so that the user could see the crunch passes).
     */
    protected CellMatrix doInBackground() throws Exception {
        // Update button state
        btnGo.setEnabled(false);
        btnGo.setText(BUTTON_STRING_WORKING);

        // Every cruncher gets an equal amount of rows to crunch on.
        int numRowsPerCruncher = display.getMatrixHeight() / numThreads;

        for (int pass = 0; pass < passes; pass++) {
            // Copy the current display model.
            currentState = new CellMatrix(display.getModel());

            // Executor service for executing our crunchers.
            executor = Executors.newFixedThreadPool(this.numThreads);
            int minRow = 0, maxRow = numRowsPerCruncher - 1;

            // Spawn the crunchers.
            for (int num = 0; num < numThreads; num++) {
                System.out.println("Cruncher spawned: " + minRow + " -> " + maxRow);

                // Spawn the cruncher.
                executor.execute(new ImageCruncher(minRow, maxRow, currentState));

                // Calculate the bounds for the next cruncher. Account for the offset
                // (i.e. the cruncher needs the NEXT row from what the previous cruncher did).
                minRow = maxRow + 1;
                maxRow = minRow + numRowsPerCruncher;

                // The last cruncher might have less work to do since the work hasn't
                // distributed equally between the crunchers.
                if (maxRow >= display.getMatrixHeight())
                    maxRow = display.getMatrixHeight() - 1;

                // Note: If numThreads > imageDimension then some workers will have no work.
                // It's up to the worker itself to determine that.
            }

            // Wait for all crunchers to finish.
            // We give every cruncher (10 * rows) seconds (meaning, 10 seconds per row),
            // so the total wait time is 10 * rows * numThreads.
            executor.shutdown();
            executor.awaitTermination(MAX_TIMEOUT * numRowsPerCruncher * numThreads, TimeUnit.SECONDS);

            // All crunchers are done, so publish the result.
            publish(currentState);

            // Delay the pass so that the user could see the crunch result.
            // NOTE: This delays the worker thread, NOT THE EVENT DISPATCH THREAD, so the
            // UI will still be responsive.
            Thread.sleep(WORKER_PASS_TIMEOUT);
        }

        // Return is mainly for readability; process() takes care of displaying stuff to the user.
        return currentState;
    }

    @Override
    protected void process(List<CellMatrix> chunks) {
        super.process(chunks);

        // Set the model for the display
        display.setModel(currentState);
    }

    @Override
    protected void done() {
        super.done();

        btnGo.setEnabled(true);
        btnGo.setText(BUTTON_STRING_GO);
    }
}
