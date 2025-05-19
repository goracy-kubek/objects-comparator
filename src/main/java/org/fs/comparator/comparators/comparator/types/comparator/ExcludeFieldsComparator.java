package org.fs.comparator.comparators.comparator.types.comparator;

import org.fs.comparator.comparators.comparator.types.FieldsComparator;
import org.fs.comparator.comparators.comparator.types.container.RecordFieldsContainer;
import org.fs.comparator.comparators.exception.ComparatorSettingsException;
import org.fs.comparator.comparators.comparator.Terminatable;
import org.fs.comparator.comparators.util.ExtractorUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Compare fields excludes some of them
 */
public final class ExcludeFieldsComparator extends FieldsComparator implements Terminatable {
    private final Object left;
    private final Object right;

    private final Set<String> excludeFields;

    public ExcludeFieldsComparator(Object left, Object right, String... fields) {
        if(fields == null || fields.length == 0 || Arrays.stream(fields).anyMatch(String::isBlank)) {
            throw new ComparatorSettingsException("Exclude fields must not be null, empty, or blank");
        }

        this.left = left;
        this.right = right;
        this.excludeFields = Set.of(fields);
    }

    @Override
    public boolean compare() {
        Set<Field> lf = ExtractorUtils.extractFieldsExclude(left, excludeFields);
        Set<Field> rf = ExtractorUtils.extractFieldsExclude(right, excludeFields);

        return super.fieldsCompare(
            new RecordFieldsContainer(lf, left),
            new RecordFieldsContainer(rf, right)
        );
    }
}
