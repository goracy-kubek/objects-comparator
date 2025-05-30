package org.fs.comparator.container;

import java.lang.reflect.Field;

/**
 * Record that contains object and it's extracted field in one place
 */
public record RecordFieldContainer(Field field, Object object) {

    /**
     * Extract a value that field has
     * @return Field value
     */
    public Object extractValue() {
        field.setAccessible(true);

        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
