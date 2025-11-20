package org.fs.comparator.util;

import org.fs.comparator.container.LeftObject;
import org.fs.comparator.container.RightObject;

import java.util.HashSet;
import java.util.Set;

public class ExtractorUtils {
    public static Set<String> getCommonFieldNames(LeftObject left, RightObject right) {
        var common = new HashSet<>(left.getThisClassFieldsNames());
        common.retainAll(right.getThisClassFieldsNames());

        return common;
    }
}
