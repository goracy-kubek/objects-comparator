package org.fs.comparator.comparator.strategies.filter;

import org.fs.comparator.comparator.ComparatorPriority;
import org.fs.comparator.comparator.strategies.ComparatorStrategy;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;

public class FilterDifferentFields implements ComparatorStrategy {
    @Override
    public ComparatorPriority getPriority() {
        return ComparatorPriority.FILTER;
    }

    @Override
    public void apply(LeftObject left, RightObject right) {

    }
}
