package org.fs.comparator.comparator;

/**
 * Represents an comparableObject that can terminate a process.
 */
@FunctionalInterface
public interface Terminable {
    /**
     * Initiate comparing process.
     * @return Result of comparing
     */
    boolean compare();
}
