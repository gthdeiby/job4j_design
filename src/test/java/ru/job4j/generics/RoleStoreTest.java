package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenameIsEngineer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Engineer"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Engineer"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Engineer"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsEngineer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Engineer"));
        store.add(new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Engineer"));
    }

    @Test
    public void whenReplaceThenRolenameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Engineer"));
        store.replace("1", new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Programmer"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Engineer"));
        store.replace("10", new Role("10", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Engineer"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Engineer"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsEngineer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Engineer"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Engineer"));
    }
}