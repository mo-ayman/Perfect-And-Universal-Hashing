package IHashing.StatticPHT;

import IHashing.Item;

public interface StaticPerfectHashTable<T> {
    int construct(Item[] items);
    Item<T> contain(int key);
    int getActualSize();
}
