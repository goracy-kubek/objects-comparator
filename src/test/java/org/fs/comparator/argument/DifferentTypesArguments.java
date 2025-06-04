package org.fs.comparator.argument;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class DifferentTypesArguments implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(
                        new Object() {
                            private String one = "1";
                            private String two = "12";
                        },
                        new Object() {
                            private int two = 12;
                            private String three = "123";
                        }
                )
        );
    }
}
