package org.fs.comparator.container.object;

import org.fs.comparator.container.RecordFieldContainer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeftObject extends ComparableObject {
    private final Object object;
    private final Map<String, RecordFieldContainer> fields = new ConcurrentHashMap<>();

    public LeftObject(Object object) {
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
