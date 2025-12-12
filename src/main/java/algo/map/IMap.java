package algo.map;

/*
* СЮДА ПИСАТЬ ВСЕ public ФУНКЦИИ
*
* */
public interface IMap<K, V> {
    void insert(K key, V value); // дамир
    V get(K key); // дамир
    boolean containsKey(K key); // дамир
    V delete(K key); // влад
    int size();
}
