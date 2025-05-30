package org.fs.comparator.util;

import org.fs.comparator.container.RecordFieldContainer;
import org.fs.comparator.container.RecordFieldsContainer;

import java.lang.reflect.Field;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Basic comparator class
 */
public class FieldsComparatorUtils {

    /**
     * Compare objects by fields
     *
     * @param leftRecord  Left record with comparableObject and fields
     * @param rightRecord Right record with comparableObject and fields
     * @return Result of comparing
     */
    public static boolean fieldsCompare(
            RecordFieldsContainer leftRecord,
            RecordFieldsContainer rightRecord
    ) {
        var rf = rightRecord
                .fields()
                .stream()
                .collect(Collectors.toMap(Field::getName, Function.identity()));

        for (Field leftField : leftRecord.fields()) {
            String leftFieldName = leftField.getName();
            Object leftValue = ExtractorUtils.extractValue(leftField, leftRecord.comparableObjectWrapper());

            if (!rf.containsKey(leftFieldName)) {
                continue;
            }

            Object rightValue = ExtractorUtils.extractValue(rf.get(leftFieldName), rightRecord.comparableObjectWrapper());

            if (!leftValue.equals(rightValue)) {
                return false;
            }
        }

        return true;
    }

    public static boolean fieldsCompare(
            RecordFieldContainer leftRecord,
            RecordFieldContainer rightRecord
    ) {
        Object lv = ExtractorUtils.extractValue(leftRecord.field(), leftRecord.object());
        Object rv = ExtractorUtils.extractValue(rightRecord.field(), rightRecord.object());

        return lv.equals(rv);
    }
}