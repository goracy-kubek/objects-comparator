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
import java.util.stream.IntStream;

/**
 * Comparator which allow to add fields with different names to compare
 */
public class AddComparator implements Comparable {
    private final LeftObject left;
    private final RightObject right;
    private final DefaultComparator defaultComparator;
    private final Set<ComparableField> moreFields;

    public AddComparator(LeftObject left, RightObject right, DefaultComparator defaultComparator, String[] moreFields) {
        validate(moreFields);

        this.left = left;
        this.right = right;
        this.defaultComparator = defaultComparator;
        this.moreFields = convertToComparable(moreFields);
    }

    private static void validate(String[] fields) {
        if(fields.length % 2 != 0) {
            throw new ValidationException("You can add only even amount of fields");
        }

        if(Arrays.stream(fields).anyMatch(field -> Objects.isNull(field) || field.isBlank())) {
            throw new ValidationException("Field cannot be empty or blank");
        }
    }

    private static Set<ComparableField> convertToComparable(String[] moreFields) {
        return IntStream.iterate(0, i -> i < moreFields.length, i -> i + 2)
                .mapToObj(i -> new ComparableField(moreFields[i], moreFields[i + 1]))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public boolean compare() {
        return defaultComparator.compare() && CompareUtils.compareFields(moreFields, left, right);
    }
}
