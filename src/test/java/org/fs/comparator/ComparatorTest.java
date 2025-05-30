package org.fs.comparator;

import org.fs.comparator.argument.DeepFieldsArguments;
import org.fs.comparator.argument.SimpleComparatorArguments;
import org.fs.comparator.argument.ExcludeFieldsArguments;
import org.fs.comparator.argument.OnlyFieldsArguments;
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
                        .compareByEqualsNames()
                        .compare();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Exclude fields")
    @ArgumentsSource(ExcludeFieldsArguments.class)
    void test2(boolean expected, String[] exclude, Object left, Object right) {
        boolean actual = ComparatorUtils.compareObjects(left, right)
                .compareByEqualsNames()
                .excludeFields(exclude)
                .compare();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Only fields")
    @ArgumentsSource(OnlyFieldsArguments.class)
    void test3(boolean expected, String[] only, Object left, Object right) {
        boolean actual = ComparatorUtils.compareObjects(left, right)
                .compareByEqualsNames()
                .onlyFields(only)
                .compare();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Compare deep fields")
    @ArgumentsSource(DeepFieldsArguments.class)
    void test4(boolean expected, String[] only, Object left, Object right) {
        boolean actual = ComparatorUtils.compareObjects(left, right)
                .compareByDifferentNames("two.two", "three")
                .compare();

        assertThat(actual).isEqualTo(expected);
    }
}
