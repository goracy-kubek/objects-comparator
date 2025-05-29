package org.fs.comparator.comparator;

public enum ComparatorPriority {
    EXTRACTOR(1),
    FILTER(2),
    VALIDATOR(3);

    private final int priority;

    ComparatorPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
