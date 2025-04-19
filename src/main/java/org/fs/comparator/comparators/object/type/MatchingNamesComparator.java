package org.fs.comparator.comparators.object.type;

import org.fs.comparator.comparators.object.Terminatable;
import org.fs.comparator.comparators.object.type.matching.ExcludeFieldsComparator;
import org.fs.comparator.comparators.object.type.matching.OnlyFieldsComparator;

public class MatchingNamesComparator implements Terminatable {
    private final Object left;
    private final Object right;

    public MatchingNamesComparator(Object left, Object right) {
        this.left = left;
        this.right = right;
    }

    public OnlyFieldsComparator onlyFields(String... fields) {
        return new OnlyFieldsComparator(left, right, fields);
    }

    public ExcludeFieldsComparator excludeFields(String... fields) {
        return new ExcludeFieldsComparator(left, right, fields);
    }

    @Override
    public boolean compare() {
        return new ExcludeFieldsComparator(left, right).compare();
    }
}
