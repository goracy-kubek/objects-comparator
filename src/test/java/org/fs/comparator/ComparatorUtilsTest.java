package org.fs.comparator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SuppressWarnings("unused")
public class ComparatorUtilsTest {
    @ParameterizedTest
    @DisplayName("Null in objects")
    @CsvSource(value = {
        "123, null",
        "null, 123",
        "null, null"
    }, nullValues = "null")
    void test1(Object left, Object right) {
        assertThatThrownBy(() ->
                ComparatorUtils.compareObjects(left, right)
                        .compareMatchingNames()
                        .compare()
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Objects mustn't be null");
    }
}
