package org.fs.comparator.comparator.processors.extractor;

import org.fs.comparator.comparator.ComparatorPriority;
import org.fs.comparator.comparator.processors.ProcessorStrategy;
import org.fs.comparator.container.RecordFieldContainer;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;

public class ExtractByName implements ProcessorStrategy {
    @Override
    public ComparatorPriority getPriority() {
        return ComparatorPriority.EXTRACTOR;
    }

    @Override
    public void apply(LeftObject left, RightObject right) {
        for(var field : left.getObject().getClass().getDeclaredFields()) {
            left.getFields().put(field.getName(), new RecordFieldContainer(field, left.getObject()));
        }

        for(var field : right.getObject().getClass().getDeclaredFields()) {
            right.getFields().put(field.getName(), new RecordFieldContainer(field, right.getObject()));
        }
    }
}
