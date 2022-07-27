package ru.job4j.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("surname")).isEqualTo("Arsentev");
    }

    @Test
    void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("surname")).isEqualTo("Arsentev");
    }

    @Test
    void whenIllegalArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String path = "./data/pair_with_illegal_argument.properties";
            Config config = new Config(path);
            config.load();
        });
    }
}