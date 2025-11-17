package org.fs.comparator.view;

import org.fs.comparator.container.LeftObject;
import org.fs.comparator.container.RightObject;

final public class ObjectsComparator {
    private final LeftObject left;
    private final RightObject right;

    public ObjectsComparator(Object left, Object right) {
        if(left == null || right == null) {
            throw new IllegalArgumentException("Objects mustn't be null");
        }

        this.left = new LeftObject(left);
        this.right = new RightObject(right);
    }
}
