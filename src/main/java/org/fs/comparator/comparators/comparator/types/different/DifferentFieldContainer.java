package org.fs.comparator.comparators.comparator.types.different;

/**
 * Contains fields that will be used for comparing in {@link DifferentFieldsComparator}
 */
public class DifferentFieldContainer {
    private final String leftField;
    private final String rightField;

    public DifferentFieldContainer(String leftField, String rightField) {
        this.leftField = leftField;
        this.rightField = rightField;
    }
}
