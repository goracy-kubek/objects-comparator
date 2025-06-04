package org.fs.comparator.util;

import org.fs.comparator.container.ComparatorRecordContainer;
import org.fs.comparator.container.RecordFieldContainer;
import org.fs.comparator.container.object.LeftObject;
import org.fs.comparator.container.object.RightObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Helper that helps with comparing two objects
 */
public class TwoObjectsUtils {

    /**
     * Extract exact fields to compare each other
     * @return Container of two fields
     */
    public static List<ComparatorRecordContainer> extractFieldsForCompare(LeftObject left, RightObject right) {
        var comparatorContainerList = new ArrayList<ComparatorRecordContainer>();

        for(var fli : left.getFields().entrySet()) {
            var leftFieldName = fli.getKey();
            var rightFieldName = left.getMappedField(leftFieldName).orElse(leftFieldName);

            Optional<RecordFieldContainer> leftFieldRecord = left.getFieldRecord(leftFieldName);
            Optional<RecordFieldContainer> rightFieldRecord = right.getFieldRecord(rightFieldName);

            if(leftFieldRecord.isEmpty() || rightFieldRecord.isEmpty()) {
                continue;
            }

            comparatorContainerList.add(new ComparatorRecordContainer(leftFieldRecord.get(), rightFieldRecord.get()));
        }

        return comparatorContainerList;
    }
}
