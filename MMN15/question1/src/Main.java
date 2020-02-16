import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int dimension, threads, passes;
        String rawValue = "";

        // Ask the user for the image dimension (n), amount of threads (m)
        // and the number of passes (t).
        try {
            rawValue = JOptionPane.showInputDialog(null,
                    "Input the image dimension:",
                    "Input",
                    JOptionPane.INFORMATION_MESSAGE);
            if (rawValue != null)
                dimension = Integer.parseInt(rawValue);
            else
                return;

            rawValue = JOptionPane.showInputDialog(null,
                    "Input the amount of threads:",
                    "Input",
                    JOptionPane.INFORMATION_MESSAGE);
            if (rawValue != null)
                threads = Integer.parseInt(rawValue);
            else
                return;

            rawValue = JOptionPane.showInputDialog(null,
                    "Input the amount of passes:",
                    "Input",
                    JOptionPane.INFORMATION_MESSAGE);
            if (rawValue != null)
                passes = Integer.parseInt(rawValue);
            else
                return;
        }
        catch (NumberFormatException numEx) {
            JOptionPane.showMessageDialog(null, "Error: " + numEx.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Input check:
        // 1. No values can be negative
        // 2. Number of threads cannot be bigger than image dimension
        if (threads <= 0 || passes <= 0 || dimension <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Error",
                    "Error: Values cannot be 0 or negative!",
                    JOptionPane.ERROR_MESSAGE);
        }

        // Show the main window, passing the parameters
        MainWindow window = new MainWindow(dimension, threads, passes);
        window.setVisible(true);
    }
}
