package org.fs.comparator.comparators.object.type;

import org.fs.comparator.comparators.object.Terminatable;
import org.fs.comparator.comparators.object.type.matching.DefaultFieldsComparator;
import org.fs.comparator.comparators.object.type.matching.ExcludeFieldsComparator;
import org.fs.comparator.comparators.object.type.matching.OnlyFieldsComparator;

/**
 * Compare objects by its fields names
 */
final public class MatchingNamesComparator implements Terminatable {
    private final Object left;
    private final Object right;

    /**
     * Main comparator class
     * @param left Left object for comparing
     * @param right Right object for comparing
     */
    public MatchingNamesComparator(Object left, Object right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Select fields that will be compared.
     * @param fields Fields names.
     * @return Comparator for fields.
     */
    public OnlyFieldsComparator onlyFields(String... fields) {
        return new OnlyFieldsComparator(left, right, fields);
    }

    /**
     * Select fields that will be excluded.
     * @param fields Fields names.
     * @return Comparator for fields.
     */
    public ExcludeFieldsComparator excludeFields(String... fields) {
        return new ExcludeFieldsComparator(left, right, fields);
    }

    @Override
    public boolean compare() {
        return new DefaultFieldsComparator(left, right).compare();
    }
}
