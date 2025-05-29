package org.fs.comparator.comparator.type;

import org.fs.comparator.comparator.strategies.filter.FilterOnlyFields;
import org.fs.comparator.container.ConditionContainer;
import org.fs.comparator.exception.ComparatorSettingsException;

import java.util.*;

/**
 * Compares only fields in settings
 */
public final class OnlyFieldsComparator {
    public OnlyFieldsComparator(ConditionContainer conditionContainer, String... fields) {
        if(fields == null || fields.length == 0 || Arrays.stream(fields).anyMatch(String::isBlank)) {
            throw new ComparatorSettingsException("Only fields must not be null, empty, or blank");
        }

        conditionContainer.addStrategy(new FilterOnlyFields(fields));
    }
}
