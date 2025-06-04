package org.fs.comparator.comparator.processors.comparator;

import org.fs.comparator.container.ComparatorRecordContainer;

import java.util.Collection;

public class CompareDifferentTypes implements Comparator {
    @Override
    public boolean compare(Collection<ComparatorRecordContainer> containers) {

        for (var container : containers) {
            if(!container.areEqualsIgnoreType()) {
                return false;
            }
        }

        return true;
    }
}
