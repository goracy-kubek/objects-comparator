package org.fs.comparator;

import org.fs.comparator.argument.ComparatorArguments;
import org.fs.comparator.argument.ExcludeFieldsArguments;
import org.fs.comparator.argument.OnlyFieldsArguments;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("unused")
public class ComparatorTest {

    @ParameterizedTest
    @DisplayName("Simple comparator")
    @ArgumentsSource(ComparatorArguments.class)
    void test1(boolean expected, Object left, Object right) {
        boolean actual = ComparatorUtils.compareObjects(left, right).compare();

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

    @Test
    void test4() {
        var left = new Object() {
            private String one = "1";
            private Object two = new Object() {
                private String one = "1";
                private String two = "12";
                private String three = "123";
            };
            private String three = "123";
        };

        var right = new Object() {
            private String one = "1";
            private String three = "12";
        };

        boolean actual = ComparatorUtils.compareObjects(left, right)
                .differentFieldsCompare("two.two", "three")
                .compare();

        assertThat(actual).isEqualTo(true);
    }
}
