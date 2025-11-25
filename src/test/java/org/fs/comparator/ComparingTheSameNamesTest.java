package org.fs.comparator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("unused")
public class ComparingTheSameNamesTest {
    static class SimpleClass {
        private String one;
        public int two;
        protected long three;
        boolean four;
    }

    static class GenericClass<T> {
        private T one;
        private List<String> two;
        private Map<Integer, T> three;
    }

    static class ChildClass extends GenericClass<String> {
        private String one;
        public static final String CONSTANT = "test";
        transient volatile String two;
        String four;
    }

    static class DeepHierarchyL1<T> {
        private T one;
        private T two;
        protected Boolean five;
    }

    static class DeepHierarchyL2<U> extends DeepHierarchyL1<U> {
        private U one;
        private U three;
    }

    static class DeepHierarchyL3 extends DeepHierarchyL2<String> {
        private String one;
        private int four;
    }

    static class ComplexGenericClass<K, V> {
        private K one;
        private V two;
        private List<Map<K, V>> three;
    }

    static class AllModifiersClass {
        private String one;
        public String two;
        protected String three;
        String four;
        static String five;
        final String six = "final";
        transient String seven;
        volatile String eight;
    }

    static class EmptyClass {}

    static class SyntheticFieldClass {
        private void someMethod() {
            class LocalClass {
                private String one;
            }
        }
    }

    @Test
    @DisplayName("Exclude and more fields")
    public void test1() {
        var left = new DeepHierarchyL3();
        left.one = "asdf";
        left.five = true;

        var right = new SimpleClass();
        right.one = "asdf";
        right.four = true;

        var actual = ComparatorUtils.compareObjects(left, right)
                .excludeFields("two", "three", "four", "five")
                .moreFields("five", "four")
                .compare();

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Only and more fields")
    public void test2() {
        var left = new DeepHierarchyL3();
        left.one = "asdf";
        left.five = true;

        var right = new SimpleClass();
        right.one = "asdf";
        right.four = true;

        var actual = ComparatorUtils.compareObjects(left, right)
                .onlyFields("one")
                .moreFields("five", "four")
                .compare();

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Not equal more fields")
    public void test3() {
        var left = new DeepHierarchyL3();
        left.one = "asdf";
        left.five = null;

        var right = new SimpleClass();
        right.one = "asdf";
        right.four = true;

        var actual = ComparatorUtils.compareObjects(left, right)
                .onlyFields("one")
                .moreFields("five", "four")
                .compare();

        assertThat(actual).isFalse();
    }
}
