package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean availability = true;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] src = line.split(" ");
                if (availability && (src[0].equals("400") || src[0].equals("500"))) {
                    out.write(src[1] + ";");
                    availability = false;
                } else if (!availability && (src[0].equals("200") || src[0].equals("300"))) {
                    out.println(src[1] + ";");
                    availability = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/unavailable.csv");
    }
}