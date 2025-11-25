package org.fs.comparator.util;

import org.fs.comparator.container.LeftObject;
import org.fs.comparator.container.RightObject;
import org.fs.comparator.exception.FieldNotFoundException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Helps compare extract fields or it's values
 */
public class FieldUtils {
    /**
     * Fetch field names which have both {@link LeftObject} and {@link RightObject}
     * @param left Left object
     * @param right Right object
     * @return Set of common field names
     */
    public static Set<String> getCommonFieldNames(LeftObject left, RightObject right) {
        return left.getThisClassFieldsNames().stream()
                .filter(right.getThisClassFieldsNames()::contains)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Retrieves all declared field names from the object's class and all its superclasses
     * @param object Object with fields
     * @return Set of field names
     */
    public static Set<String> getFieldsNamesForObject(Object object) {
        return getFieldsForObject(object).stream()
                .map(Field::getName)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Retrieves all declared fields from the object's class and all its superclasses
     * @param object Object with fields
     * @return Set of fields
     */
    public static Set<Field> getFieldsForObject(Object object) {
        return getClassesFromHierarchy(object.getClass())
                .flatMap(c -> Arrays.stream(c.getDeclaredFields()))
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Extracts nested field values from an object using dot notation.
     * Supports accessing fields at any depth by chaining field names with dots.
     * <p>
     * Example: "user.profile.address.street" will return the street value
     * from the nested address object inside user's profile.
     *
     * @param fieldPath the field path in dot notation (e.g., "user.name", "account.balance.currency")
     * @param object the source object to extract the value from
     * @return the field value, or null if any intermediate field in the path is null
     * @throws FieldNotFoundException if the field path is invalid or field doesn't exist
     */
    public static Object getFieldValueFromTheDeep(String fieldPath, Object object) {
        String[] fields = fieldPath.split("\\.");
        Object o = object;

        for (String field : fields) {
            if(o == null) {
                return null;
            }

            Optional<Field> thisObjectField = getThisObjectField(field, o);

            if(thisObjectField.isEmpty()) {
                throw new FieldNotFoundException(fieldPath);
            }

            o = getFieldValue(thisObjectField.get(), o);
        }

        return o;
    }

    private static Optional<Field> getFieldOptional(String fieldName, Class<?> clazz) {
        try {
            return Optional.of(clazz.getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            return Optional.empty();
        }
    }

    private static Optional<Field> getThisObjectField(String fieldName, Object object) {
        return getClassesFromHierarchy(object.getClass())
                .map(c -> getFieldOptional(fieldName, c))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    private static Object getFieldValue(Field field, Object object) {
        try {
            if(!field.canAccess(object)) {
                field.setAccessible(true);
            }

            return field.get(object);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    private static Stream<Class<?>> getClassesFromHierarchy(Class<?> clazz) {
        return Stream.iterate(clazz, isNotSimpleObject(), Class::getSuperclass);
    }

    private static Predicate<Class<?>> isNotSimpleObject() {
        return superclass -> superclass != null && superclass != Object.class;
    }
}
