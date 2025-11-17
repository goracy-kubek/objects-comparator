package org.fs.comparator;

import org.fs.comparator.container.ComparableObject;
import org.fs.comparator.container.LeftObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("unused")
class ComparableObjectTest {

    static class SimpleClass {
        private String privateField;
        public int publicField;
        protected long protectedField;
        boolean packagePrivateField;
    }

    static class GenericClass<T> {
        private T genericField;
        private List<String> stringList;
        private Map<Integer, T> genericMap;
    }

    static class ChildClass extends GenericClass<String> {
        private String childField;
        public static final String CONSTANT = "test";
        transient volatile String transientField;
    }

    static class DeepHierarchyL1<T> {
        private T level1Field;
    }

    static class DeepHierarchyL2<U> extends DeepHierarchyL1<U> {
        private U level2Field;
    }

    static class DeepHierarchyL3 extends DeepHierarchyL2<String> {
        private String level3Field;
    }

    static class ComplexGenericClass<K, V> {
        private K key;
        private V value;
        private List<Map<K, V>> complexList;
    }

    static class AllModifiersClass {
        private String privateField;
        public String publicField;
        protected String protectedField;
        String packagePrivateField;
        static String staticField;
        final String finalField = "final";
        transient String transientField;
        volatile String volatileField;
    }

    static class EmptyClass {}

    static class SyntheticFieldClass {
        private void someMethod() {
            class LocalClass {
                private String localField;
            }
        }
    }

    @Test
    @DisplayName("Extract fields from simple class with different access modifiers")
    void test1() {
        SimpleClass object = new SimpleClass();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("privateField")).isPresent();
        assertThat(comparableObject.getFieldByName("publicField")).isPresent();
        assertThat(comparableObject.getFieldByName("protectedField")).isPresent();
        assertThat(comparableObject.getFieldByName("packagePrivateField")).isPresent();
    }

    @Test
    @DisplayName("Handle generic classes with parameterized types")
    void test2() {
        GenericClass<String> object = new GenericClass<>();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("genericField")).isPresent();
        assertThat(comparableObject.getFieldByName("stringList")).isPresent();
        assertThat(comparableObject.getFieldByName("genericMap")).isPresent();
    }

    @Test
    @DisplayName("Extract fields from inheritance hierarchy including parent classes")
    void test3() {
        ChildClass object = new ChildClass();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("childField")).isPresent();
        assertThat(comparableObject.getFieldByName("transientField")).isPresent();
        assertThat(comparableObject.getFieldByName("genericField")).isPresent();
        assertThat(comparableObject.getFieldByName("stringList")).isPresent();
        assertThat(comparableObject.getFieldByName("genericMap")).isPresent();
    }

    @Test
    @DisplayName("Handle deep inheritance hierarchy with three levels")
    void test4() {
        DeepHierarchyL3 object = new DeepHierarchyL3();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("level3Field")).isPresent();
        assertThat(comparableObject.getFieldByName("level2Field")).isPresent();
        assertThat(comparableObject.getFieldByName("level1Field")).isPresent();
    }

    @Test
    @DisplayName("Work with complex generic classes with multiple type parameters")
    void test5() {
        ComplexGenericClass<Integer, String> object = new ComplexGenericClass<>();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("key")).isPresent();
        assertThat(comparableObject.getFieldByName("value")).isPresent();
        assertThat(comparableObject.getFieldByName("complexList")).isPresent();
    }

    @Test
    @DisplayName("Extract fields with all possible modifiers except static")
    void test6() {
        AllModifiersClass object = new AllModifiersClass();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("privateField")).isPresent();
        assertThat(comparableObject.getFieldByName("publicField")).isPresent();
        assertThat(comparableObject.getFieldByName("protectedField")).isPresent();
        assertThat(comparableObject.getFieldByName("packagePrivateField")).isPresent();
        assertThat(comparableObject.getFieldByName("finalField")).isPresent();
        assertThat(comparableObject.getFieldByName("transientField")).isPresent();
        assertThat(comparableObject.getFieldByName("volatileField")).isPresent();
        assertThat(comparableObject.getFieldByName("staticField")).isPresent();
    }

    @Test
    @DisplayName("Exclude fields inherited from java.lang.Object")
    void test7() {
        SimpleClass object = new SimpleClass();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("wait")).isEmpty();
        assertThat(comparableObject.getFieldByName("notify")).isEmpty();
        assertThat(comparableObject.getFieldByName("notifyAll")).isEmpty();
        assertThat(comparableObject.getFieldByName("getClass")).isEmpty();
    }

    @Test
    @DisplayName("Handle anonymous classes created from interfaces")
    void test8() {
        Runnable anonymousClass = new Runnable() {
            public void setAnonymousField(String anonymousField) {
                this.anonymousField = anonymousField;
            }

            public String getAnonymousField() {
                return anonymousField;
            }

            private String anonymousField = "test";

            @Override
            public void run() {}
        };

        ComparableObject comparableObject = new LeftObject(anonymousClass);

        assertThat(comparableObject.getFieldByName("anonymousField")).isPresent();
    }

    @Test
    @DisplayName("Work with primitive arrays and object arrays")
    void test9() {
        class ArrayClass {
            private int[] intArray;
            private String[] stringArray;
        }

        ArrayClass object = new ArrayClass();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("intArray")).isPresent();
        assertThat(comparableObject.getFieldByName("stringArray")).isPresent();
    }

    @Test
    @DisplayName("Extract fields from enum types")
    void test10() {
        enum TestEnum {
            VALUE1, VALUE2;
            private String enumField;
        }

        TestEnum object = TestEnum.VALUE1;
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("enumField")).isPresent();
    }

    @Test
    @DisplayName("Check correctness of extracted field types")
    void test11() {
        SimpleClass object = new SimpleClass();
        ComparableObject comparableObject = new LeftObject(object);

        Optional<Field> privateField = comparableObject.getFieldByName("privateField");
        Optional<Field> publicField = comparableObject.getFieldByName("publicField");

        assertThat(privateField).isPresent();
        assertThat(privateField.get())
                .extracting(Field::getType, Field::getName)
                .containsExactly(String.class, "privateField");

        assertThat(publicField).isPresent();
        assertThat(publicField.get())
                .extracting(Field::getType)
                .isEqualTo(int.class);
    }

    @Test
    @DisplayName("Handle null object with exception throwing")
    void test12() {
        assertThatThrownBy(() -> new LeftObject(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Work with lambda expressions without crashing")
    void test13() {
        Runnable lambda = () -> System.out.println("test");
        ComparableObject comparableObject = new LeftObject(lambda);

        assertThat(comparableObject.getObject()).isNotNull();
    }

    @Test
    @DisplayName("Handle classes without fields")
    void test14() {
        EmptyClass object = new EmptyClass();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("anyField")).isEmpty();
    }

    @Test
    @DisplayName("Handle synthetic fields without crashing")
    void test15() {
        SyntheticFieldClass object = new SyntheticFieldClass();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getObject()).isNotNull();
    }

    @Test
    @DisplayName("Handle class with only static fields")
    @SuppressWarnings("InstantiationOfUtilityClass")
    void test21() {
        class StaticOnlyClass {
            static String staticField1;
            static int staticField2;
        }

        StaticOnlyClass object = new StaticOnlyClass();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("staticField1")).isPresent();
        assertThat(comparableObject.getFieldByName("staticField2")).isPresent();
    }

    @Test
    @DisplayName("Handle classes implementing multiple interfaces")
    void test23() {
        class MultiInterfaceClass implements Runnable, AutoCloseable {
            private String multiField;

            @Override public void run() {}
            @Override public void close() {}
        }

        MultiInterfaceClass object = new MultiInterfaceClass();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("multiField")).isPresent();
    }

    @Test
    @DisplayName("Handle duplicate field names in hierarchy (shadowing)")
    void test26() throws Exception {
        class ParentWithField {
            private final String shadowedField = "parent";
        }

        class ChildWithField extends ParentWithField {
            private final String shadowedField = "child";
        }

        ChildWithField object = new ChildWithField();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("shadowedField")).isPresent();
        assertThat(comparableObject.getFieldByName("shadowedField").orElseThrow().get(object))
                .isEqualTo(object.shadowedField);
    }

    @Test
    @DisplayName("Handle fields from abstract classes")
    void test28() {
        abstract class AbstractClass {
            private String abstractField;
        }

        class ConcreteClass extends AbstractClass {}

        ConcreteClass object = new ConcreteClass();
        ComparableObject comparableObject = new LeftObject(object);

        assertThat(comparableObject.getFieldByName("abstractField")).isPresent();
    }
}