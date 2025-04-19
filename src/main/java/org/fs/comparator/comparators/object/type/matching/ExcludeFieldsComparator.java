package org.fs.comparator.comparators.object.type.matching;

import org.fs.comparator.comparators.object.Terminatable;
import org.fs.comparator.comparators.util.ExtractorUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;

public class ExcludeFieldsComparator extends FieldsComparator implements Terminatable {
    private final Object left;
    private final Object right;

    private final Set<String> excludeFields;

    public ExcludeFieldsComparator(Object left, Object right, String... fields) {
        this.left = Objects.requireNonNull(left, "Left object cannot be null");
        this.right = Objects.requireNonNull(right, "Right object cannot be null");
        this.excludeFields = fields == null
                ? Collections.emptySet()
                : new LinkedHashSet<>(Arrays.asList(fields));
    }

    public ExcludeFieldsComparator excludeFields(String... fields) {
        if (fields == null || fields.length == 0) {
            return this;
        }

        return new ExcludeFieldsComparator(
                left,
                right,
                Stream.concat(excludeFields.stream(), Stream.of(fields)).toArray(String[]::new)
        );
    }

    @Override
    public boolean compare() {
        List<Field> lf = ExtractorUtils.extractFieldsExclude(left, excludeFields);
        List<Field> rf = ExtractorUtils.extractFieldsExclude(right, excludeFields);

        return super.fieldsCompare(lf, rf, left, right);
    }
}
