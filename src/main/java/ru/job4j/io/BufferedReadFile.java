package ru.job4j.io;

import java.io.*;

public class BufferedReadFile {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("data/input.txt"))) {
            in.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}