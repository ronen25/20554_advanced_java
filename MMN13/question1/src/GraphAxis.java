import javax.swing.*;
import java.awt.*;

public class GraphAxis extends JPanel {
    // Constants

    // Properties
    private boolean hortizontal;
    private int minValue, maxValue, gap;
    private int visualGap;

    /** Constructor */
    public GraphAxis(int minValue, int maxValue, int gap, boolean horizontal) {
        // Initialize variables
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.gap = gap;
        this.hortizontal = horizontal;
    }

    @Override
    public void paintComponent(Graphics g) {
        // Set graphics
        g.setColor(Color.BLACK);

        // Draw one horizontal/vertical line throughout the component
        if (this.hortizontal)
            g.drawLine(0, 0, this.getWidth(), 0);
        else
            g.drawLine(0, 0, 0, this.getHeight());

        // Based on the size of the component, calculate the visual gap.
    }

    @Override
    public Dimension getPreferredSize() {
        if (this.hortizontal)
            return new Dimension(getWidth(), 100);
        else
            return new Dimension(100, getHeight());
    }

    /** Sets the orientation of the graph. */
    public void setOrientation(boolean hortizontal) {
        this.hortizontal = hortizontal;
    }

    /** Gets whether the axis is horizontally or vertically oriented. */
    public boolean getHorizontal() {
        return hortizontal;
    }

    /** Gets the minimum value in the axis. */
    public int getMinValue() {
        return minValue;
    }

    /** Sets the minimum value in the axis. */
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    /** Gets the maximum value in the axis. */
    public int getMaxValue() {
        return maxValue;
    }

    /** Sets the maximum value in the axis. */
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /** Gets the gap between two values in the axis. */
    public int getGap() {
        return gap;
    }

    /** Set the gap between two values in the axis. */
    public void setGap(int gap) throws IllegalArgumentException {
        // Check bounds
        if (gap <= 0)
            throw new IllegalArgumentException("Value gap must be > 1");

        this.gap = gap;
    }

    /** Get the amount of pixels between every value in the graph axis. */
    public int getVisualGap() {
        return visualGap;
    }
}
