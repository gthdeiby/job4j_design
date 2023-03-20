package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class StringGeneratorTest {
    @Test
    void whenKeyNotFound() {
        StringGenerator generator = new StringGenerator();
        String defaultString ="I am a ${name}, Who are ${subject}? ";
        Map<String, String> keys = Map.of("name", "Petr Arsentev");
        assertThatThrownBy(() -> generator.produce(defaultString, keys))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Key not found");
    }

    @Test
    void whenExtraKeysFound() {
        StringGenerator generator = new StringGenerator();
        String defaultString ="I am a ${name}.";
        Map<String, String> keys = Map.of("name", "Petr Arsentev", "subject", "you");
        assertThatThrownBy(() -> generator.produce(defaultString, keys))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Extra keys found");
    }

    @Test
    void whenProduceSuccess() {
        Generator generator = new StringGenerator();
        String defaultString ="I am a ${name}, Who are ${subject}? ";
        Map<String, String> keys = Map.of("name", "Petr Arsentev", "subject", "you");
        String rsl = "I am a Petr Arsentev, Who are you? ";
        assertThat(generator.produce(defaultString, keys)).isEqualTo(rsl);
    }

    @Test
    void whenProduceFailed() {
        Generator generator = new StringGenerator();
        String defaultString ="I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = Map.of("name", "Petr Arsentev", "subject", "you");
        String rsl = "I am a Petr Arsentev, Who are you? ";
        assertThat(generator.produce(defaultString, keys)).isNotEqualTo(rsl);
    }
}