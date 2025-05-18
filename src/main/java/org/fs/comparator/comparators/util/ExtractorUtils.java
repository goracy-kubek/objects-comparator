package org.fs.comparator.comparators.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utils for filter fields
 */
public class ExtractorUtils {
    public static Object extractValue(Field field, Object object) {
        try {
            if(!field.canAccess(object)) {
                field.setAccessible(true);
            }

            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Set<Field> extractFields(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .collect(Collectors.toSet());
    }

    public static Set<Field> extractFieldsExclude(Object object, Set<String> exclude) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(e -> !exclude.contains(e.getName()))
                .collect(Collectors.toSet());
    }

    public static Set<Field> extractFieldsOnly(Object object, Set<String> only) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(e -> only.contains(e.getName()))
                .collect(Collectors.toSet());
    }
}
