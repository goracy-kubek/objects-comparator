package org.fs.comparator.comparator.processors;

import org.fs.comparator.comparator.ComparatorPriority;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;

public interface ProcessorStrategy extends Comparable<ProcessorStrategy> {
    ComparatorPriority getPriority();
    void apply(LeftObject left, RightObject right);

    @Override
    default int compareTo(ProcessorStrategy o) {
        return getPriority().toNumber() - o.getPriority().toNumber();
    }
}
