package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "three", "four", "five");
        assertThat(list).hasSize(4)
                .filteredOn(s -> !s.equals("first"))
                .first().isEqualTo("three");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "first", "second", "second", "three", "four", "five", "six", "six", "seven");
        assertThat(set).hasSize(7)
                .anySatisfy(s -> {
                            assertThat(s.length()).isEqualTo(3);
                            assertThat(s.contains("s")).isEqualTo(true);
                        })
                .allSatisfy(s -> {
                            assertThat(s.length()).isLessThan(7);
                        });
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five", "six", "seven");
        assertThat(map).hasSize(7)
                .containsKeys("seven", "second")
                .containsValues(5, 6, 1)
                .hasEntrySatisfying("first", v -> {
                    assertThat(v).isGreaterThan(-1);
                    assertThat(v).isLessThan(2);
                });
    }
}