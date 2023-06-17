package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    List<Food> foods = new ArrayList<>();

    @Override
    public double calculateExpiration(Food product) {
        return (double) ChronoUnit.DAYS.between(product.getCreateDate(), LocalDate.now())
                / (double) ChronoUnit.DAYS.between(product.getCreateDate(), product.getExpiryDate());
    }

    @Override
    public List<Food> getFood() {
        return foods;
    }
}