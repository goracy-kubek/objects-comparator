package org.fs.comparator.argument;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class OnlyFieldsArguments implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(
                        true,
                        new String[] { "three" },
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
                        true,
                        new String[] { "two", "three" },
                        new Object() {
                            private String one = "1";
                            private String two = "12";
                            private String three = "123";
                            private String four = "1234";
                        },
                        new Object() {
                            private String two = "12";
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
