package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    public static void multiple(int size) {
        String rsl;
        try (FileOutputStream out = new FileOutputStream("Multiple.txt")) {
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size; j++) {
                    rsl = i * j + "  ";
                    out.write((rsl).getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        multiple(5);
    }
}