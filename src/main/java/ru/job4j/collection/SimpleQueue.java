package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int sizeIn;
    private int sizeOut;

    public T poll() {
        if (sizeIn == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (sizeOut == 0) {
            while (sizeIn > 0) {
                output.push(input.pop());
                sizeOut++;
                sizeIn--;
            }
        }
        T value = output.pop();
        sizeOut--;
        return value;
    }

    public void push(T value) {
        input.push(value);
        sizeIn++;
    }
}