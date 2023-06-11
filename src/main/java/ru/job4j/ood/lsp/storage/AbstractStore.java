package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class AbstractStore implements Store {

    @Override
    public double calculateExpiration(Food product) {
        return (double) ChronoUnit.DAYS.between(product.getCreateDate(), LocalDate.now())
                / (double) ChronoUnit.DAYS.between(product.getCreateDate(), product.getExpiryDate());
    }
}