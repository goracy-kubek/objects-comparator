package org.fs.comparator.comparator.processors.filter;

import org.fs.comparator.comparator.ComparatorPriority;
import org.fs.comparator.comparator.processors.ProcessorStrategy;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;

import java.util.Arrays;
import java.util.stream.Stream;

public class FilterOnlyFields implements ProcessorStrategy {
    private final String[] fields;

    public FilterOnlyFields(String... fields) {
        this.fields = fields;
    }

    @Override
    public ComparatorPriority getPriority() {
        return ComparatorPriority.FILTER;
    }

    @Override
    public void apply(LeftObject left, RightObject right) {
        var fieldsToSave = Arrays.stream(fields).toList();
        var leftToRemove = Stream.concat(left.getFields().keySet().stream(), left.getFieldMapper().keySet().stream())
                .filter(e -> !fieldsToSave.contains(e))
                .toList();

        var rightToRemove = Stream.concat(right.getFields().keySet().stream(), right.getFieldMapper().keySet().stream())
                .filter(e -> !fieldsToSave.contains(e))
                .toList();

        for (String field : leftToRemove) {
            left.getFields().remove(field);
            left.getFieldMapper().remove(field);
        }

        for (String field : rightToRemove) {
            right.getFields().remove(field);
            right.getFieldMapper().remove(field);
        }
    }
}
