package org.fs.comparator.view;

import org.fs.comparator.container.ComparableField;
import org.fs.comparator.container.LeftObject;
import org.fs.comparator.container.RightObject;
import org.fs.comparator.exception.ValidationException;
import org.fs.comparator.util.CompareUtils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddComparator {
    private final LeftObject left;
    private final RightObject right;
    private final DefaultComparator defaultComparator;
    private final Set<ComparableField> moreFields;

    public AddComparator(LeftObject left, RightObject right, DefaultComparator defaultComparator, String[] moreFields) {
        if(moreFields.length % 2 != 0) {
            throw new ValidationException("You can add only even amount of fields");
        }

        this.left = left;
        this.right = right;
        this.defaultComparator = defaultComparator;
        this.moreFields = convertToComparable(moreFields);
    }

    private static Set<ComparableField> convertToComparable(String[] moreFields) {
        return IntStream.iterate(0, i -> i < moreFields.length, i -> i + 2)
                .mapToObj(i -> new ComparableField(moreFields[i], moreFields[i + 1]))
                .collect(Collectors.toUnmodifiableSet());
    }

    public boolean compare() {
        return defaultComparator.compare() && CompareUtils.compareFields(moreFields, left, right);
    }
}
