package org.fs.comparator.container;

import org.fs.comparator.container.object.ComparableObjectWrapper;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * Record that contains comparableObject and its fields
 * @param fields Object fields
 * @param comparableObjectWrapper Objects with fields
 */
public record RecordFieldsContainer(Set<Field> fields, ComparableObjectWrapper comparableObjectWrapper) {
}
