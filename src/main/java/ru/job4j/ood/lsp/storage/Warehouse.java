package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractStore {

    List<Food> warehouse = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        if (calculateExpiration(product) < 0.25) {
            rsl = warehouse.add(product);
        }
        return rsl;
    }

    @Override
    public List<Food> getFood() {
        return warehouse;
    }
}
