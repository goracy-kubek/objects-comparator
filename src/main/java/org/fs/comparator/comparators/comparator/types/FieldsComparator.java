package org.fs.comparator.comparators.comparator.types;

import org.fs.comparator.comparators.comparator.types.container.RecordFieldContainer;
import org.fs.comparator.comparators.comparator.types.container.RecordFieldsContainer;
import org.fs.comparator.comparators.util.ExtractorUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Basic comparator class
 */
public abstract class FieldsComparator {

    /**
     * Compare objects by fields
     *
     * @param leftRecord  Left record with object and fields
     * @param rightRecord Right record with object and fields
     * @return Result of comparing
     */
    protected boolean fieldsCompare(
            RecordFieldsContainer leftRecord,
            RecordFieldsContainer rightRecord
    ) {
        var rf = rightRecord
                .fields()
                .stream()
                .collect(Collectors.toMap(Field::getName, Function.identity()));

        for (Field leftField : leftRecord.fields()) {
            String leftFieldName = leftField.getName();
            Object leftValue = ExtractorUtils.extractValue(leftField, leftRecord.object());

            if (!rf.containsKey(leftFieldName)) {
                continue;
            }

            Object rightValue = ExtractorUtils.extractValue(rf.get(leftFieldName), rightRecord.object());

            if (!leftValue.equals(rightValue)) {
                return false;
            }
        }

        return true;
    }

    protected boolean fieldsCompare(
            RecordFieldContainer leftRecord,
            RecordFieldContainer rightRecord
    ) {
        Object lv = ExtractorUtils.extractValue(leftRecord.field(), leftRecord.object());
        Object rv = ExtractorUtils.extractValue(rightRecord.field(), rightRecord.object());

        return lv.equals(rv);
    }
}