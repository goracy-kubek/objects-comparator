package org.fs.comparator;

import org.fs.comparator.comparators.object.ObjectsComparator;

public class ComparatorUtils {
    public static ObjectsComparator compareObjects(Object left, Object right) {
        return new ObjectsComparator(left, right);
    }
}