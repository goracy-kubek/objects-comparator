package org.fs.comparator.comparators.comparator.types.matching;

import org.fs.comparator.comparators.util.ExtractorUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Basic comparator class
 */
public abstract class FieldsComparator {

    /**
     * Compare objects by fields
     * @param leftFields Left object fields for comparing
     * @param rightFields Right object fields for comparing
     * @param leftObject Left objets for comparing
     * @param rightObject Right objects for comparing
     * @return Result of comparing
     */
    protected boolean fieldsCompare(
            List<Field> leftFields,
            List<Field> rightFields,
            Object leftObject,
            Object rightObject
    ) {
        var rf = rightFields
                .stream()
                .collect(Collectors.toMap(Field::getName, Function.identity()));

        for (Field leftField : leftFields) {
            String leftFieldName = leftField.getName();
            Object leftValue = ExtractorUtils.extractValue(leftField, leftObject);

            if(!rf.containsKey(leftFieldName)) {
                continue;
            }

            Object rightValue = ExtractorUtils.extractValue(rf.get(leftFieldName), rightObject);

            if(!leftValue.equals(rightValue)) {
                return false;
            }
        }

        return true;
    }
}
