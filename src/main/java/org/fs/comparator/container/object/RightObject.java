package org.fs.comparator.container.object;

import org.fs.comparator.container.RecordFieldContainer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Right comparable object wrapper
 */
public class RightObject extends ComparableObjectWrapper {
    private final Object object;
    private final Map<String, RecordFieldContainer> fields = new ConcurrentHashMap<>();
    private final Map<String, String> fieldMapper = new ConcurrentHashMap<>();

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

    @Override
    public void addFieldMapper(String left, String right) {
        this.fieldMapper.putAll(fieldMapper);
    }

    @Override
    public Map<String, String> getFieldMapper() {
        return fieldMapper;
    }
}
