package org.fs.comparator.comparators.object.type;

import org.fs.comparator.comparators.object.Terminatable;

final public class DifferentNamesComparator implements Terminatable {
    private final Object left;
    private final Object right;

    public DifferentNamesComparator(Object left, Object right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean compare() {
        return false;
    }
}
