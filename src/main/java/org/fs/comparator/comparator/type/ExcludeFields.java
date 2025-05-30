package org.fs.comparator.comparator.type;

import org.fs.comparator.comparator.Terminable;
import org.fs.comparator.comparator.processors.filter.FilterExcludeFields;
import org.fs.comparator.exception.ComparatorSettingsException;
import org.fs.comparator.container.ConditionProcessor;

import java.util.*;

/**
 * Compare fields excludes some of them
 */
public final class ExcludeFields implements Terminable {
    private final ConditionProcessor conditionProcessor;

    public ExcludeFields(ConditionProcessor conditionProcessor, String... fields) {
        if(fields == null || fields.length == 0 || Arrays.stream(fields).anyMatch(String::isBlank)) {
            throw new ComparatorSettingsException("Exclude fields must not be null, empty, or blank");
        }

        conditionProcessor.addStrategy(new FilterExcludeFields(fields));
        this.conditionProcessor = conditionProcessor;
    }

    @Override
    public boolean compare() {
        return conditionProcessor.compare();
    }
}
