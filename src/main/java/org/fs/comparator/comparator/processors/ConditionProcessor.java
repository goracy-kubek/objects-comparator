package org.fs.comparator.comparator.processors;

import org.fs.comparator.comparator.processors.comparator.Comparator;
import org.fs.comparator.comparator.processors.comparator.DefaultComparator;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;
import org.fs.comparator.util.TwoObjectsUtils;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Main class that store comparing strategies and start process it
 */
public class ConditionProcessor {
    private final LeftObject left;
    private final RightObject right;
    private final Queue<ProcessorStrategy> strategies = new PriorityBlockingQueue<>();
    private Comparator comparator = new DefaultComparator();

    public ConditionProcessor(LeftObject left, RightObject right) {
        this.left = left;
        this.right = right;
    }

    public LeftObject getLeft() {
        return left;
    }

    public RightObject getRight() {
        return right;
    }

    /**
     * Add strategy to queue
     * @param strategy Strategy to add
     */
    public void addStrategy(ProcessorStrategy strategy) {
        strategies.add(strategy);
    }

    /**
     * Replace comparator
     */
    public synchronized void addComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    /**
     * Main method that start comparing
     * @return Comparing result
     */
    public boolean compare() {
        applyStrategies();

        return comparator.compare(TwoObjectsUtils.extractFieldsForCompare(left, right));
    }

    private void applyStrategies() {
        while (!strategies.isEmpty()) {
            strategies.poll().apply(left, right);
        }
    }
}
