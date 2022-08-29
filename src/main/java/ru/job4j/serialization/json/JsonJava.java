package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonJava {
    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonEngine = new JSONObject("{\"type\":\"Petrol\", \"power\":\"296\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Luke");
        list.add("ABS");
        list.add("GPS");
        JSONArray jsonOptions = new JSONArray(list);

        /* JSONObject напрямую методом put */
        Car car = new Car(true, 2008, "BMW X7",
                new Engine("Diesel", 300), new String[]{"AUX", "Глонасс"});
        JSONObject jsonCar = new JSONObject();
        jsonCar.put("leftHand", car.isLeftHand());
        jsonCar.put("year", car.getYear());
        jsonCar.put("model", car.getModel());
        jsonCar.put("engine", jsonEngine);
        jsonCar.put("options", jsonOptions);

        /* Выведем результат в консоль */
        System.out.println(jsonCar.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(car).toString());
    }
}
