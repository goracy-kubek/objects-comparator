package org.fs.comparator.comparator.processors.validator;

import org.fs.comparator.comparator.ComparatorPriority;
import org.fs.comparator.comparator.processors.ProcessorStrategy;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;

public class ValidateDifferentType implements ProcessorStrategy {
    @Override
    public ComparatorPriority getPriority() {
        return ComparatorPriority.VALIDATOR;
    }

    @Override
    public void apply(LeftObject left, RightObject right) {

    }
}
