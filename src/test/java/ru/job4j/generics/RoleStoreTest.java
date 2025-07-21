package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Добавление"));
        Role result = store.findById("1");
        assertThat(result.getRoots()).isEqualTo("Добавление");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Добавление"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Добавление"));
        store.add(new Role("1", "Редактирование"));
        Role result = store.findById("1");
        assertThat(result.getRoots()).isEqualTo("Добавление");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Удаление"));
        store.replace("1", new Role("1", "Редактирование"));
        Role result = store.findById("1");
        assertThat(result.getRoots()).isEqualTo("Редактирование");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Удаление"));
        store.replace("10", new Role("10", "Добавление"));
        Role result = store.findById("1");
        assertThat(result.getRoots()).isEqualTo("Удаление");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Удаление"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Удаление"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoots()).isEqualTo("Удаление");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Бан"));
        boolean result = store.replace("1", new Role("1", "Мут"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Редактирование"));
        boolean result = store.replace("10", new Role("10", "Мут"));
        assertThat(result).isFalse();
    }
}