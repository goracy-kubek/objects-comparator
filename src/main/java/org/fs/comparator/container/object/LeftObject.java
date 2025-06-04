package org.fs.comparator.container.object;

import org.fs.comparator.container.RecordFieldContainer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Left comparable object wrapper
 */
public class LeftObject extends ComparableObjectWrapper {
    private final Object object;
    private final Map<String, RecordFieldContainer> fields = new ConcurrentHashMap<>();
    private final Map<String, String> fieldMapper = new ConcurrentHashMap<>();

    public LeftObject(Object object) {
        this.object = object;
    }

    @Override
    public void addFieldMapper(String left, String right) {
        this.fieldMapper.put(left, right);
    }

    @Override
    public Map<String, String> getFieldMapper() {
        return fieldMapper;
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
