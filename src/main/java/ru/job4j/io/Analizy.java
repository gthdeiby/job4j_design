package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean availability = true;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] values = line.split(" ");
                if (availability && ("400".equals(values[0]) || "500".equals(values[0]))) {
                    out.write(values[1] + ";");
                    availability = false;
                }
                if (!availability && ("200".equals(values[0]) || "300".equals(values[0]))) {
                    out.println(values[1] + ";");
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