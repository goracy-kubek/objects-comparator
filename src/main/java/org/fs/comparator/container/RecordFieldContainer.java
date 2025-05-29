package org.fs.comparator.container;

import org.fs.comparator.container.object.ComparableObject;

import java.lang.reflect.Field;

/**
 * Record that contains object and it's extracted field in one place
 */
public record RecordFieldContainer(Field field, Object object) {
    public Object extractValue() {
        field.setAccessible(true);

        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
