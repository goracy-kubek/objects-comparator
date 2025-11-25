package org.fs.comparator.util;

import org.fs.comparator.container.ComparableField;
import org.fs.comparator.container.LeftObject;
import org.fs.comparator.container.RightObject;

import java.util.Set;

public class CompareUtils {

    /**
     * Compare fields for specific objects
     * @param fields fields of objects to compare
     * @param left Left object to compare
     * @param right Right object to compare
     * @return Comparison result
     */
    public static boolean compareFields(Set<ComparableField> fields, LeftObject left, RightObject right) {
        return fields.stream()
                .allMatch(compare -> compare.compareObjects(left, right));
    }
}
