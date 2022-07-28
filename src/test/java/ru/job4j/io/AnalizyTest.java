package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalizyTest {

    @Test
    void unavailable(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(s -> rsl.append(s).append(System.lineSeparator()));
        }
        assertThat("10:57:01;10:59:01;" + System.lineSeparator()
                + "11:01:02;11:02:02;" + System.lineSeparator()).isEqualTo(rsl.toString());
    }
}