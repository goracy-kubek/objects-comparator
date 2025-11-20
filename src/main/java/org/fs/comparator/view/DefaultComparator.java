package org.fs.comparator.view;

import org.fs.comparator.container.ComparableField;
import org.fs.comparator.container.LeftObject;
import org.fs.comparator.container.RightObject;
import org.fs.comparator.util.CompareUtils;
import org.fs.comparator.util.ExtractorUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class DefaultComparator {
    protected final LeftObject left;
    protected final RightObject right;

    public DefaultComparator(LeftObject left, RightObject right) {
        this.left = left;
        this.right = right;
    }

    public AddComparator moreFields(String... fields) {
        return new AddComparator(left, right, this, fields);
    }

    public ExcludeComparator excludeFields(String... fields) {
        return new ExcludeComparator(left, right, fields);
    }

    public OnlyComparator onlyFields(String... fields) {
        return new OnlyComparator(left, right, fields);
    }

    public boolean compare() {
        return CompareUtils.compareFields(getFields(), left, right);
    }

    private Set<ComparableField> getFields() {
        return ExtractorUtils.getCommonFieldNames(left, right)
                .stream()
                .map(ComparableField::new)
                .collect(Collectors.toUnmodifiableSet());
    }
}
