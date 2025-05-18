package org.fs.comparator;

import org.fs.comparator.comparators.comparator.ObjectsComparator;

/**
 * Main class for comparator
 */
public class ComparatorUtils {

    /**
     * Compare two objects
     * @param left Left object for compare
     * @param right Right object for compare
     * @return Standard object comparator
     */
    public static ObjectsComparator compareObjects(Object left, Object right) {
        return new ObjectsComparator(left, right);
    }
}