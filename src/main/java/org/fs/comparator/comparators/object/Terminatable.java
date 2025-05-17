package org.fs.comparator.comparators.object;

/**
 * Represents an object that can terminate a process.
 */
@FunctionalInterface
public interface Terminatable {
    /**
     * Initiate comparing process.
     * @return Result of comparing
     */
    boolean compare();
}
