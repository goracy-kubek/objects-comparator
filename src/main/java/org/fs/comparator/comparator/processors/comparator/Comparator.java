package org.fs.comparator.comparator.processors.comparator;

import org.fs.comparator.container.ComparatorRecordContainer;

import java.util.Collection;

/**
 * Main interface for all comparators
 */
@FunctionalInterface
public interface Comparator {

    /**
     * Run compare process
     * @return Compare result
     */
    boolean compare(Collection<ComparatorRecordContainer> containers);
}
