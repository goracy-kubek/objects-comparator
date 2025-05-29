package org.fs.comparator.comparator.strategies.validator;

import org.fs.comparator.comparator.ComparatorPriority;
import org.fs.comparator.comparator.strategies.ComparatorStrategy;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;

public class ValidateDifferentTypeStrategy implements ComparatorStrategy {
    @Override
    public ComparatorPriority getPriority() {
        return ComparatorPriority.VALIDATOR;
    }

    @Override
    public void apply(LeftObject left, RightObject right) {

    }
}
