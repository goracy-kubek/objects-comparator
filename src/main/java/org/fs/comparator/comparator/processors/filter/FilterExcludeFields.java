package org.fs.comparator.comparator.processors.filter;

import org.fs.comparator.comparator.ComparatorPriority;
import org.fs.comparator.comparator.processors.ProcessorStrategy;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;

public class FilterExcludeFields implements ProcessorStrategy {
    private final String[] fields;

    public FilterExcludeFields(String... fields) {
        this.fields = fields;
    }

    @Override
    public ComparatorPriority getPriority() {
        return ComparatorPriority.FILTER;
    }

    @Override
    public void apply(LeftObject left, RightObject right) {
        for(var field : fields) {
            left.getFields().remove(field);
            right.getFields().remove(field);
        }
    }
}
