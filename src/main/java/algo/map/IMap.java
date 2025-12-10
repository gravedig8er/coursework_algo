package algo.map;

public interface IMap<T, V> {
    void insert(T key, V value);
    V search(T key);
    V delete(T key);
    boolean contains(T key);
}
