package org.fs.comparator.processor;

import org.fs.comparator.comparator.Terminable;
import org.fs.comparator.comparator.strategies.extractor.ExtractByNameStrategy;
import org.fs.comparator.container.ConditionContainer;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;
import org.fs.comparator.processor.name.ComparatorByName;

final public class ObjectsComparator implements Terminable {
    private final ConditionContainer conditionContainer;

    public ObjectsComparator(Object left, Object right) {
        if(left == null || right == null) {
            throw new IllegalArgumentException("Objects mustn't be null");
        }

        this.conditionContainer = new ConditionContainer(new LeftObject(left), new RightObject(right));
    }

    public ComparatorByName compareByFieldNames() {
        conditionContainer.addStrategy(new ExtractByNameStrategy());

        return new ComparatorByName(conditionContainer);
    }

    @Override
    public boolean compare() {
        return conditionContainer.compare();
    }
}
