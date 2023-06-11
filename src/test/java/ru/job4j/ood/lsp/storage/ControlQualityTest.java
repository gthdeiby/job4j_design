package ru.job4j.ood.lsp.storage;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    ControlQuality control;
    List<Store> stores = new ArrayList<>();
    LocalDate now;

    @BeforeEach
    void init() {
        now = LocalDate.now();
        AbstractStore warehouse = new Warehouse();
        AbstractStore shop = new Shop();
        AbstractStore trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        control = new ControlQuality(stores);
    }

    @Test
    void whenExecute() {
        Food butter = new Food("Butter", now.minusDays(10), now.minusDays(100), 100, 0.1);
        Food apple =  new Food("Apple", now.plusDays(100), now.minusDays(10), 150, 0.1);
        Food dumplings =  new Food("Dumplings", now.plusDays(100), now.minusDays(100), 200, 0.1);
        Food potato = new Food("Potato", now.plusDays(10), now.minusDays(100), 50, 0.1);
        List<Food> foods = new ArrayList<>(List.of(butter, apple, dumplings, potato));
        control.execute(foods);
        assertThat(stores.get(0).getFood().get(0).getName()).isEqualTo("Apple");
        assertThat(stores.get(1).getFood().get(0).getName()).isEqualTo("Dumplings");
        assertThat(stores.get(1).getFood().get(1).getPrice()).isEqualTo(45);
        assertThat(stores.get(2).getFood().get(0).getName()).isEqualTo("Butter");
    }

    @Test
    void whenToWarehouse() {
        Food apple =  new Food("Apple", now.plusDays(100), now.minusDays(10), 150, 0.1);
        List<Food> foods = new ArrayList<>(List.of(apple));
        control.execute(foods);
        assertThat(stores.get(0).getFood()).contains(apple);
    }

    @Test
    void whenToShop() {
        Food dumplings =  new Food("Dumplings", now.plusDays(100), now.minusDays(100), 200, 0.1);
        Food potato = new Food("Potato", now.plusDays(10), now.minusDays(100), 50, 0.1);
        List<Food> foods = new ArrayList<>(List.of(dumplings, potato));
        control.execute(foods);
        assertThat(stores.get(1).getFood()).contains(dumplings);
    }

    @Test
    void whenToTrash() {
        Food butter = new Food("Butter", now.minusDays(10), now.minusDays(100), 100, 0.1);
        List<Food> foods = new ArrayList<>(List.of(butter));
        control.execute(foods);
        assertThat(stores.get(2).getFood()).contains(butter);
    }
}