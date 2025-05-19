package org.fs.comparator.comparators.util;

import org.fs.comparator.comparators.comparator.types.container.RecordFieldContainer;
import org.fs.comparator.comparators.exception.FieldNotFoundException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utils for filter fields
 */
public class ExtractorUtils {

    /**
     * Extract fields by record container
     * @param record Record object with field
     * @return Field value
     */
    public static Object extractValue(RecordFieldContainer record) {
        return extractValue(record.field(), record.object());
    }

    /**
     * Extract values form field
     * @param field Field with value
     * @param object Object with field
     * @return Field value
     */
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

    /**
     * Extract fields form object. Only declared fields
     * @param object Object with specific fields
     * @return Declared fields in object
     */
    public static Set<Field> extractFields(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .collect(Collectors.toSet());
    }

    /**
     * Extract fields with don't have such names
     * @param object Object with fields
     * @param exclude Names that fields shouldn't have
     * @return Fields that don't have names in params
     */
    public static Set<Field> extractFieldsExclude(Object object, Set<String> exclude) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(e -> !exclude.contains(e.getName()))
                .collect(Collectors.toSet());
    }

    /**
     * Extract fields with specific names
     * @param object Object with fields
     * @param only Fields names for extract
     * @return Field with only names
     */
    public static Set<Field> extractFieldsOnly(Object object, Set<String> only) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(e -> only.contains(e.getName()))
                .collect(Collectors.toSet());
    }

    /**
     * Find field in internal objects recursively
     * @param path Field path using dots
     * @param object Object for finding fields
     * @return Found field
     */
    public static RecordFieldContainer extractFieldByPathRecursively(String path, Object object) {
        String[] names = path.split("\\.");

        if(names.length == 1) {
            Field field = extractField(names[0], object);

            return new RecordFieldContainer(field, object);
        }

        Object tempObj = object;

        for (int i = 0; i < names.length; i++) {
            Field field = extractField(names[i], tempObj);

            if(i == names.length - 1) {
                return new RecordFieldContainer(field, tempObj);
            }

            tempObj = extractValue(field, tempObj);
        }

        throw new FieldNotFoundException("Cannot find field with path: " + path);
    }

    /**
     * Extract specific field by its name
     * @param name Field name
     * @param object Object contains field
     * @return Field with name
     */
    public static Field extractField(String name, Object object) {
        for (Field declaredField : object.getClass().getDeclaredFields()) {
            if(declaredField.getName().equals(name)) {
                return declaredField;
            }
        }

        throw new FieldNotFoundException("Cannot find field with name: " + name);
    }
}
