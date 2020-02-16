/**
 * Ronen Lapushner - 313116584
 * MMN14
 * Question 1
 */

import java.util.*;

public class Person implements Comparable<Person> {
    // Properties
    private int id;
    private String firstName, lastName;

    // Cnstr.
    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /** Gets the person's ID. */
    public int getId() {
        return id;
    }

    /** Sets the person's ID. */
    public void setId(int id) {
        this.id = id;
    }

    /** Gets the person's first name. */
    public String getFirstName() {
        return firstName;
    }

    /** Sets the person's first name. */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** Gets the person's last name. */
    public String getLastName() {
        return lastName;
    }

    /** Sets the person's last name. */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** Returns the person's full name (first name, space, then last name). */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns the string representation of this person in the following format:
     * [first name] [last name] ([id])
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + "(" + id + ")";
    }

    /** Returns true if two Persons have the same names; otherwise false. */
    @Override
    public boolean equals(Object other) {
        // Check casting
        if (!(other instanceof Person))
            return false;

        // Cast the object
        Person otherPerson = (Person)other;

        // Persons are equal if they have the same names
        return (this.firstName.equals(otherPerson.getFirstName()) &&
                this.lastName.equals(otherPerson.getLastName()));
    }

    /** Compares two Person objects. */
    @Override
    public int compareTo(Person other) {
        // If the person's last and first name are equal, return 0.
        // If this person's names are lexicographically different, return the comparison result
        // of the names.
        int result;

        // Compare first names
        result = this.firstName.compareTo(other.getFirstName());
        if (result == 0) {
            // Compare the last names.
            result = this.lastName.compareTo(other.getLastName());
        }

        // Return whatever the result was.
        return result;
    }
}
