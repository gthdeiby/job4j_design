package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Argument pattern violation");
        }
        if (!Paths.get(args[0]).toFile().exists()) {
            throw new IllegalArgumentException(String.format("%s is not exist", args[0]));
        }
        if (!Paths.get(args[0]).toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("%s is not directory", args[0]));
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException(String.format("%s is not file extension", args[1]));
        }
    }
}