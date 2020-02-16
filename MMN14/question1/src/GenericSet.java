/**
 * Ronen Lapushner - 313116584
 * MMN14
 * Question 1
 */


import java.util.*;

public class GenericSet<E> implements Iterable<E> {
    // Properties
    private ArrayList<E> items;

    // Cnstr.
    public GenericSet() {
        // Initialize empty array
        items = new ArrayList<>();
    }

    // Cnstr. creating set from an array
    public GenericSet(E[] itemsRaw) {
        this();

        // Add the items one by one
        // Duplicates are taken care of by the insert() method.
        for (E item: itemsRaw)
            this.insert(item);
    }

    // "Copy" cnstr. for duplicating sets
    public GenericSet(GenericSet<E> otherSet) {
        this();

        // Copy over all items
        for (E item: otherSet.items)
            this.insert(item);
    }

    /**
     * Combines two sets into a single set,
     * taking care of duplicate items.
     */
    public void union(GenericSet<E> other) {
        // For every item in the other set, add it to
        // this set.
        // Duplicates are taken care of by the insert() method.
        Iterator<E> iter = other.iterator();
        while (iter.hasNext()) {
            E item = iter.next();
            this.items.add(item);
        }
    }

    /**
     * Removes the items that are not in the other set.
     */
    public void intersect(GenericSet<E> other) {
        // For every item in the current set, check if
        // it is available in the other set.
        // If not, remove it from the set.
        Iterator<E> iter = this.items.iterator();
        while (iter.hasNext()) {
            // Check if item is in the other set
            E element = iter.next();
            if (!other.isMember(element))
                iter.remove();
        }
    }

    /**
     * Checks if the current set is a subset of an other set, meaning,
     * if all items in the current set are also members of the other set.
     */
    public boolean isSubset(GenericSet<E> other) {
        // For every item in this set, check if it exists in the other set.
        for (E item: this.items) {
            if (!other.isMember(item))
                return false;
        }

        return true;
    }

    /** Checks if an item is already in the set. */
    public boolean isMember(E element) {
        return items.contains(element);
    }

    /**
     * Inserts an item into the set.
     * If the item does already exist, this operation is a NOP.
     */
    public void insert(E element) {
        // Do anything only if the item is not in the set.
        if (!items.contains(element)) {
            items.add(element);
        }
    }

    /**
     * Deletes an item from the collection.
     * If the item does not exists, this operation is a NOP.
     */
    public void delete(E element) {
        items.remove(element);
    }

    /** Returns an iterator for the set. */
    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }

    /** Returns a string representation of this set. */
    @Override
    public String toString() {
        String repr = "{ ";

        // Special case: for empty sets, return { }.
        if (this.items.size() == 0)
            return "{ }";

        // Add items to the set, except for the last,
        // which gets special formatting.
        for (int i = 0; i < this.items.size() - 1; i++)
            repr += (this.items.get(i) + ", ");

        // For the last item, add it with the final brace
        repr += (this.items.get(this.items.size() - 1) + " }");

        return repr;
    }
}
