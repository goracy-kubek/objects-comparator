package org.fs.comparator.util;

import org.fs.comparator.container.RecordFieldContainer;
import org.fs.comparator.exception.FieldNotFoundException;

import java.lang.reflect.Field;

/**
 * Utils for filter fields
 */
public class ExtractorUtils {
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
