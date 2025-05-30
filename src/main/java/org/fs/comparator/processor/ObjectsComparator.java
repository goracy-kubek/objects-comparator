package org.fs.comparator.processor;

import org.fs.comparator.comparator.Terminable;
import org.fs.comparator.comparator.type.OnlyDifferentFieldsToCompare;
import org.fs.comparator.container.ConditionProcessor;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;
import org.fs.comparator.processor.name.ComparatorByName;

final public class ObjectsComparator implements Terminable {
    private final ConditionProcessor conditionProcessor;

    public ObjectsComparator(Object left, Object right) {
        if(left == null || right == null) {
            throw new IllegalArgumentException("Objects mustn't be null");
        }

        this.conditionProcessor = new ConditionProcessor(new LeftObject(left), new RightObject(right));
    }

    public OnlyDifferentFieldsToCompare compareByDifferentNames(String... fields) {
        return new OnlyDifferentFieldsToCompare(conditionProcessor, fields);
    }

    public ComparatorByName compareByEqualsNames() {
        return new ComparatorByName(conditionProcessor);
    }

    @Override
    public boolean compare() {
        return conditionProcessor.compare();
    }
}
