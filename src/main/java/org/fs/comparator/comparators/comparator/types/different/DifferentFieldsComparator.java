package org.fs.comparator.comparators.comparator.types.different;

import org.fs.comparator.comparators.comparator.types.matching.OnlyFieldsComparator;

/**
 *
 */
final public class DifferentFieldsComparator {
    private final Object left;
    private final Object right;

    public DifferentFieldsComparator(Object left, Object right) {
        this.left = left;
        this.right = right;
    }

    public CompareDifferentFields fieldsForCompare(String... fields) {
        return new CompareDifferentFields(left, right, fields);
    }
}