package org.fs.comparator.util;

import org.fs.comparator.exception.FieldNotFoundException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FieldUtils {
    public static Field getField(String fieldName, Object object) {
        return getThisObjectField(fieldName, object)
                .orElseThrow(() -> new FieldNotFoundException(fieldName));
    }

    public static Optional<Field> getFieldOptional(String fieldName, Class<?> clazz) {
        try {
            return Optional.of(clazz.getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            return Optional.empty();
        }
    }

    public static Optional<Field> getThisObjectField(String fieldName, Object object) {
        return getClassesFromHierarchy(object.getClass())
                .map(c -> getFieldOptional(fieldName, c))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    public static Set<String> getFieldsNamesForObject(Object object) {
        return getFieldsForObject(object).stream()
                .map(Field::getName)
                .collect(Collectors.toUnmodifiableSet());
    }

    public static Set<Field> getFieldsForObject(Object object) {
        return getClassesFromHierarchy(object.getClass())
                .flatMap(c -> Arrays.stream(c.getDeclaredFields()))
                .collect(Collectors.toUnmodifiableSet());
    }

    public static Object getFieldValue(Field field, Object object) {
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
