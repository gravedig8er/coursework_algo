package algo;

import algo.map.MyHashMap;


public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.insert("арбуз", 1);
        map.insert("дыня", 2);
        map.insert("виноград", 3);
        map.insert("клубника", 4);

        for (MyHashMap.Entity<String, Integer> stringIntegerEntity : map) {
            System.out.println(stringIntegerEntity.getKey() + " " + stringIntegerEntity.getValue());
        }
    }
}