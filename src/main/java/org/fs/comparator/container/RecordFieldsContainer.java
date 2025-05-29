package org.fs.comparator.container;

import org.fs.comparator.container.object.ComparableObject;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * Record that contains comparableObject and its fields
 * @param fields Object fields
 * @param comparableObject Objects with fields
 */
public record RecordFieldsContainer(Set<Field> fields, ComparableObject comparableObject) {
}
