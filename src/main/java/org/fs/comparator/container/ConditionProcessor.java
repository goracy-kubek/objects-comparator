package org.fs.comparator.container;

import org.fs.comparator.comparator.processors.ProcessorStrategy;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;

import java.util.Optional;
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

        for(var fli : left.getFields().entrySet()) {
            var leftFieldName = fli.getKey();
            var rightFieldName = left.getMappedField(leftFieldName).orElse(leftFieldName);

            Optional<RecordFieldContainer> leftFieldRecord = left.getFieldRecord(leftFieldName);
            Optional<RecordFieldContainer> rightFieldRecord = right.getFieldRecord(rightFieldName);

            if(leftFieldRecord.isEmpty() || rightFieldRecord.isEmpty()) {
                continue;
            }

            var leftRecord = leftFieldRecord.get();
            var rightRecord = rightFieldRecord.get();

            if(!leftRecord.extractValue().equals(rightRecord.extractValue())) {
                return false;
            }
        }

        return true;
    }
}
