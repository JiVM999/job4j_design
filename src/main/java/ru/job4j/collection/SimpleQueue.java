package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int sizeIn;
    private int sizeOut;

    public T poll() {
        if (sizeOut == 0 && sizeIn == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        for (int i = 0; i < sizeIn; i++) {
            output.push(input.pop());
        }
        T value = output.pop();
        sizeIn--;
        for (int i = 0; i < sizeIn; i++) {
            input.push(output.pop());
        }
        return value;
    }

    public void push(T value) {
        input.push(value);
        sizeIn++;
    }
}