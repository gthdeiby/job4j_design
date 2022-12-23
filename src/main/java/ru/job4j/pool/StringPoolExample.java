package ru.job4j.pool;

public class StringPoolExample {
    public static void main(String[] args) {
        String string1 = new String("Hello");
        String string2 = new String("Hello");
        String string3 = "Hello";
        String string4 = "Hello";
        System.out.println(string1 == string2);
        System.out.println(string3 == string4);
        System.out.println(string1 == string3);
        System.out.println(string2 == string4);
        /*
        false
        true
        false
        false
         */

        String string5 = "Hello, world";
        String string6 = "Hello, " + "world";
        System.out.println(string5 == string6);
        /*
        true
        Интернирование в пул строк происходит только на этапе компиляции.
        Все строковые литералы, с помощью которых мы создаем новые строки,
        известны нам на этапе компиляции.
         */

        String string7 = "Hello, world";
        String string8 = "Hello, ";
        String string9 = string8 + "world";
        System.out.println(string7 == string9);
        /*
        false
        Вычисление string9 будет происходить только во время выполнения программы,
        а результирующая строка string9 будет создана в куче.
         */

        String string10 = "Hello";
        String string11 = new String("Hello");
        String string12 = string11.intern();
        System.out.println(string10 == string12);
        /*
        true
         */

        System.out.println(new String("New string") == new String("New string"));
        System.out.println(new String("Interned string").intern() == new String("Interned string").intern());
        /*
        false
        true
         */
    }
}
