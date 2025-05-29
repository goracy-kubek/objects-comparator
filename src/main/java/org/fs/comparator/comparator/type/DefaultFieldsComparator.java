package org.fs.comparator.comparator.type;

import org.fs.comparator.comparator.Terminable;
import org.fs.comparator.container.RecordFieldsContainer;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;
import org.fs.comparator.util.ExtractorUtils;
import org.fs.comparator.util.FieldsComparatorUtils;
import org.fs.comparator.container.ConditionProcessor;

final public class DefaultFieldsComparator implements Terminable {
    private final ConditionProcessor conditionProcessor;

    public DefaultFieldsComparator(ConditionProcessor conditionProcessor) {
        this.conditionProcessor = conditionProcessor;
    }

    @Override
    public boolean compare() {
        LeftObject left = conditionProcessor.getLeft();
        RightObject right = conditionProcessor.getRight();

        var lf = ExtractorUtils.extractFields(left);
        var rf = ExtractorUtils.extractFields(right);

        return FieldsComparatorUtils.fieldsCompare(
                new RecordFieldsContainer(lf, left),
                new RecordFieldsContainer(rf, right)
        );
    }
}
