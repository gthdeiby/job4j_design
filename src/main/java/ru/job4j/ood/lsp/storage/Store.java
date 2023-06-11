package ru.job4j.ood.lsp.storage;

import java.util.List;

public interface Store {

    boolean add(Food product);

    List<Food> getFood();

    double calculateExpiration(Food product);
}