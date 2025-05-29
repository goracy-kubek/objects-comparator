package org.fs.comparator.comparator.type;

import org.fs.comparator.comparator.Terminable;
import org.fs.comparator.container.ConditionContainer;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;
import org.fs.comparator.util.ExtractorUtils;
import org.fs.comparator.util.FieldsComparatorUtils;
import org.fs.comparator.container.DifferentFieldContainer;
import org.fs.comparator.container.RecordFieldContainer;
import org.fs.comparator.exception.ComparatorSettingsException;

import java.util.*;

/**
 * Compare fields with not the save name
 */
public final class DifferentFieldsComparator implements Terminable {
    private final ConditionContainer conditionContainer;
    private final List<DifferentFieldContainer> onlyFields;

    public DifferentFieldsComparator(ConditionContainer conditionContainer, String... fields) {
        validateFields(fields);

        this.conditionContainer = conditionContainer;
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
            LeftObject left = conditionContainer.getLeft();
            RightObject right = conditionContainer.getRight();

            RecordFieldContainer lr = ExtractorUtils.extractFieldByPathRecursively(fieldPair.leftFieldPath(), left);
            RecordFieldContainer rr = ExtractorUtils.extractFieldByPathRecursively(fieldPair.rightFieldPath(), right);

            boolean result = FieldsComparatorUtils.fieldsCompare(lr, rr);

            if(!result) {
                return false;
            }
        }

        return true;
    }
}
