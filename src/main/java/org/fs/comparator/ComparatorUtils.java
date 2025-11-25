package org.fs.comparator;

import org.fs.comparator.container.LeftObject;
import org.fs.comparator.container.RightObject;
import org.fs.comparator.exception.ValidationException;
import org.fs.comparator.view.DefaultComparator;

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
    public static DefaultComparator compareObjects(Object left, Object right) {
        if(left == null) {
            throw new ValidationException("Left object cannot be null");
        }

        if(right == null) {
            throw new ValidationException("Right object cannot be null");
        }

        return new DefaultComparator(new LeftObject(left), new RightObject(right));
    }
}