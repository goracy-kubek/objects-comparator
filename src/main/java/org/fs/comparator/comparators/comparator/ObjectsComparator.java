package org.fs.comparator.comparators.comparator;

import org.fs.comparator.comparators.comparator.types.different.DifferentFieldsComparator;
import org.fs.comparator.comparators.comparator.types.matching.DefaultFieldsComparator;
import org.fs.comparator.comparators.comparator.types.matching.MatchingFieldsComparator;

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

    public MatchingFieldsComparator compareMatchingNames() {
        return new MatchingFieldsComparator(left, right);
    }

    public DifferentFieldsComparator compareDifferentNames() {
        return new DifferentFieldsComparator(left, right);
    }

    @Override
    public boolean compare() {
        return new DefaultFieldsComparator(left, right).compare();
    }
}
