package org.fs.comparator.comparators.object;

import org.fs.comparator.comparators.object.type.DifferentNamesComparator;
import org.fs.comparator.comparators.object.type.MatchingNamesComparator;

public class ObjectsComparator {
    private final Object left;
    private final Object right;

    public ObjectsComparator(Object left, Object right) {
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
