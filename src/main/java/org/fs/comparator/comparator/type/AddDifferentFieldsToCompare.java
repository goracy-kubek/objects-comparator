package org.fs.comparator.comparator.type;

import org.fs.comparator.comparator.Terminable;
import org.fs.comparator.comparator.processors.extractor.ExtractAddDifferentFields;
import org.fs.comparator.comparator.processors.ConditionProcessor;
import org.fs.comparator.exception.ComparatorSettingsException;

import java.util.*;

/**
 * Compare fields with not the save name
 */
public final class AddDifferentFieldsToCompare implements Terminable {
    private final ConditionProcessor conditionProcessor;

    public AddDifferentFieldsToCompare(ConditionProcessor conditionProcessor, String... fields) {
        validateFields(fields);

        conditionProcessor.addStrategy(new ExtractAddDifferentFields(fields));
        this.conditionProcessor = conditionProcessor;
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
        return conditionProcessor.compare();
    }
}
