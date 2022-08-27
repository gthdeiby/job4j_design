package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car(true, 2008, "Ford Explorer",
                new Engine("Petrol", 296), new String[]{"Luke", "ABS", "GPS"});

        final Gson gson = new GsonBuilder().create();
        String carJson = gson.toJson(car);
        System.out.println(carJson);

        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
