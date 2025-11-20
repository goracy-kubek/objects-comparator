package org.fs.comparator.container;

import org.fs.comparator.util.FieldUtils;

import java.lang.reflect.Field;
import java.util.*;

public abstract class ComparableObject {
    private final Object object;

    protected ComparableObject(Object object) {
        if(object == null) {
            throw new IllegalArgumentException("Objects mustn't be null");
        }

        this.object = object;
    }

    public Set<String> getThisClassFieldsNames() {
        return FieldUtils.getFieldsNamesForObject(object);
    }

    public Object getObject() {
        return object;
    }

    public Field getFieldByName(String name) {
        return FieldUtils.getField(name, object);
    }

    public Object getFieldValue(String fieldName) {
        return FieldUtils.getFieldValue(getFieldByName(fieldName), object);
    }
}
