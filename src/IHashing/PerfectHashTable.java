package IHashing;

public interface PerfectHashTable<T> {
    void insert(Item<T> item);
    Item<T> contain(int key);
    Item<T> delete(int key);
}
