package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Sphere")
                .isNotNull();
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Tetrahedron")
                .isNotBlank();
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Cube")
                .isNotEmpty();
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(10, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void numberOfVerticesOfSphere() {
        Box box = new Box(0, 10);
        int number = box.getNumberOfVertices();
        assertThat(number)
                .isEqualTo(0)
                .isBetween(-1, 1);
    }

    @Test
    void numberOfVerticesOfUnknown() {
        Box box = new Box(0, -2);
        int number = box.getNumberOfVertices();
        assertThat(number)
                .isEqualTo(-1)
                .isLessThan(0);
    }

    @Test
    void cubeIsExist() {
        Box box = new Box(8, 10);
        assertThat(box.isExist())
                .isTrue();
    }

    @Test
    void whenUnknownThenNotExist() {
        Box box = new Box(10, 10);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void tetrahedronArea() {
        Box box = new Box(4, 10);
        assertThat(box.getArea())
                .isCloseTo(173.2D, within(0.1D))
                .isEqualTo(173.2D, within(0.1D))
                .isPositive();
    }

    @Test
    void unknownArea() {
        Box box = new Box(2, -2);
        assertThat(box.getArea()).isEqualTo(0);
    }
}