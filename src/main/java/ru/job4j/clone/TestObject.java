package ru.job4j.clone;

public class TestObject implements Cloneable {
    int num;
    InnerObject innerObj;

    @Override
    protected TestObject clone() throws CloneNotSupportedException {
        TestObject testObj = (TestObject) super.clone();
        testObj.innerObj = innerObj.clone();
        return testObj;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
//        TestObject testObj1 = new TestObject();
//        testObj1.num = 5;
//        TestObject testObj2 = testObj1;
//        testObj2.num = 10;
//        System.out.println(testObj1.num);
        /*
        10
         */
//        TestObject testObj3 = new TestObject();
//        testObj3.num = 5;
//        TestObject testObj4 = testObj3.clone();
//        testObj4.num = 10;
//        System.out.println(testObj3.num);
//        System.out.println(testObj4.num);
        /*
        Shallow copy
        5
        10
         */
        TestObject testObj5 = new TestObject();
        testObj5.num = 5;
        InnerObject innerObj = new InnerObject();
        innerObj.num = 15;
        testObj5.innerObj = innerObj;
        TestObject testObj6 = testObj5.clone();
        testObj6.num = 25;
        testObj6.innerObj.num = 35;
        System.out.println("Исходный класс: " + testObj5.num);
        System.out.println("Исходный класс: " + testObj5.innerObj.num);
        System.out.println("Скопированный класс: " + testObj6.num);
        System.out.println("Скопированный класс: " + testObj6.innerObj.num);
        /*
        Deep copy
        Исходный класс: 5
        Исходный класс: 15
        Скопированный класс: 25
        Скопированный класс: 35
         */
    }
}
