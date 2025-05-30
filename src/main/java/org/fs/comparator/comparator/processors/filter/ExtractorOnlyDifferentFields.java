package org.fs.comparator.comparator.processors.filter;

import org.fs.comparator.comparator.ComparatorPriority;
import org.fs.comparator.comparator.processors.ProcessorStrategy;
import org.fs.comparator.container.DifferentFieldContainer;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;
import org.fs.comparator.util.ExtractorUtils;

import java.util.List;

public class ExtractorOnlyDifferentFields implements ProcessorStrategy {
    private final List<DifferentFieldContainer> fields;

    public ExtractorOnlyDifferentFields(String... fields) {
        this.fields = parseFields(fields);
    }

    @Override
    public ComparatorPriority getPriority() {
        return ComparatorPriority.EXTRACTOR;
    }

    @Override
    public void apply(LeftObject left, RightObject right) {
        for (DifferentFieldContainer field : fields) {
            var lc = ExtractorUtils.extractFieldByPathRecursively(field.leftFieldPath(), left.getObject());
            var rc = ExtractorUtils.extractFieldByPathRecursively(field.rightFieldPath(), right.getObject());

            left.getFields().put(field.leftFieldPath(), lc);
            right.getFields().put(field.rightFieldPath(), rc);

            left.addFieldMapper(field.leftFieldPath(), field.rightFieldPath());
            right.addFieldMapper(field.leftFieldPath(), field.rightFieldPath());
        }
    }
}
