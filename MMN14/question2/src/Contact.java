/**
 * Ronen Lapushner - 313116584
 * MMN14
 * Question 2
 */

import java.io.Serializable;

/** A class that represents a single phone book contact. */
public class Contact implements Comparable<Contact>, Serializable {
    // Properties
    private String name, phone;

    // Cnstr.
    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    /** Gets the name. */
    public String getName() {
        return name;
    }

    /** Sets the name. */
    public void setName(String name) {
        this.name = name;
    }

    /** Gets the phone number. */
    public String getPhone() {
        return phone;
    }

    /** Sets the phone number. */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** Returns the contact's details in the following format: "[name] - [phone]" */
    @Override
    public String toString() {
        return name + " - " + phone;
    }

    /**
     * Compares two contacts for their names and phone numbers.
     */
    @Override
    public int compareTo(Contact contact) {
        // null Error check
        if (contact == null)
            return -1;

        // Compare first names
        int flag = this.name.compareTo(contact.getName());
        if (flag == 0) {
            flag = this.phone.compareTo(contact.getPhone());
        }

        return flag;
    }

    /**
     * Checks whether two contacts have the same name and the same phone number.
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the object is a Contact
        if (obj == null || !(obj instanceof Contact))
            return false;

        // Cast
        Contact other = (Contact) obj;

        // Compare names and phones
        return this.name.equals(other.getName()) && this.phone.equals(other.getPhone());
    }
}
