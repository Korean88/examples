package com.home.retry.business;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClass {

    private List<String> in = Arrays.asList("aba","abb", "123");
    private List<String> out = Arrays.asList("aba","bba", "321");

    @TestFactory
    public Stream<DynamicTest> translateDynamicTestsFromStream() {
        return in.stream()
                .map(word ->
                        DynamicTest.dynamicTest("Test translate " + word, () -> {
                            int id = in.indexOf(word);
                            assertEquals(out.get(id), translate(word));
                        })
                );
    }

    public String translate(String word) {
        return new StringBuilder(word).reverse().toString();
    }
}
