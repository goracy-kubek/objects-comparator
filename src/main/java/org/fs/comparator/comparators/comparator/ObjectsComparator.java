package org.fs.comparator.comparators.comparator;

import org.fs.comparator.comparators.comparator.types.different.DifferentNamesComparator;
import org.fs.comparator.comparators.comparator.types.matching.MatchingNamesComparator;

final public class ObjectsComparator {
    private final Object left;
    private final Object right;

    public ObjectsComparator(Object left, Object right) {
        if(left == null || right == null) {
            throw new IllegalArgumentException("Objects mustn't be null");
        }

        this.left = left;
        this.right = right;
    }

    public MatchingNamesComparator compareMatchingNames() {
        return new MatchingNamesComparator(left, right);
    }

    public DifferentNamesComparator compareDifferentNames() {
        return new DifferentNamesComparator(left, right);
    }
}
