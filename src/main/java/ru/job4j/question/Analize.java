package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int add;
        int change = 0;
        int delete = 0;
        Map<Integer, String> map = new HashMap<>();

        for (User user : current) {
            map.put(user.getId(), user.getName());
        }
        for (User user : previous) {
            if (!map.containsKey(user.getId())) {
                delete++;
            } else if (!map.get(user.getId()).equals(user.getName())) {
                change++;
            }
            map.put(user.getId(), user.getName());
        }
        add = map.size() - previous.size();
        return new Info(add, change, delete);
    }
}