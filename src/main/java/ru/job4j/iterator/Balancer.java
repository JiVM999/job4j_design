package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int row = 0;
        while (source.hasNext()) {
            if (nodes.size() == row) {
                row = 0;
            }
            nodes.get(row++).add(source.next());
        }
    }
}