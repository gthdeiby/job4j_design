package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Buffered {
    public static void main(String[] args) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("data/input.txt"));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("data/output.txt"))) {
            out.write(in.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
