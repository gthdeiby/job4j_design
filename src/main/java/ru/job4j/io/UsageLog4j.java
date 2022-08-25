package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean condition = true;
        char letter = 'O';
        byte number = 1;
        short position = 10;
        int age = 29;
        long capacity = 100500;
        float length = 10.5f;
        double height = 1.88;
        LOG.debug("condition : {},\nletter : {},\nnumber : {},\nposition : {},\nage : {},\ncapacity : {},\nlength : {},\nheight : {},\n", condition, letter, number, position, age, capacity, length, height);
    }
}