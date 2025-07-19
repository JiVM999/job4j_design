package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkInvalidFormatNoSymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key:value", "key1=value1", "key2=value2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("the symbol '='");
    }

    @Test
    void checkInvalidFormatNoKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=value", "key1=value1", "key2=value2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("a key");
    }

    @Test
    void checkInvalidFormatNoValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key=", "key1=value1", "key2=value2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("a value");
    }

    @Test
    void checkArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty");
    }
}