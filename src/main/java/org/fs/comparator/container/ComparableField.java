package org.fs.comparator.container;

import java.util.Objects;

/**
 * Contains fields names for comparison
 */
public class ComparableField {
    private final String leftField;
    private final String rightField;

    public ComparableField(String leftField, String rightField) {
        this.leftField = leftField;
        this.rightField = rightField;
    }

    public ComparableField(String field) {
        this.leftField = field;
        this.rightField = field;
    }

    public boolean compareObjects(LeftObject left, RightObject right) {
        var areEqual = Objects.equals(left.getFieldValue(leftField), right.getFieldValue(rightField));

        if(!areEqual) {
            System.out.printf("Field %s isn't equal to field %s", leftField, rightField);
            return false;
        }

        return true;
    }
}
