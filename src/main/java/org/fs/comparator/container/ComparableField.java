package org.fs.comparator.container;

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

    public String getLeftField() {
        return leftField;
    }

    public String getRightField() {
        return rightField;
    }
}
