package org.fs.comparator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("unused")
public class ExcludeFieldsComparatorTest {
    @Test
    @DisplayName("Two equals fields")
    void test1() {
        class Left {
            private String one;
            private String two;
        }
        var left = new Left();
        left.one = "1";
        left.two = "12";

        class Right {
            private String two;
            private String three;
        }
        var right = new Right();
        right.two = "12";
        right.three = "123";

        assertThat(
            ComparatorUtils.compareObjects(left, right)
                .compareMatchingNames()
                .compare()
        ).isTrue();
    }

    @Test
    @DisplayName("Two not equals fields")
    void test2() {
        class Left {
            private String one;
            private String two;
        }
        var left = new Left();
        left.one = "1";
        left.two = "12";

        class Right {
            private String two;
            private String three;
        }
        var right = new Right();
        right.two = "12 Incorrect";
        right.three = "123";

        assertThat(
                ComparatorUtils.compareObjects(left, right)
                        .compareMatchingNames()
                        .compare()
        ).isFalse();
    }

    @Test
    @DisplayName("Default not equals")
    void test3() {
        class Left {
            private String one;
            private String two;
            private String three;
        }
        var left = new Left();
        left.one = "1";
        left.two = "12";
        left.three = "123";

        class Right {
            private String two;
            private String three;
        }
        var right = new Right();
        right.two = "12 Incorrect";
        right.three = "123";

        assertThat(
                ComparatorUtils.compareObjects(left, right)
                        .compareMatchingNames()
                        .compare()
        ).isFalse();
    }

    @Test
    @DisplayName("Equals exclude field")
    void test4() {
        class Left {
            private String one;
            private String two;
            private String three;
        }
        var left = new Left();
        left.one = "1";
        left.two = "12";
        left.three = "123";

        class Right {
            private String two;
            private String three;
        }
        var right = new Right();
        right.two = "12 Incorrect";
        right.three = "123";

        assertThat(
                ComparatorUtils.compareObjects(left, right)
                        .compareMatchingNames()
                        .excludeFields("two")
                        .compare()
        ).isTrue();
    }

    @Test
    @DisplayName("Equals exclude fields")
    void test5() {
        class Left {
            private String one;
            private String two;
            private String three;
            private String four;
        }
        var left = new Left();
        left.one = "1";
        left.two = "12";
        left.three = "123";
        left.four = "1234";

        class Right {
            private String two;
            private String three;
            private String four;
        }
        var right = new Right();
        right.two = "12 Incorrect";
        right.three = "123";
        right.four = "1234 Incorrect";

        assertThat(
                ComparatorUtils.compareObjects(left, right)
                        .compareMatchingNames()
                        .excludeFields("two", "four")
                        .compare()
        ).isTrue();
    }

    @Test
    @DisplayName("Not equals exclude field")
    void test6() {
        class Left {
            private String one;
            private String two;
            private String three;
            private String four;
        }
        var left = new Left();
        left.one = "1";
        left.two = "12";
        left.three = "123";
        left.four = "1234";

        class Right {
            private String two;
            private String three;
            private String four;
        }
        var right = new Right();
        right.two = "12 Incorrect";
        right.three = "123";
        right.four = "1234 Incorrect";

        assertThat(
                ComparatorUtils.compareObjects(left, right)
                        .compareMatchingNames()
                        .excludeFields("two")
                        .compare()
        ).isFalse();
    }
}
