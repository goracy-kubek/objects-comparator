package org.fs.comparator.comparators.object.type.matching;

import org.fs.comparator.comparators.object.Terminatable;
import org.fs.comparator.comparators.util.ExtractorUtils;

import java.util.*;
import java.util.stream.Stream;

public class OnlyFieldsComparator extends FieldsComparator implements Terminatable {
    private final Object left;
    private final Object right;

    private final Set<String> onlyFields;

    public OnlyFieldsComparator(Object left, Object right, String... fields) {
        this.left = Objects.requireNonNull(left, "Left object cannot be null");
        this.right = Objects.requireNonNull(right, "Right object cannot be null");
        this.onlyFields = fields == null
                ? Collections.emptySet()
                : new LinkedHashSet<>(Arrays.asList(fields));
    }

    public synchronized OnlyFieldsComparator onlyFields(String... fields) {
        if (fields == null || fields.length == 0) {
            return this;
        }

        return new OnlyFieldsComparator(
                left,
                right,
                Stream.concat(onlyFields.stream(), Stream.of(fields)).toArray(String[]::new)
        );
    }

    @Override
    public boolean compare() {
        var lf = ExtractorUtils.extractFieldsOnly(left, onlyFields);
        var rf = ExtractorUtils.extractFieldsOnly(right, onlyFields);

        return fieldsCompare(lf, rf, left, right);
    }
}
