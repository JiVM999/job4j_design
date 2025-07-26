package ru.job4j.generics;

import java.util.Objects;

public class Animal {

    private int numberOfLimbs;

    public Animal(int numberOfLimbs) {
        this.numberOfLimbs = numberOfLimbs;
    }

    public int getNumberOfLimbs() {
        return numberOfLimbs;
    }

    public void setNumberOfLimbs(int numberOfLimbs) {
        this.numberOfLimbs = numberOfLimbs;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return numberOfLimbs == animal.numberOfLimbs;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numberOfLimbs);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{"
                + "Количество конечностей " + numberOfLimbs
                + '}';
    }
}
