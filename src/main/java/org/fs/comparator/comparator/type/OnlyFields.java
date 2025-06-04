package org.fs.comparator.comparator.type;

import org.fs.comparator.comparator.Terminable;
import org.fs.comparator.comparator.processors.filter.FilterOnlyFields;
import org.fs.comparator.comparator.processors.ConditionProcessor;
import org.fs.comparator.exception.ComparatorSettingsException;

import java.util.*;

/**
 * Compares only fields in settings
 */
public final class OnlyFields implements Terminable {
    private final ConditionProcessor conditionProcessor;

    public OnlyFields(ConditionProcessor conditionProcessor, String... fields) {
        if(fields == null || fields.length == 0 || Arrays.stream(fields).anyMatch(String::isBlank)) {
            throw new ComparatorSettingsException("Only fields must not be null, empty, or blank");
        }

        conditionProcessor.addStrategy(new FilterOnlyFields(fields));
        this.conditionProcessor = conditionProcessor;
    }


    @Override
    public boolean compare() {
        return conditionProcessor.compare();
    }
}
