package org.fs.comparator.comparator.processors.validator;

import org.fs.comparator.comparator.ComparatorPriority;
import org.fs.comparator.comparator.processors.ProcessorStrategy;
import org.fs.comparator.container.ComparatorRecordContainer;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;
import org.fs.comparator.exception.ValidationException;
import org.fs.comparator.util.TwoObjectsUtils;

public class ValidateRejectDifferentTypes implements ProcessorStrategy {
    @Override
    public ComparatorPriority getPriority() {
        return ComparatorPriority.VALIDATOR;
    }

    @Override
    public void apply(LeftObject left, RightObject right) {
        var comparatorRecordContainers = TwoObjectsUtils.extractFieldsForCompare(left, right);

        for (ComparatorRecordContainer comparatorRecordContainer : comparatorRecordContainers) {
            if(!comparatorRecordContainer.hasTheSameType()) {
                throw new ValidationException(String.format(
                        "Field %s and %s. Don't have same type",
                        comparatorRecordContainer.left().field().getName(),
                        comparatorRecordContainer.right().field().getName())
                );
            }
        }
    }
}
