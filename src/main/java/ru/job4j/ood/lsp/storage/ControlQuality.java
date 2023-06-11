package ru.job4j.ood.lsp.storage;

import java.util.List;

public class ControlQuality {

    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void execute(List<Food> foods) {
        for (Food food : foods) {
            for (Store store : stores) {
                if (store.add(food)) {
                    break;
                }
            }
        }
    }
}