package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {

    List<Food> shop = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        double exp = calculateExpiration(product);
        if (exp >= 0.25 && exp < 1) {
            if (exp > 0.75) {
                product.setPrice(product.getPrice() * (1 - product.getDiscount()));
            }
            rsl = shop.add(product);
        }
        return rsl;
    }

    @Override
    public List<Food> getFood() {
        return shop;
    }
}
