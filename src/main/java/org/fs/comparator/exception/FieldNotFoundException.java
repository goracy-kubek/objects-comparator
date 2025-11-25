package org.fs.comparator.exception;

public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(String fieldName) {
        super("No such field with name: " + fieldName);
    }
}
