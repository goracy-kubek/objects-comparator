package org.fs.comparator.container.object;

import org.fs.comparator.container.RecordFieldContainer;

import java.util.Map;
import java.util.Optional;

/**
 * Wrapper for objects will be compared
 */
public abstract class ComparableObjectWrapper {

    /**
     * Get wrapped object
     * @return Wrapper object
     */
    public abstract Object getObject();

    /**
     * Fields to compare of this object
     * @return Map of field name, and record that contains it's value
     */
    public abstract Map<String, RecordFieldContainer> getFields();

    /**
     * Contains fields names for comparing with another fields with different names
     *
     * @param left Left field name
     * @param right Right field name
     */
    public abstract void addFieldMapper(String left, String right);

    public abstract Map<String, String> getFieldMapper();

    public Optional<RecordFieldContainer> getFieldRecord(String fieldName) {
        return Optional.ofNullable(getFields().get(fieldName));
    }

    public Optional<String> getMappedField(String fieldName) {
        return Optional.ofNullable(getFieldMapper().get(fieldName));
    }
}
