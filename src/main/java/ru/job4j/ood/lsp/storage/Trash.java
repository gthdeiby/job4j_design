package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Trash extends AbstractStore {

    List<Food> trash = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        if (calculateExpiration(product) >= 1) {
            rsl = trash.add(product);
        }
        return rsl;
    }

    @Override
    public List<Food> getFood() {
        return trash;
    }
}
