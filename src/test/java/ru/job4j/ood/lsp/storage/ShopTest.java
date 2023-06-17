package ru.job4j.ood.lsp.storage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ShopTest {

    @Test
    void whenToShop() {
        List<Store> stores = new ArrayList<>();
        LocalDate now = LocalDate.now();
        AbstractStore warehouse = new Warehouse();
        AbstractStore shop = new Shop();
        AbstractStore trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality control = new ControlQuality(stores);
        Food dumplings =  new Food("Dumplings", now.plusDays(100), now.minusDays(100), 200, 0.1);
        Food potato = new Food("Potato", now.plusDays(10), now.minusDays(100), 50, 0.1);
        List<Food> foods = new ArrayList<>(List.of(dumplings, potato));
        control.execute(foods);
        assertThat(stores.get(1).getFood()).contains(dumplings);
    }
}