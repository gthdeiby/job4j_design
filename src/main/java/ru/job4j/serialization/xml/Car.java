package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean leftHand;
    @XmlAttribute
    private int year;
    @XmlAttribute
    private String model;
    private Engine engine;
    @XmlElementWrapper
    @XmlElement(name = "option")
    private String[] options;

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

    public Car() {
    }

    public Car(boolean leftHand, int year, String model, Engine engine, String[] options) {
        this.leftHand = leftHand;
        this.year = year;
        this.model = model;
        this.engine = engine;
        this.options = options;
    }
}
