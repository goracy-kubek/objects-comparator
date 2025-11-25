package org.fs.comparator.view;

import org.fs.comparator.container.ComparableField;
import org.fs.comparator.container.LeftObject;
import org.fs.comparator.container.RightObject;
import org.fs.comparator.exception.ValidationException;
import org.fs.comparator.util.CompareUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Allows to compare only specific fields
 */
public class OnlyComparator extends DefaultComparator {
    private final Set<String> onlyFields;

    public OnlyComparator(LeftObject left, RightObject right, String[] onlyFields) {
        super(left, right);
        validate(onlyFields);

        this.onlyFields = Set.of(onlyFields);
    }

    private static void validate(String[] fields) {
        if(Arrays.stream(fields).anyMatch(field -> Objects.isNull(field) || field.isBlank())) {
            throw new ValidationException("Field cannot be empty or blank");
        }
    }

    @Override
    public boolean compare() {
        return CompareUtils.compareFields(getFields(), left, right);
    }

    private Set<ComparableField> getFields() {
        return onlyFields.stream()
                .map(ComparableField::new)
                .collect(Collectors.toUnmodifiableSet());
    }
}
