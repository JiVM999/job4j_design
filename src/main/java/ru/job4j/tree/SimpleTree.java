package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> mom = findBy(parent);
        Optional<Node<E>> baby = findBy(child);
        if (mom.isPresent()) {
            if (baby.isPresent() && !mom.get().children.contains(baby.get())) {
                mom.get().children.add(baby.get());
            } else {
                mom.get().children.add(new Node<>(child));
            }
        }
        return false;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (condition.test(element)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }

        @Override
    public boolean isBinary() {
        return findByPredicate((el) -> el.children.size() > 2).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate((el) -> el.value.equals(value));
    }
}