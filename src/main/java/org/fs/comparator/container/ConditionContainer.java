package org.fs.comparator.container;

import org.fs.comparator.comparator.Terminable;
import org.fs.comparator.comparator.strategies.ComparatorStrategy;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class ConditionContainer implements Terminable {
    private final LeftObject left;
    private final RightObject right;
    private final Queue<ComparatorStrategy> strategies = new PriorityBlockingQueue<>();

    public ConditionContainer(LeftObject left, RightObject right) {
        this.left = left;
        this.right = right;
    }

    public LeftObject getLeft() {
        return left;
    }

    public RightObject getRight() {
        return right;
    }

    public void addStrategy(ComparatorStrategy strategy) {
        strategies.add(strategy);
    }

    @Override
    public boolean compare() {
        while (!strategies.isEmpty()) {
            strategies.poll().apply(left, right);
        }

        var fls = left.getFields();
        var frs = right.getFields();

        for(var fl : fls.entrySet()) {
            if(!frs.containsKey(fl.getKey())) {
                continue;
            }

            var fr = frs.get(fl.getKey());

            if(!fl.getValue().extractValue().equals(fr.extractValue())) {
                return false;
            }
        }

        return true;
    }
}
