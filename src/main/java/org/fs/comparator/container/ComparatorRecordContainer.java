package org.fs.comparator.container;

/**
 * Contains two fields that needs to compare
 * @param left Left field to compare
 * @param right Right field to compare
 */
public record ComparatorRecordContainer(RecordFieldContainer left, RecordFieldContainer right) {
    /**
     * Are the fields of the same type or not
     */
    public boolean hasTheSameType() {
        return left.field().getType().equals(right.field().getType());
    }

    /**
     * Are the fields equal or not
     */
    public boolean areEquals() {
        return left.extractValue().equals(right.extractValue());
    }

    public boolean areEqualsIgnoreType() {
        var lv = left.extractValue();
        var rv = right.extractValue();

        if(hasTheSameType()) {
            return areEquals();
        }

        if(lv instanceof Number lvn && rv instanceof String rvs) {
            return ("" + lvn).equals(rvs);
        }

        if(lv instanceof String lvn && rv instanceof Number rvs) {
            return lvn.equals(rvs + "");
        }

        return false;
    }
}
