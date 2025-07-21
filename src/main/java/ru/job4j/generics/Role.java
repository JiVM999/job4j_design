package ru.job4j.generics;

public class Role extends Base {

    private final String roots;

    public Role(String id, String roots) {
        super(id);
        this.roots = roots;
    }

    public String getRoots() {
        return roots;
    }
}
