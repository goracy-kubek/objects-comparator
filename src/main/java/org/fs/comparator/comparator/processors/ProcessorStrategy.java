package org.fs.comparator.comparator.processors;

import org.fs.comparator.comparator.ComparatorPriority;
import org.fs.comparator.container.DifferentFieldContainer;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface ProcessorStrategy extends Comparable<ProcessorStrategy> {
    ComparatorPriority getPriority();
    void apply(LeftObject left, RightObject right);

    @Override
    default int compareTo(ProcessorStrategy o) {
        return getPriority().toNumber() - o.getPriority().toNumber();
    }

    default List<DifferentFieldContainer> parseFields(String[] fields) {
        var fieldsList = new ArrayList<DifferentFieldContainer>();

        for(int i = 0; i < fields.length / 2; i += 2) {
            fieldsList.add(new DifferentFieldContainer(fields[i], fields[i + 1]));
        }

        return Collections.unmodifiableList(fieldsList);
    }
}
