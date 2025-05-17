package org.fs.comparator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("unused")
public class ExcludeFieldsComparatorTest {

    @ParameterizedTest
    @DisplayName("Simple comparator")
    @MethodSource("compareFirstFields")
    void test1(boolean expected, Object left, Object right) {
        boolean actual = ComparatorUtils.compareObjects(left, right)
                .compareMatchingNames()
                .compare();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Exclude fields")
    @MethodSource("compareWithExclude")
    void test2(boolean expected, String[] exclude, Object left, Object right) {
        boolean actual = ComparatorUtils.compareObjects(left, right)
                .compareMatchingNames()
                .excludeFields(exclude)
                .compare();

        assertThat(actual).isEqualTo(expected);
    }

    @SuppressWarnings("FieldMayBeFinal")
    private static Stream<Arguments> compareFirstFields() {
        return Stream.of(
            Arguments.of(
                true,
                new Object() {
                    private String one = "1";
                    private String two = "12";
                },
                new Object() {
                    private String two = "12";
                    private String three = "123";
                }
            ),
            Arguments.of(
                false,
                new Object() {
                    private String one = "1";
                    private String two = "12";
                },
                new Object() {
                    private String two = "12 Incorrect";
                    private String three = "123";
                }
            ),
            Arguments.of(
                false,
                new Object() {
                    private String one = "1";
                    private String two = "12";
                    private String three = "123";
                },
                new Object() {
                    private String two = "12 Incorrect";
                    private String three = "123";
                }
            )
        );
    }

    @SuppressWarnings("FieldMayBeFinal")
    private static Stream<Arguments> compareWithExclude() {
        return Stream.of(
                Arguments.of(
                        true,
                        new String[] { "two" },
                        new Object() {
                            private String one = "1";
                            private String two = "12";
                            private String three = "123";
                        },
                        new Object() {
                            private String two = "12 Incorrect";
                            private String three = "123";
                        }
                ),
                Arguments.of(
                        false,
                        new String[] { "two" },
                        new Object() {
                            private String one = "1";
                            private String two = "12";
                            private String three = "123";
                            private String four = "1234";
                        },
                        new Object() {
                            private String two = "12 Incorrect";
                            private String three = "123";
                            private String four = "1234 Incorrect";
                        }
                ),
                Arguments.of(
                        false,
                        new String[] { "two" },
                        new Object() {
                            private String one = "1";
                            private String two = "12";
                            private String three = "123";
                            private String four = "1234";
                        },
                        new Object() {
                            private String two = "12 Incorrect";
                            private String three = "123";
                            private String four = "1234 Incorrect";
                        }
                )
        );
    }
}
