package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
           for (Path path : sources) {
               zos.putNextEntry(new ZipEntry(path.toFile().getPath()));
               try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                   zos.write(out.readAllBytes());
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArgsName validateInput(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Argument pattern violation");
        }
        ArgsName zip = ArgsName.of(args);
        if (!Paths.get(zip.get("d")).toFile().exists()) {
            throw new IllegalArgumentException(String.format("%s is not exist", zip.get("d")));
        }
        if (!Paths.get(zip.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("%s is not directory", zip.get("d")));
        }
        if (!zip.get("e").startsWith(".")) {
            throw new IllegalArgumentException(String.format("%s is not file extension", zip.get("e")));
        }
        if (!zip.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("%s is not archive name", zip.get("o")));
        }
        return zip;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName zipStart = zip.validateInput(args);
        List<Path> source = Search.search(Path.of(zipStart.get("d")),
                p -> !p.toFile().getName().endsWith(zipStart.get("e")));
        zip.packFiles(source, Path.of(zipStart.get("o")));
    }
}