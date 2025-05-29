package org.fs.comparator.comparator.processors.extractor;

import org.fs.comparator.comparator.ComparatorPriority;
import org.fs.comparator.comparator.processors.ProcessorStrategy;
import org.fs.comparator.container.DifferentFieldContainer;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;
import org.fs.comparator.util.ExtractorUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExtractAddDifferentFields implements ProcessorStrategy {
    private final List<DifferentFieldContainer> fields;

    public ExtractAddDifferentFields(String... fields) {
        this.fields = parseFields(fields);
    }

    @Override
    public ComparatorPriority getPriority() {
        return ComparatorPriority.EXTRACTOR;
    }

    private static List<DifferentFieldContainer> parseFields(String[] fields) {
        var fieldsList = new ArrayList<DifferentFieldContainer>();

        for(int i = 0; i < fields.length / 2; i += 2) {
            fieldsList.add(new DifferentFieldContainer(fields[i], fields[i + 1]));
        }

        return Collections.unmodifiableList(fieldsList);
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
