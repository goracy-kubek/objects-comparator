package org.fs.comparator.comparator.processors.comparator;

import org.fs.comparator.container.ComparatorRecordContainer;

import java.util.Collection;

public class DefaultComparator implements Comparator {
    @Override
    public boolean compare(Collection<ComparatorRecordContainer> containers) {
        for (var container : containers) {
            if(!container.areEquals()) {
                return false;
            }
        }

        return true;
    }
}
