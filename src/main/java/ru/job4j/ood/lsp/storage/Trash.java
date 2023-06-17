package ru.job4j.ood.lsp.storage;

public class Trash extends AbstractStore {

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        if (calculateExpiration(product) >= 1) {
            rsl = foods.add(product);
        }
        return rsl;
    }
}