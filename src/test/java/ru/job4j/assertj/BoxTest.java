package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 2);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisNotExist() {
        Box box = new Box(2, 4);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void isThisExist() {
        Box box = new Box(4, 11);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void whenNumberOfVerticesIsSix() {
        Box box = new Box(4, 11);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(4);
    }

    @Test
    void whenNumberOfVerticesIsNegative() {
        Box box = new Box(4, 0);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(-1);
    }

    @Test
    void whenAreaOfSphere() {
        Box box = new Box(0, 9);
        double area = box.getArea();
        assertThat(area).isCloseTo(1017.87601d, withPrecision(0.001d));
        assertThat(area).isGreaterThan(1017d)
                .isLessThan(1018d);
    }

    @Test
    void whenAreaOfCube() {
        Box box = new Box(8, 13);
        double area = box.getArea();
        assertThat(area).isEqualTo(1014d);
    }
}