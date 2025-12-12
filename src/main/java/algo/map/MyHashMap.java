package algo.map;

public class MyHashMap<K, V> implements IMap<K, V>{

    private static class Entity<K, V> { // КЛАСС-СУЩНОСТЬ ОДНОГО ЭЛЕМЕНТА МАССИВА
        K key;
        V value;
        boolean isDeleted;

        Entity(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entity<K, V>[] table;
    private int size; // реальное кол-во элементов
    private int capacity; // размер таблицы
    private double tresHold; // при каком кэфе происходит ресайз 0.75

    public MyHashMap() {
        this.capacity = 10;
        this.table = (Entity<K, V>[]) new Entity[capacity];
    }

    @Override
    public void insert(K key, V value) {
        return;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public V delete(K key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    private void resize() {

    }

    private int hash(K key) {
        return 0;
    }

        private int getIndex(int hash, int step) {
        return 0;
    }

}
