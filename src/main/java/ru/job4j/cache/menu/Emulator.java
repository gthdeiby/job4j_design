package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.io.File;
import java.util.Scanner;

public class Emulator {
    private static final String NOT_DIRECTORY = "Введите существующую директорию:";
    private static final String DIRECTORY = "Укажите, пожалуйста, директорию:";
    private static final String NOT_FILE = "Файл не найден.";
    private static final String FILE = "Введите, пожалуйста, имя файла:";
    private static final String CONTENT = "Содержание файла:";
    private static final String SUCCESS = "Данные успешно загружены.";
    private static final String EXIT = "Конец работы программы.";
    private static final String MENU = """
            ==================================================
            Введите 1 для задания директории.
            Введите 2 для загрузки содержимого файла в кэш.
            Введите 3 для получения содержимого файла из кэша.
            Введите любой другой символ для выхода.
            ==================================================
            """;

    private static void run(Scanner scanner) {
        boolean run = true;
        String path = changeDirectory(scanner);
        String key;
        int userChoice;
        AbstractCache<String, String> cache = new DirFileCache(path);
        while (run) {
            System.out.println(MENU);
            try {
                userChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                userChoice = 0;
            }
            if (userChoice == 1) {
                path = changeDirectory(scanner);
            } else if (userChoice == 2) {
                System.out.println(FILE);
                key = scanner.nextLine();
                if (!(new File(String.join("\\", path, key)).isFile())) {
                    System.out.println(NOT_FILE);
                    continue;
                }
                cache.get(key);
                System.out.println(SUCCESS);
            } else if (userChoice == 3) {
                System.out.println(FILE);
                key = scanner.nextLine();
                String file = cache.get(key);
                if (file == null) {
                    System.out.println(NOT_FILE);
                    continue;
                }
                System.out.println(CONTENT);
                System.out.println(file);
            } else {
                System.out.println(EXIT);
                run = false;
            }
        }
    }

    private static String changeDirectory(Scanner scanner) {
        System.out.println(DIRECTORY);
        String path = scanner.nextLine();
        while (!(new File(path).isDirectory())) {
            System.out.println(NOT_DIRECTORY);
            path = scanner.nextLine();
        }
        return path;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }
}
