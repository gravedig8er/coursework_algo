package algo.map;

public class MyHashMap<T, V> implements IMap<T, V>{

    private static class Entity<T, V> { // КЛАСС-СУЩНОСТЬ ОДНОГО ЭЛЕМЕНТА МАССИВА
        T key;
        V value;
        boolean isDeleted;

        Entity(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entity<T, V>[] table;
    private int size; // реальное кол-во элементов
    private int capacity; // размер таблицы
    private double trashHold; // при каком кэфе происходит ресайз 0.75

    public MyHashMap() {
        this.capacity = 10;
        this.table = (Entity<T, V>[]) new Entity[capacity];
    }

    @Override
    public void insert(T key, V value) {
        return;
    }

    @Override
    public V search(T key) {
        return null;
    }

    @Override
    public V delete(T key) {
        return null;
    }

    @Override
    public boolean contains(T key) {
        return false;
    }
}
