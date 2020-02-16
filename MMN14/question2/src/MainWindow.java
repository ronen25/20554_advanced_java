/**
 * Ronen Lapushner - 313116584
 * MMN14
 * Question 2
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

/** Main application window */
public class MainWindow extends JFrame {
    // Constants
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final String WINDOW_TITLE = "Phone Book";

    private final String DIALOG_TITLE_OPEN = "Open phone book...";
    private final String DIALOG_TITLE_SAVE = "Save phone book...";

    // Properties
    private MenuBarPanel menuPanel;
    private PhoneList phoneList;
    private BorderLayout layout;
    private JFileChooser fileChooser;

    // Cnstr.
    public MainWindow() {
        initLayout();
        initUI();
        initActions();
    }

    /** Initializes the window layout */
    private void initLayout() {
        layout = new BorderLayout();
        this.setLayout(layout);
    }

    /** Initializes all UI components */
    private void initUI() {
        // Set size and title
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle(WINDOW_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the the menu panel
        menuPanel = new MenuBarPanel();

        // Initialize contact list
        phoneList = new PhoneList();

        // Add components
        this.add(menuPanel, BorderLayout.NORTH);
        this.add(phoneList, BorderLayout.CENTER);
    }

    /** Registers action listeners for all menu items. */
    private void initActions() {
        // Initialize the file chooser
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // "Save" callback
        menuPanel.getSaveMenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonClicked();
            }
        });

        // "Load" callback
        menuPanel.getLoadMenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadButtonClicked();
            }
        });

        // "Add" callback
        menuPanel.getAddMenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addButtonClicked();
            }
        });

        // "Remove" callback
        menuPanel.getRemoveMenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                removeButtonClicked();
            }
        });

        // "Edit"
        menuPanel.getEditMenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                editButtonClicked();
            }
        });

        // "Search"
        menuPanel.getSearchMenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchButtonClicked();
            }
        });
    }

    /** Action callback for the "save" button */
    private void saveButtonClicked() {
        // Set title and show dialog
        fileChooser.setDialogTitle(DIALOG_TITLE_OPEN);
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            // User selected a path, (try to) save the phone book to that path.
            String chosenPath = fileChooser.getSelectedFile().getAbsolutePath();

            try {
                phoneList.saveBook(chosenPath);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "I/O error while saving file '" + chosenPath + "':\n" + ex.getMessage(),
                        "I/O Error (Save)", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** Action callback for the "load" button */
    private void loadButtonClicked() {
        // Set title and show dialog
        fileChooser.setDialogTitle(DIALOG_TITLE_OPEN);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            // User selected a file - construct a phone book from it (or at least try)
            // All errors (including if file doesn't exist) are handled by the fromFile method.
            try {
                // Load the phone book (erasing the previous one, if any)
                phoneList.loadBook(fileChooser.getSelectedFile().getAbsolutePath());
            }
            catch (IOException ioEx) {
                JOptionPane.showMessageDialog(this,
                        "I/O error while loading file:\n" + ioEx.getMessage(),
                        "I/O Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (ClassNotFoundException clEx) {
                JOptionPane.showMessageDialog(this,
                        "Deserialization error while loading file:\n" + clEx.getMessage(),
                        "Deserialization Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Unknown error while loading file:\n" + ex.toString(),
                        "Unknown Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** Action callback for the "add contact" button */
    private void addButtonClicked() {
        // Show input for name and phone
        String name = JOptionPane.showInputDialog("Enter contact name:");
        if (name.isEmpty()) {
            // Name is empty?
            JOptionPane.showMessageDialog(this, "Name cannot be empty!", "Error",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        // Input phone
        String phone = JOptionPane.showInputDialog("Enter contact phone number:");
        if (phone.isEmpty()) {
            // Phone is empty?
            JOptionPane.showMessageDialog(this, "Phone cannot be empty!", "Error",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        // Add to the phone book.
        phoneList.addContact(name, phone);
    }

    /** Action callback for the "remove contact" button */
    private void removeButtonClicked() {
        // Check if something is selected in the phone list
        Contact contact = phoneList.getSelection();
        if (contact == null) {
            // No selection - display this to the user.
            JOptionPane.showMessageDialog(this, "Nothing selected!", "No selection",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // The key is the first part - so trim the space at the end.
        phoneList.removeContact(contact.getName());
    }

    /** Action callback for the "edit contact" button */
    private void editButtonClicked() {
        int answer;

        // Check if something is selected in the phone list
        Contact contact = phoneList.getSelection();
        if (contact == null) {
            // No selection - display this to the user.
            JOptionPane.showMessageDialog(this, "Nothing selected!", "No selection",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ask the user to provide new data
        String phone = JOptionPane.showInputDialog(this, "Enter the contact's phone:",
                contact.getPhone());

        // Update the contact object
        try {
            phoneList.updatePhone(contact.getName(), phone);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error:\n" + ex.getMessage(),
                    "Error Updating Contact", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Action callback for the "search contact" button */
    private void searchButtonClicked() {
        // Ask the user for a name to search
        String nameToSearch = JOptionPane.showInputDialog(this, "Enter the name to search:");
        if (nameToSearch != null && !nameToSearch.isEmpty()) {
            // Highlight it in the list if it exists.
            phoneList.highlightContact(nameToSearch);
        }
    }
}
