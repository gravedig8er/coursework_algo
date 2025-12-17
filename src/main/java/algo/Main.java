package algo;

import algo.map.MyHashMap;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();

        /*
        map.insert("виноград", 122);
        map.insert("клубника", 4);
        map.insert("арбуз", 10);
        map.insert("дыня", 2);
         */

        map.insert(99, 123);
        map.insert(1123, 123);
        map.insert(100111, 123);
        map.insert(1231212123, 777);
        map.insert(23, 123);


        System.out.println(map.getCapacity() + " capacity");
        System.out.println();

        for (MyHashMap.Entity<Integer, Integer> stringIntegerEntity : map) {
            System.out.println(stringIntegerEntity.getKey() + " " + stringIntegerEntity.getValue());
        }

        System.out.println();

        System.out.println(map.size() + " size");
        map.delete(99);
        System.out.println(map.size() + " size");
        System.out.println();
        for (MyHashMap.Entity<Integer, Integer> stringIntegerEntity : map) {
            System.out.println(stringIntegerEntity.getKey() + " " + stringIntegerEntity.getValue());
        }

        System.out.println();

        System.out.println(map.getCapacity());
        System.out.println();

        map.insert(99, 111);
        for (MyHashMap.Entity<Integer, Integer> stringIntegerEntity : map) {
            System.out.println(stringIntegerEntity.getKey() + " " + stringIntegerEntity.getValue());
        }

        System.out.println();

        System.out.println(map.containsKey(1123));
        System.out.println(map.containsKey(228));

        System.out.println("value = " + map.get(1231212123) + " 1231212123 <= key");

        map.delete(1231212123);
        System.out.println(map.containsKey(1231212123));

    }
}