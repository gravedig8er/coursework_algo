package algo;

import algo.map.MyHashMap;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        System.out.println(arrayList.remove(Integer.valueOf(1)));


    }
}