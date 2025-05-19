package org.fs.comparator.comparators.comparator;

import org.fs.comparator.comparators.comparator.types.comparator.DefaultFieldsComparator;
import org.fs.comparator.comparators.comparator.types.comparator.DifferentFieldsComparator;
import org.fs.comparator.comparators.comparator.types.comparator.ExcludeFieldsComparator;
import org.fs.comparator.comparators.comparator.types.comparator.OnlyFieldsComparator;

final public class ObjectsComparator implements Terminatable {
    private final Object left;
    private final Object right;

    public ObjectsComparator(Object left, Object right) {
        if(left == null || right == null) {
            throw new IllegalArgumentException("Objects mustn't be null");
        }

        this.left = left;
        this.right = right;
    }

    public ExcludeFieldsComparator excludeFields(String... fields) {
        return new ExcludeFieldsComparator(left, right, fields);
    }

    public OnlyFieldsComparator onlyFields(String... fields) {
        return new OnlyFieldsComparator(left, right, fields);
    }

    public DifferentFieldsComparator differentFieldsCompare(String... fields) {
        return new DifferentFieldsComparator(left, right, fields);
    }

    @Override
    public boolean compare() {
        return new DefaultFieldsComparator(left, right).compare();
    }
}
