package org.fs.comparator.view;

import org.fs.comparator.container.ComparableField;
import org.fs.comparator.container.LeftObject;
import org.fs.comparator.container.RightObject;
import org.fs.comparator.util.CompareUtils;
import org.fs.comparator.util.FieldUtils;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Main comparator which compare fields with same names
 */
public class DefaultComparator implements Comparable {
    protected final LeftObject left;
    protected final RightObject right;

    public DefaultComparator(LeftObject left, RightObject right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Add extra fields with different names to compare
     * Allows to add fields from nested classes by using dots
     * <p>
     * Example: "user.profile.address.street" will return the street value
     * from the nested address object inside user's profile.
     * <p>
     * Can have only even amount of fields. Odd field for LeftObject, even field for RightObject
     *
     * @param fields Even numbers of field names, comparing one to another
     */
    public AddComparator moreFields(String... fields) {
        return new AddComparator(left, right, this, fields);
    }

    /**
     * Excludes specified fields from the comparison process
     *
     * @param fields Fields to exclude
     */
    public ExcludeComparator excludeFields(String... fields) {
        return new ExcludeComparator(left, right, fields);
    }

    /**
     * Compare only specific fields in objects
     *
     * @param fields Fields to compare
     */
    public OnlyComparator onlyFields(String... fields) {
        return new OnlyComparator(left, right, fields);
    }

    @Override
    public boolean compare() {
        return CompareUtils.compareFields(getFields(), left, right);
    }

    private Set<ComparableField> getFields() {
        return FieldUtils.getCommonFieldNames(left, right)
                .stream()
                .map(ComparableField::new)
                .collect(Collectors.toUnmodifiableSet());
    }
}
