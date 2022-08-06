package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(file.toFile().length(), file.toFile().getName());
        List<Path> paths = new ArrayList<>();
        if (files.containsKey(property)) {
            paths = (files.get(property));
        }
        paths.add(file.toAbsolutePath());
        files.put(property, paths);
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicates() {
        List<Path> duplicates = new ArrayList<>();
        files.values().stream()
             .filter(paths -> paths.size() > 1)
             .forEach(duplicates::addAll);
        return duplicates;
    }
}