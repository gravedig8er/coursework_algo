package algo;

import algo.map.MyHashMap;


public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.insert("vlad", 1);
        map.insert("damir", 2);

        for (MyHashMap.Entity<String, Integer> stringIntegerEntity : map) {
            System.out.println(stringIntegerEntity.getKey() + " " + stringIntegerEntity.getValue());
        }
    }
}