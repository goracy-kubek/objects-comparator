package org.fs.comparator.comparators.comparator.types.comparator;

import org.fs.comparator.comparators.comparator.Terminatable;
import org.fs.comparator.comparators.comparator.types.FieldsComparator;
import org.fs.comparator.comparators.comparator.types.container.RecordFieldContainer;
import org.fs.comparator.comparators.comparator.types.container.RecordFieldsContainer;
import org.fs.comparator.comparators.util.ExtractorUtils;

final public class DefaultFieldsComparator extends FieldsComparator implements Terminatable {
    private final Object left;
    private final Object right;

    public DefaultFieldsComparator(Object left, Object right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean compare() {
        var lt = ExtractorUtils.extractFields(left);
        var rt = ExtractorUtils.extractFields(right);

        return super.fieldsCompare(
            new RecordFieldsContainer(lt, left),
            new RecordFieldsContainer(rt, right)
        );
    }
}
