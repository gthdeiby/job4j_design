package ru.job4j.ood.lsp.storage;

public class Warehouse extends AbstractStore {

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        if (calculateExpiration(product) < 0.25) {
            rsl = foods.add(product);
        }
        return rsl;
    }
}