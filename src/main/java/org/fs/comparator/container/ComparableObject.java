package org.fs.comparator.container;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ComparableObject {
    private final Object object;
    private final Map<String, Field>  fields;

    protected ComparableObject(Object object) {
        this.object = object;
        this.fields = fieldsToMap(object.getClass());
    }

    private static Map<String, Field> fieldsToMap(Class<?> clazz) {
       return getClassHierarchy(clazz)
               .flatMap(c -> Arrays.stream(c.getDeclaredFields()))
               .collect(Collectors.toMap(Field::getName, Function.identity(), (o, n) -> o));
    }

    private static Stream<Class<?>> getClassHierarchy(Class<?> clazz) {
        return Stream.iterate(clazz, isNotSimpleObject(), Class::getSuperclass);
    }

    private static Predicate<Class<?>> isNotSimpleObject() {
        return superclass -> superclass != null && superclass != Object.class;
    }

    public Object getObject() {
        return object;
    }

    public Optional<Field> getFieldByName(String name) {
        return Optional.ofNullable(fields.get(name));
    }
}
