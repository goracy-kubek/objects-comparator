package org.fs.comparator.container.object;

import org.fs.comparator.container.RecordFieldContainer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RightObject extends ComparableObject {
    private final Object object;
    private final Map<String, RecordFieldContainer> fields = new ConcurrentHashMap<>();

    public RightObject(Object object) {
        this.object = object;
    }

    @Override
    public Object getObject() {
        return object;
    }

    @Override
    public Map<String, RecordFieldContainer> getFields() {
        return fields;
    }
}
