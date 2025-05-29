package org.fs.comparator;

import org.fs.comparator.argument.ComparatorExceptionsNullBlankEmptyArguments;
import org.fs.comparator.exception.ComparatorSettingsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ComparatorExceptionsTest {

//    @ParameterizedTest
//    @DisplayName("Exclude. Exception on null, blank and empty fields")
//    @ArgumentsSource(ComparatorExceptionsNullBlankEmptyArguments.class)
//    void test1(String[] exclude, Object left, Object right) {
//        assertThatThrownBy(() ->
//                ComparatorUtils.compareObjects(left, right)
//                        .excludeFields(exclude)
//                        .compare()
//        )
//                .isInstanceOf(ComparatorSettingsException.class)
//                .hasMessage("Exclude fields must not be null, empty, or blank");
//    }
//
//    @ParameterizedTest
//    @DisplayName("Only. Exception on null, blank and empty fields")
//    @ArgumentsSource(ComparatorExceptionsNullBlankEmptyArguments.class)
//    void test2(String[] exclude, Object left, Object right) {
//        assertThatThrownBy(() ->
//                ComparatorUtils.compareObjects(left, right)
//                        .onlyFields(exclude)
//                        .compare()
//        )
//                .isInstanceOf(ComparatorSettingsException.class)
//                .hasMessage("Only fields must not be null, empty, or blank");
//    }
}
