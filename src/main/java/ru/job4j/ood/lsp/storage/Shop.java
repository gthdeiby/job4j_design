package ru.job4j.ood.lsp.storage;

public class Shop extends AbstractStore {

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        double exp = calculateExpiration(product);
        if (exp >= 0.25 && exp < 1) {
            if (exp > 0.75) {
                product.setPrice(product.getPrice() * (1 - product.getDiscount()));
            }
            rsl = foods.add(product);
        }
        return rsl;
    }
}