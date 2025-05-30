package org.fs.comparator.comparator;

/**
 * Mark class that can start comparing process
 */
@FunctionalInterface
public interface Terminable {
    /**
     * Initiate comparing process.
     * @return Result of comparing
     */
    boolean compare();
}
