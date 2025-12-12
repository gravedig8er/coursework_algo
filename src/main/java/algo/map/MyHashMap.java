package algo.map;

import java.util.Iterator;

public class MyHashMap<K, V> implements IMap<K, V>, Iterable<MyHashMap.Entity<K, V>> {

    public static class Entity<K, V> { // КЛАСС-СУЩНОСТЬ ОДНОГО ЭЛЕМЕНТА МАССИВА
        K key;
        V value;
        boolean isDeleted;

        Entity(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private class MyIterator implements Iterator<Entity<K, V>> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            while (currentIndex < capacity) {
                if (table[currentIndex] != null && !table[currentIndex].isDeleted) {
                    return true;
                }
                currentIndex++;
            }
            return  false;
        }

        @Override
        public Entity<K, V> next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return table[currentIndex++];
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
        Entity<K, V> entry = findEntity(key); // вернет объект или null TODO: ДАМИР, ПРОВЕРЬ ФУНКЦИИ ПРОБИРОВАНИЯ, НУЖНО ВОЗВРАЩАТЬ ССЫЛКУ НА ОРИГУ
    // смотри чтобы мы в ней могли выбрать по флагу например какой-то способ пробирования!!! (сделать отдельную переменную в классе, которая обозначает пробирование)

        if (entry == null || entry.isDeleted) return null;
        V value = entry.value;

        entry.isDeleted = true;
        entry.value = null; // ни в коем случае не зануляем ключ
        size--;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() { // функция для обновления размерности
        Entity<K, V>[] oldTable = this.table; // сохраняем для копирования

        capacity *= 2;
        table = (Entity<K, V>[]) new Entity[capacity];

        size = 0;
        for (Entity<K, V> oldEntry: oldTable) {
            if (oldEntry != null && !oldEntry.isDeleted) {
                insert(oldEntry.key, oldEntry.value);
            }
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode());
    }

    @Override
    public Iterator<Entity<K, V>> iterator() {
        return new MyIterator();
    }
}
