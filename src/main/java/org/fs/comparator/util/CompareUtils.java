package org.fs.comparator.util;

import org.fs.comparator.container.ComparableField;
import org.fs.comparator.container.LeftObject;
import org.fs.comparator.container.RightObject;

import java.util.Set;

public class CompareUtils {
    public static boolean compareFields(Set<ComparableField> fields, LeftObject left, RightObject right) {
        return fields.stream()
                .allMatch(compare ->
                        left.getFieldValue(compare.getLeftField()).equals(right.getFieldValue(compare.getRightField()))
                );
    }
}
