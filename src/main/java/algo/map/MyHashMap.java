package algo.map;

import com.sun.source.tree.BreakTree;

import java.util.Iterator;
import java.util.Map;

public class MyHashMap<K, V> implements IMap<K, V>, Iterable<MyHashMap.Entity<K, V>> {

    public static class Entity<K, V> { // КЛАСС-СУЩНОСТЬ ОДНОГО ЭЛЕМЕНТА МАССИВА
        K key;
        V value;
        boolean isDeleted;

        Entity(K key, V value) {
            this.key = key;
            this.value = value;
            this.isDeleted = false;
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
    private int size; // реальное кол-во элементов, у которых isDeleted = false!!!!!!
    private int capacity; // размер таблицы
    private final double tresHold = 0.5; // при каком кэфе происходит ресайз 0.5; для вставки
    private final boolean probWay = false; // false => linear; true => quadratic

    public MyHashMap() {
        this.capacity = 10;
        this.table = (Entity<K, V>[]) new Entity[capacity];
    }

    @Override
    public void insert(K key, V value) {
        int indexArr = hash(key) % capacity;
        Entity<K, V> item = table[indexArr];
        if (item == null || item.isDeleted) { // <= тут проверили просто на null начальный индекс/isDeleted == true
            table[indexArr] = new Entity<>(key, value); // если null, то просто новый айтем кидаем в Arr
            size++;
        }
        else {
            if (item.key.equals(key)) { // <= тут проверяем дальше: если по индексу начальному айтем
                // не удален и ключ совпал то просто value меняем
                item.value = value;
                return;
            }
            else { // <= тут если у меня элемент по начальному индексу удален или ключ другой (то есть коллизия возникла)
                // ==> делаем пробирование
                if (probWay) {
                    int tmpIndex = quadraticProb(key, indexArr);
                    if (tmpIndex == -1) {
                        System.out.println("Мапа полностью заполнена");
                        return;
                    }
                    else if (table[tmpIndex] == null || table[tmpIndex].isDeleted) {
                        table[tmpIndex] = new Entity<>(key, value);
                        size++;
                    }
                    else if (table[tmpIndex].key.equals(key)) {
                        table[tmpIndex].value = value;
                        return;
                    }
                }
                else {
                    int tmpIndex = linearProb(key, indexArr);
                    if (tmpIndex == -1) {
                        System.out.println("Мапа полностью заполнена");
                        return;
                    }
                    else if (table[tmpIndex] == null || table[tmpIndex].isDeleted) {
                        table[tmpIndex] = new Entity<>(key, value);
                        size++;
                    }
                    else if (table[tmpIndex].key.equals(key)) {
                        table[tmpIndex].value = value;
                        return;
                    }
                }
            }
        }
        if (size / (double) capacity >= tresHold) { // <= ну тут просто ресайзим если коэф переполнения достиг своей планки
            resize();
        }
    }

    @Override
    public V get(K key) {
        Entity<K, V> entity = findEntity(key);
        return ((entity != null) ? entity.value : null);
    }

    // возвращает индекс подходящей для операций ячейки или -1 если не нашлось
    private int linearProb(K key, int indexArr) {
        int flagOfDel = -1;
        for (int i = 1; i < capacity; ++i) {
            int tmp = (indexArr + i) % capacity; // вот тут это обязательно нужно, потому что наша мапа кольцевая
                                          // и надо проверить как после indexArr, так и до
            if (table[tmp] == null) {
                if (flagOfDel != -1) {
                    return flagOfDel;
                }
                return tmp;
            }

            if (table[tmp].isDeleted) {
                flagOfDel = ((flagOfDel == -1) ? tmp : flagOfDel);
                continue;
            }

            if (table[tmp].key.equals(key)) {
                return tmp;
            }
        }
        // -1 может так-то в теории быть, но маловероятно, только если мапа наша заполниться полностью
        return -1;
    }

    private int quadraticProb(K key, int indexArr) {
        int flagOfDel = -1;
        int c1 = 1;
        int c2 = 1;
        for (int i = 1; i < capacity; ++i) {
            int tmp = (indexArr + c1 * i + c2 * i * i) % capacity; // вот тут это обязательно нужно, потому что наша мапа кольцевая
            // и надо проверить как после indexArr, так и до
            if (table[tmp] == null) {
                if (flagOfDel != -1) {
                    return flagOfDel;
                }
                return tmp;
            }

            if (table[tmp].isDeleted) {
                flagOfDel = ((flagOfDel == -1) ? tmp : flagOfDel);
                continue;
            }

            if (table[tmp].key.equals(key)) {
                return tmp;
            }
        }
        // -1 может так-то в теории быть, но маловероятно, только если мапа наша заполниться полностью
        return -1;
    }



    @Override
    public boolean containsKey(K key) {
        return (findEntity(key) != null);
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

    private Entity<K, V> findEntity(K key) {
        int indexArr = hash(key) % capacity;
        Entity<K, V> entity = table[indexArr];

        if (entity == null) return null;

        if (!entity.isDeleted && entity.key.equals(key)) {
            return entity;
        }
        else {
            if (probWay) {
                int tmpIndex = quadraticProb(key, indexArr);
                if (tmpIndex == -1) {
                    System.out.println("Мапа переполнена");
                    return null;
                }
                else if (table[tmpIndex] != null && !table[tmpIndex].isDeleted) {
                    return table[tmpIndex];
                }
                return null;
            }
            else {
                int tmpIndex = linearProb(key, indexArr);
                if (tmpIndex == -1) {
                    System.out.println("Мапа переполнена");
                    return null;
                }
                else if (table[tmpIndex] != null && !table[tmpIndex].isDeleted) {
                    return table[tmpIndex];
                }
                return null;
            }
        }
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
        int keyHash = Math.abs(key.hashCode());
        double A = (Math.sqrt(5) - 1) / 2; // это коэф Дональда Кнута
        return (int) Math.floor(capacity * ((keyHash * A) % 1));
    }


    @Override
    public Iterator<Entity<K, V>> iterator() {
        return new MyIterator();
    }
}
