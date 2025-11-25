package org.fs.comparator.view;

@FunctionalInterface
public interface Comparable {
    /**
     * Execute comparing
     *
     * @return Result of comparing
     */
    boolean compare();
}
