package IHashing.DynamicPHT;

import IHashing.Item;

public interface DynamicPerfectHashTable<T> {
    int insert(Item<T> item);
    Item<T> contain(int key);
    Item<T> delete(int key);
}
