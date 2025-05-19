package org.fs.comparator.comparators.comparator.types.container;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * Record that contains object and its fields
 * @param fields Object fields
 * @param object Objects with fields
 */
public record RecordFieldsContainer(Set<Field> fields, Object object) {
}
