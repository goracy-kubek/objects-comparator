package org.fs.comparator.comparator.type;

import org.fs.comparator.comparator.Terminable;
import org.fs.comparator.comparator.strategies.filter.FilterExcludeFields;
import org.fs.comparator.util.FieldsComparatorUtils;
import org.fs.comparator.container.RecordFieldsContainer;
import org.fs.comparator.exception.ComparatorSettingsException;
import org.fs.comparator.util.ExtractorUtils;
import org.fs.comparator.container.ConditionContainer;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Compare fields excludes some of them
 */
public final class ExcludeFieldsComparator implements Terminable {
    private final ConditionContainer conditionContainer;

    public ExcludeFieldsComparator(ConditionContainer conditionContainer, String... fields) {
        if(fields == null || fields.length == 0 || Arrays.stream(fields).anyMatch(String::isBlank)) {
            throw new ComparatorSettingsException("Exclude fields must not be null, empty, or blank");
        }

        conditionContainer.addStrategy(new FilterExcludeFields(fields));
        this.conditionContainer = conditionContainer;
    }

    @Override
    public boolean compare() {
        return conditionContainer.compare();
    }
}
