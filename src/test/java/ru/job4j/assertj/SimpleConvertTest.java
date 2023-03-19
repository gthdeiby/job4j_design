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
        String[] array = simpleConvert.toArray("one", "two", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("two")
                .contains("one", Index.atIndex(0))
                .containsAnyOf("zero", "two", "six")
                .doesNotContain("one", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("one", "two", "three", "four", "five");
        assertThat(list).startsWith("one", "two")
                .endsWith("five")
                .containsSequence("two", "three", "four")
                .first().isNotNull()
                    .isEqualTo("one");
        assertThat(list).filteredOnAssertions(e -> assertThat(e.startsWith("f")).isNotNull());
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("one", "two", "three", "four", "five");
        assertThat(set).allSatisfy(e -> {
            assertThat(e).isLessThan("zero");
        })
                .anyMatch(e -> e.equals("one"))
                .last().isNotNull();
        assertThat(set).filteredOn(e -> e.length() == 3).hasSize(2);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("one", "two", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("one", "two", "three")
                .containsValues(1, 2, 3)
                .doesNotContainKey("zero")
                .doesNotContainValue(-1)
                .containsEntry("two", 1);
    }
}