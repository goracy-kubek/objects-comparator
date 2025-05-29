package org.fs.comparator.container.object;

import org.fs.comparator.container.RecordFieldContainer;

import java.util.Map;

public abstract class ComparableObject {
    public abstract Object getObject();
    public abstract Map<String, RecordFieldContainer> getFields();
}
