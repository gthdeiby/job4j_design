package ru.job4j.ood.lsp.storage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TrashTest {

    @Test
    void whenToTrash() {
        List<Store> stores = new ArrayList<>();
        LocalDate now = LocalDate.now();
        AbstractStore warehouse = new Warehouse();
        AbstractStore shop = new Shop();
        AbstractStore trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality control = new ControlQuality(stores);
        Food butter = new Food("Butter", now.minusDays(10), now.minusDays(100), 100, 0.1);
        List<Food> foods = new ArrayList<>(List.of(butter));
        control.execute(foods);
        assertThat(stores.get(2).getFood()).contains(butter);
    }
}