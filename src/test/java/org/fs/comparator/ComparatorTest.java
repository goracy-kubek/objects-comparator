package org.fs.comparator;

import org.fs.comparator.argument.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("unused")
public class ComparatorTest {

    @ParameterizedTest
    @DisplayName("Simple comparator")
    @ArgumentsSource(SimpleComparatorArguments.class)
    void test1(boolean expected, Object left, Object right) {
        boolean actual = ComparatorUtils.compareObjects(left, right)
                        .compare();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Exclude fields")
    @ArgumentsSource(ExcludeFieldsArguments.class)
    void test2(boolean expected, String[] exclude, Object left, Object right) {
        boolean actual = ComparatorUtils.compareObjects(left, right)
                .excludeFields(exclude)
                .compare();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Only fields")
    @ArgumentsSource(OnlyFieldsArguments.class)
    void test3(boolean expected, String[] only, Object left, Object right) {
        boolean actual = ComparatorUtils.compareObjects(left, right)
                .onlyFields(only)
                .compare();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Add different fields to compare")
    @ArgumentsSource(AddDifferentFieldsArguments.class)
    void test5(boolean expected, String exclude, String[] only, Object left, Object right) {
        boolean actual = ComparatorUtils.compareObjects(left, right)
                .excludeFields(exclude)
                .moreFields(only)
                .compare();

        assertThat(actual).isEqualTo(expected);
    }
}
