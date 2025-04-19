package org.fs.comparator.comparators.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

    public static List<Field> extractFields(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields()).toList();
    }

    public static List<Field> extractFieldsExclude(Object object, Set<String> exclude) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(e -> !exclude.contains(e.getName()))
                .toList();
    }

    public static List<Field> extractFieldsOnly(Object object, Set<String> only) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(e -> only.contains(e.getName()))
                .toList();
    }
}
