package org.fs.comparator.comparators.object.type.matching;

import org.fs.comparator.comparators.exception.ComparatorSettingsException;
import org.fs.comparator.comparators.object.Terminatable;
import org.fs.comparator.comparators.util.ExtractorUtils;

import java.util.*;

/**
 * Compares only fields in settings
 */
public class OnlyFieldsComparator extends FieldsComparator implements Terminatable {
    private final Object left;
    private final Object right;

    private final Set<String> onlyFields;

    public OnlyFieldsComparator(Object left, Object right, String... fields) {
        if(fields == null || fields.length == 0 || Arrays.stream(fields).anyMatch(String::isBlank)) {
            throw new ComparatorSettingsException("Only fields must not be null, empty, or blank");
        }

        this.left = left;
        this.right = right;
        this.onlyFields = Set.of(fields);
    }

    @Override
    public boolean compare() {
        var lf = ExtractorUtils.extractFieldsOnly(left, onlyFields);
        var rf = ExtractorUtils.extractFieldsOnly(right, onlyFields);

        return fieldsCompare(lf, rf, left, right);
    }
}
