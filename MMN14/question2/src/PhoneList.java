/**
 * Ronen Lapushner - 313116584
 * MMN14
 * Question 2
 */

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.util.*;

/** A class for displaying PhoneBook classes */
public class PhoneList extends JPanel {
    // Properties
    private JList<Contact> list;
    private DefaultListModel<Contact> internalModel;
    private PhoneBook model;

    // Cnstr.
    public PhoneList() {
        initModel();

        // Initialize GUI components
        initLayout();
        initUI();
    }

    /** Initializes the PhoneBook model. */
    private void initModel() {
        model = new PhoneBook();
    }

    /** Initializes the window layout */
    private void initLayout() {
        this.setLayout(new BorderLayout());
    }

    /** Initializes all UI components */
    private void initUI() {
        // Initialize JList and it's model
        internalModel = new DefaultListModel<>();
        list = new JList<>(internalModel);

        this.add(list);
    }

    /** Reloads the phone book view. */
    public void refresh() {
        // Clear the internal model of the list, just in case
        internalModel.clear();

        // Load every item we have in the model into the list's internal model
        Set<String> keys = model.contactNames();
        for (String key: keys) {
            // Get the full contact info
            Contact fullInfo = model.getContact(key);

            // Add to the internal model, which will be displayed in the list
            // Note: We don't check for errors, since we know the contact exists.
            internalModel.addElement(fullInfo);
        }
    }

    /** Loads a phone book from file into view. */
    public void loadBook(String path) throws IOException, ClassNotFoundException {
        // Initialize the model and load it.
        // Simply rethrow any exceptions.
        model.load(path);
        this.refresh();
    }

    /** Saves the current model to a file. */
    public void saveBook(String path) throws IOException {
        model.save(path);
    }

    /** Add contact to the phone book. */
    public void addContact(String name, String phone) {
        // Add contact and refresh the view
        model.addContact(name, phone);
        this.refresh();
    }

    /** Removes a contact from the phone book, based on that contact's name. */
    public void removeContact(String name) {
        // Remove contact and refresh view
        model.removeContact(name);
        this.refresh();
    }

    /**
     * Updates the phone number of an already-existing contact.
     * If the contact does not exist, throws an exception.
     */
    public void updatePhone(String name, String newPhone) throws Exception {
        // Check if contact exists
        Contact c = model.getContact(name);
        if (c != null) {
            model.editContact(name, newPhone);
            this.refresh();
        }
    }

    /**
     * Gets a contact based on his name.
     * If the contact does not exist, returns null.
     */
    public Contact findContact(String name) {
        return model.getContact(name);
    }

    /**
     * Finds a contact in the view and highlights it.
     * If the contact does not exist, nothing is highlighted.
     */
    public void highlightContact(String name) {
        // Check every contact in the internal model.
        // If it's our contact, highlight it in the list.
        for (int i = 0; i < internalModel.size(); i++) {
            Contact element = internalModel.getElementAt(i);
            if (element.getName().equals(name)) {
                list.setSelectedIndex(i);
                break;
            }
        }
    }

    /** Gets the currently-selected contact, or null if nothing is selected. */
    public Contact getSelection() {
        return list.getSelectedValue();
    }
}
