package org.fs.comparator.comparators.comparator.types.different;

import org.fs.comparator.comparators.exception.ComparatorSettingsException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Compare fields with not the save name
 */
public class CompareDifferentFields {
    private final Object left;
    private final Object right;

    private final List<DifferentFieldContainer> onlyFields;

    public CompareDifferentFields(Object left, Object right, String... fields) {
        Objects.requireNonNull(fields, "Fields mustn't be null");

        if(fields.length > 0 && fields.length % 2 == 0) {
            throw new ComparatorSettingsException("Fields count must be even and above zero");
        }

        if(Arrays.stream(fields).anyMatch(String::isBlank)) {
            throw new ComparatorSettingsException("No fields must be blank");
        }

        this.left = left;
        this.right = right;


    }
}
