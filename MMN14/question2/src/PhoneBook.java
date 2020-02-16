/**
 * Ronen Lapushner - 313116584
 * MMN14
 * Question 2
 */

import java.io.*;
import java.util.*;

/** Class representing a phone book. */
public class PhoneBook implements Serializable {
    // Properties
    private TreeMap<String, Contact> contacts;

    // Cnstr.
    public PhoneBook() {
        this.contacts = new TreeMap<>();
    }

    // Cnstr. (from file)
    public PhoneBook(String path) throws IOException, ClassNotFoundException {
        this.load(path);
    }

    /** Saves the phone book to a path. */
    public void save(String path) throws IOException {
        // Open file and stream stream
        try (FileOutputStream fout = new FileOutputStream(path)) {
            try (ObjectOutputStream out = new ObjectOutputStream(fout)) {
                // Write out the hashmap with the contents
                out.writeObject(this.contacts);
                out.close();
            }
        }
    }

    /** Load a phone book from file. */
    public void load(String path) throws IOException, ClassNotFoundException {
        // Check if a file exists
        // NOTE: Might throw IoException if the file doesn't exist/there's some
        // other problem with it.
        try (FileInputStream fin = new FileInputStream(path)) {
            try (ObjectInputStream input = new ObjectInputStream(fin)) {
                // Read the serialized contacts
                // NOTE: Might throw ClassNotFound exception if something's wrong with the
                // serialization process.
                TreeMap<String, Contact> rawMap = (TreeMap<String, Contact>) input.readObject();
                this.contacts = rawMap;
            }
        }
    }

    /** Returns all the contact names */
    public Set<String> contactNames() {
        return this.contacts.keySet();
    }

    /** Gets full contact information, as one string, for a name (name + phone). */
    public Contact getContact(String contactName) {
        // Get the phone of the contact
        return contacts.get(contactName);
    }

    /** Adds a contact with a phone number to the phone book. */
    public void addContact(String contactName, String contactPhone) {
        this.contacts.put(contactName, new Contact(contactName, contactPhone));
    }

    /**
     * Updates the phone number of a contact.
     */
    public void editContact(String contactName, String newPhone) {
        // Adding the same contact would, essentially, update the existing contact.
        addContact(contactName, newPhone);
    }

    /**
     * Removes a contact from the book by name.
     * Throws an exception if the key is not found, or if it is null, or if the
     * type is wrong.
     */
    public void removeContact(String contactName) {
        // If the key exists, remove it.
        this.contacts.remove(contactName);
    }

    /** Returns the count of the contacts in the phone book. */
    public int size() {
        return this.contacts.size();
    }
}
