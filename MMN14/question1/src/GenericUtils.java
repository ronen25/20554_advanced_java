/**
 * Ronen Lapushner - 313116584
 * MMN14
 * Question 1
 */

import java.util.*;

public class GenericUtils {
    /** Returns the minimal value in a set, without modifying it. */
    public static <E extends Comparable<E>> E minValue(GenericSet<E> set) {
        Iterator<E> iter = set.iterator();

        // Start with the first item as minimal item.
        E minimalItem = iter.next();

        // Check every item
        while (iter.hasNext()) {
            E nextItem = iter.next();

            // This item is smaller than what we've previously found, update it
            if (nextItem.compareTo(minimalItem) < 0)
                minimalItem = nextItem;
        }

        return minimalItem;
    }
}
