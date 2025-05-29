package org.fs.comparator.container;

import org.fs.comparator.comparator.processors.ProcessorStrategy;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Main class that store comparing strategies and start process it
 */
public class ConditionProcessor {
    private final LeftObject left;
    private final RightObject right;
    private final Queue<ProcessorStrategy> strategies = new PriorityBlockingQueue<>();

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
     * Main method that start comparing
     * @return Comparing result
     */
    public boolean compare() {
        while (!strategies.isEmpty()) {
            strategies.poll().apply(left, right);
        }

        var fls = left.getFields();
        var frs = right.getFields();

        for(var fli : fls.entrySet()) {
            var leftFieldName = fli.getKey();

            if(!right.getFieldMapper().containsKey(leftFieldName) && !frs.containsKey(fli.getKey())) {
                continue;
            }

            RecordFieldContainer fr = null;

            if(right.getFieldMapper().containsKey(leftFieldName)) {
                var rightFieldName = right.getFieldMapper().get(leftFieldName);

                if(!frs.containsKey(rightFieldName)) {
                    continue;
                }

                fr = frs.get(rightFieldName);
            } else {
                fr = frs.get(leftFieldName);
            }

            var fl = fli.getValue();

            if(!fl.extractValue().equals(fr.extractValue())) {
                return false;
            }
        }

        return true;
    }
}
