package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final boolean leftHand;
    private final int year;
    private final String model;
    private final Engine engine;
    private final String[] options;

    @Override
    public String toString() {
        return "Car{"
                + "leftHand=" + leftHand
                + ", year=" + year
                + ", model='" + model + '\''
                + ", engine=" + engine
                + ", options=" + Arrays.toString(options)
                + '}';
    }
    public Car(boolean leftHand, int year, String model, Engine engine, String[] options) {
        this.leftHand = leftHand;
        this.year = year;
        this.model = model;
        this.engine = engine;
        this.options = options;
    }

    public boolean isLeftHand() {
        return leftHand;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getOptions() {
        return options;
    }
}
