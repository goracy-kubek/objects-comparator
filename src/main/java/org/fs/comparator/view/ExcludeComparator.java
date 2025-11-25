package org.fs.comparator.view;

import org.fs.comparator.container.ComparableField;
import org.fs.comparator.container.LeftObject;
import org.fs.comparator.container.RightObject;
import org.fs.comparator.exception.ValidationException;
import org.fs.comparator.util.CompareUtils;
import org.fs.comparator.util.FieldUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Allows to exclude fields from comparing process
 */
public class ExcludeComparator extends DefaultComparator {
    private final Set<String> excludeFields;

    public ExcludeComparator(LeftObject left, RightObject right, String[] excludeFields) {
        super(left, right);
        validate(excludeFields);

        this.excludeFields = Set.of(excludeFields);
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
        return FieldUtils.getCommonFieldNames(left, right)
                .stream()
                .filter(e -> !excludeFields.contains(e))
                .map(ComparableField::new)
                .collect(Collectors.toUnmodifiableSet());
    }
}
