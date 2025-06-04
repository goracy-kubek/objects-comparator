package org.fs.comparator.comparator.type;

import org.fs.comparator.comparator.Terminable;
import org.fs.comparator.comparator.processors.filter.ExtractorOnlyDifferentFields;
import org.fs.comparator.comparator.processors.ConditionProcessor;
import org.fs.comparator.exception.ComparatorSettingsException;

import java.util.Arrays;
import java.util.Objects;

final public class OnlyDifferentFieldsToCompare implements Terminable {
    private final ConditionProcessor conditionProcessor;

    public OnlyDifferentFieldsToCompare(ConditionProcessor conditionProcessor, String... fields) {
        validateFields(fields);

        conditionProcessor.addStrategy(new ExtractorOnlyDifferentFields(fields));
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
