package org.fs.comparator.comparator;

/**
 * Every preparing for compare should have at least one of three actions:
 * - Extract fields
 * - Filter fields
 * - Validate fields
 * This enum allows to determine in which order they should be applied
 */
public enum ComparatorPriority {
    EXTRACTOR(1),
    FILTER(2),
    VALIDATOR(3);
    private final int toNumber;

    ComparatorPriority(int toNumber) {
        this.toNumber = toNumber;
    }

    /**
     * Represents order
     * @return Num value of order
     */
    public int toNumber() {
        return toNumber;
    }
}
