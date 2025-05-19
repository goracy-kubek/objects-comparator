package org.fs.comparator.comparators.comparator.types.comparator;

import org.fs.comparator.comparators.comparator.Terminatable;
import org.fs.comparator.comparators.comparator.types.container.DifferentFieldContainer;
import org.fs.comparator.comparators.comparator.types.FieldsComparator;
import org.fs.comparator.comparators.comparator.types.container.RecordFieldContainer;
import org.fs.comparator.comparators.exception.ComparatorSettingsException;
import org.fs.comparator.comparators.util.ExtractorUtils;

import java.util.*;

/**
 * Compare fields with not the save name
 */
public final class DifferentFieldsComparator extends FieldsComparator implements Terminatable {
    private final Object left;
    private final Object right;

    private final List<DifferentFieldContainer> onlyFields;

    public DifferentFieldsComparator(Object left, Object right, String... fields) {
        validateFields(fields);

        this.left = left;
        this.right = right;
        this.onlyFields = parseFields(fields);
    }

    private List<DifferentFieldContainer> parseFields(String[] fields) {
        var fieldsList = new ArrayList<DifferentFieldContainer>();

        for(int i = 0; i < fields.length / 2; i += 2) {
            fieldsList.add(new DifferentFieldContainer(fields[i], fields[i + 1]));
        }

        return Collections.unmodifiableList(fieldsList);
    }

    private static void validateFields(String[] fields) {
        Objects.requireNonNull(fields, "Fields mustn't be null");

        if(fields.length % 2 != 0) {
            throw new ComparatorSettingsException("Fields count must be even and above zero");
        }

        if(Arrays.stream(fields).anyMatch(String::isBlank)) {
            throw new ComparatorSettingsException("No fields must be blank");
        }
    }

    @Override
    public boolean compare() {
        for (DifferentFieldContainer fieldPair : onlyFields) {
            RecordFieldContainer lr = ExtractorUtils.extractFieldByPathRecursively(fieldPair.leftFieldPath(), left);
            RecordFieldContainer rr = ExtractorUtils.extractFieldByPathRecursively(fieldPair.rightFieldPath(), right);

            boolean result = super.fieldsCompare(lr, rr);

            if(!result) {
                return false;
            }
        }

        return true;
    }
}
