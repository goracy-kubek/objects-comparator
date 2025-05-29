package org.fs.comparator.comparator.strategies;

import org.fs.comparator.comparator.ComparatorPriority;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;

public interface ComparatorStrategy extends Comparable<ComparatorStrategy> {
    ComparatorPriority getPriority();
    void apply(LeftObject left, RightObject right);

    @Override
    default int compareTo(ComparatorStrategy o) {
        return getPriority().getPriority() - o.getPriority().getPriority();
    }
}
