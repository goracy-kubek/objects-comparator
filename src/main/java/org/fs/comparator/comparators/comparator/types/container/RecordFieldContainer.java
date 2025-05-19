package org.fs.comparator.comparators.comparator.types.container;

import java.lang.reflect.Field;

/**
 * Record that contains object and it's extracted field in one place
 */
public record RecordFieldContainer(Field field, Object object) {
}
