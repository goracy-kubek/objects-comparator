package org.fs.comparator.container;

import org.fs.comparator.util.FieldUtils;

import java.util.*;

/**
 * Object comparer helper. Helps extract fields and separate {@link LeftObject} from {@link RightObject}
 */
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

    public Object getFieldValue(String fieldName) {
        return FieldUtils.getFieldValueFromTheDeep(fieldName, object);
    }
}
