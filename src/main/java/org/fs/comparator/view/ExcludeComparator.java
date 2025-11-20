package org.fs.comparator.view;

import org.fs.comparator.container.ComparableField;
import org.fs.comparator.container.LeftObject;
import org.fs.comparator.container.RightObject;
import org.fs.comparator.util.CompareUtils;
import org.fs.comparator.util.ExtractorUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class ExcludeComparator extends DefaultComparator {
    private final Set<String> excludeFields;

    public ExcludeComparator(LeftObject left, RightObject right, String[] excludeFields) {
        super(left, right);
        this.excludeFields = Set.of(excludeFields);
    }

    public boolean compare() {
        return CompareUtils.compareFields(getFields(), left, right);
    }

    private Set<ComparableField> getFields() {
        return ExtractorUtils.getCommonFieldNames(left, right)
                .stream()
                .filter(e -> !excludeFields.contains(e))
                .map(ComparableField::new)
                .collect(Collectors.toUnmodifiableSet());
    }
}
