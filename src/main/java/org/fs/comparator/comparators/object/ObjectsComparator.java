package org.fs.comparator.comparators.object;

import org.fs.comparator.comparators.object.type.MatchingNamesType;
import org.fs.comparator.comparators.object.type.ObjectsComparatorType;

import java.util.ArrayList;
import java.util.List;

public class ObjectsComparator implements Terminatable {
    private final Object left;
    private final Object right;

    private int compareLevel;
    private ObjectsComparatorType type = new MatchingNamesType();

    private List<String> excludeFields = new ArrayList<>();
    private List<String> onlyFields = new ArrayList<>();

    public ObjectsComparator(Object left, Object right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean compare() {
        return false;
    }
}
