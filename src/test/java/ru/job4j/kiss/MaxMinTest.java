package ru.job4j.kiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    private List<Integer> list;
    private Comparator<Integer> comparator;
    private MaxMin maxMin;

    @BeforeEach
    void init() {
        list = new ArrayList<>(List.of(5, 25, 10, 3, 33, 100));
        comparator = Comparator.naturalOrder();
        maxMin = new MaxMin();
    }

    @Test
    void whenFindMax() {
        assertThat(maxMin.max(list, comparator)).isEqualTo(100);
    }

    @Test
    void whenFindMin() {
        assertThat(maxMin.min(list, comparator)).isEqualTo(3);
    }

    @Test
    void whenListIsNull() {
        List<Integer> list = Collections.emptyList();
        assertThat(maxMin.search(list, comparator)).isNull();
    }
}