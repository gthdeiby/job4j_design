package ru.job4j.serialization.json;

public class Engine {
    private final String type;
    private final int power;

    public Engine(String type, int power) {
        this.type = type;
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "type='" + type + '\''
                + ", power=" + power
                + '}';
    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }
}
