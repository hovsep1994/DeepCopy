package com.deepcopy;

import com.deepcopy.models.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class DeepCopyUtilTest {

    @Test
    void testDeepCopyWithImmutableObject() {
        String original = "Hello";
        Object copy = DeepCopyUtil.deepCopy(original);
        Assertions.assertThat(original).isSameAs(copy);
    }

    @Test
    void testDeepCopyWithArray() {
        int[] original = {1, 2, 3};
        int[] copy = DeepCopyUtil.deepCopy(original);
        Assertions.assertThat(original).isEqualTo(copy);
        Assertions.assertThat(original).isNotSameAs(copy);
    }

    @Test
    void testDeepCopyWithCollection() {
        List<String> original = Arrays.asList("A", "B", "C");
        List<?> copy = DeepCopyUtil.deepCopy(original);
        Assertions.assertThat(original).isEqualTo(copy);
        Assertions.assertThat(original).isNotSameAs(copy);
    }

    @Test
    void testDeepCopyWithMap() {
        Map<String, String> original = Map.of("key1", "value1", "key2", "value2");
        Map<?, ?> copy = DeepCopyUtil.deepCopy(original);
        Assertions.assertThat(original).isEqualTo(copy);
        Assertions.assertThat(original).isNotSameAs(copy);
    }

    @Test
    void testDeepCopyWithSimpleCustomObject() {
        Simple original = new Simple("Test", 42);
        Simple copy = DeepCopyUtil.deepCopy(original);
        Assertions.assertThat(original).usingRecursiveComparison().isEqualTo(copy);
        Assertions.assertThat(original).isNotSameAs(copy);
    }

    @Test
    void testDeepCopyWithManCustomObject() {
        Man original = new Man("Test", 42, Arrays.asList("Book1", "Book2"));
        Man copy = DeepCopyUtil.deepCopy(original);
        Assertions.assertThat(original).usingRecursiveComparison().isEqualTo(copy);
        Assertions.assertThat(original).isNotSameAs(copy);
    }

    @Test
    void testDeepCopyWithNoConstructorObject() {
        NoConstructor original = new NoConstructor("Test");
        NoConstructor copy = DeepCopyUtil.deepCopy(original);
        Assertions.assertThat(original).usingRecursiveComparison().isEqualTo(copy);
        Assertions.assertThat(original).isNotSameAs(copy);
    }

    @Test
    void testDeepCopyWithComplexCustomObject() {
        Complex complex = new Complex();
        complex.setMan(new Man("Test", 42, Arrays.asList("Book1", "Book2")));
        complex.setBigDecimal(BigDecimal.valueOf(23.43));
        complex.setMap(Map.of(
                Type.A, new Simple("John", 30),
                Type.B, new Simple("Mary", 25)
        ));
        complex.setLocalDateTime(LocalDateTime.now());
        Complex copy = DeepCopyUtil.deepCopy(complex);
        Assertions.assertThat(complex).usingRecursiveComparison().isEqualTo(copy);
        Assertions.assertThat(complex).isNotSameAs(copy);
    }

    @Test
    void testDeepCopyWithSelfReferenceCustomObject() {
        SelfReference original = new SelfReference();
        original.setSelfReference(original);
        SelfReference copy = DeepCopyUtil.deepCopy(original);
        Assertions.assertThat(original).usingRecursiveComparison().isEqualTo(copy);
        Assertions.assertThat(original).isNotSameAs(copy);
    }

}
