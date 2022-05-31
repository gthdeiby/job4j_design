package ru.job4j.generics;

public class Role extends Base {

    private final String rolename;

    public Role(String id, String name) {
        super(id);
        this.rolename = name;
    }

    public String getRolename() {
        return rolename;
    }
}