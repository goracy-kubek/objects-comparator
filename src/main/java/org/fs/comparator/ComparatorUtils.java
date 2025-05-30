package org.fs.comparator;

import org.fs.comparator.processor.ObjectsComparator;

/**
 * Main class for comparator
 */
public class ComparatorUtils {

    /**
     * Compare two objects
     * @param left Left comparableObject for compare
     * @param right Right comparableObject for compare
     * @return Standard comparableObject comparator
     */
    public static ObjectsComparator compareObjects(Object left, Object right) {
        return new ObjectsComparator(left, right);
    }
}