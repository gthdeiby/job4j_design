package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Wrong key");
        }
        return values.get(key);
    }

    private void validateInput(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Parameter list is empty");
        }
        for (String arg : args) {
            if (!arg.contains("=")) {
                throw new IllegalArgumentException("Missing \"=\"");
            }
            String[] values = arg.split("=", 2);
            if (values.length != 2 || values[0].equals("-")
                    || values[0].isEmpty() || values[1].isEmpty()) {
                throw new IllegalArgumentException("Key-value pair violation");
            }
        }
    }

    private void parse(String[] args) {
        validateInput(args);
        Arrays.stream(args)
                .map(s -> s.split("=", 2))
                .forEach(s -> values.put(s[0].substring(1), s[1]));
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
