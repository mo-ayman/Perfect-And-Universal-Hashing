package IHashing.StatticPHT;

import IHashing.Item;
import IHashing.UniversalHashFnGenerator;

import java.lang.reflect.Array;
import java.util.Arrays;

public class StaticPerfectHashTable_nSquareSpace<T> implements StaticPerfectHashTable<T>{
    private Byte[][] fn;
    private Item<T>[] table;
    private int b_locDimension;
    private UniversalHashFnGenerator generator;
    public StaticPerfectHashTable_nSquareSpace() {
        this.fn = new Byte[0][0];
        this.table = new Item[0];
        this.b_locDimension = 0;
        generator = new UniversalHashFnGenerator();
    }
    private int hash(int x){
        Byte[] keyVector = generator.decompose(x);
        Byte[] locVector = new Byte[this.b_locDimension];
        Arrays.fill(locVector, Byte.parseByte("0"));
        for (int j = 0; j < keyVector.length; j++) {
            if (keyVector[j] == 1) {
                for (int k = 0; k < this.b_locDimension; k++) {
                    int val = locVector[k].intValue() ^ this.fn[k][j] ;
                    locVector[k] = Byte.parseByte(Integer.toString(val));
                }
            }
        }
        return generator.aggregate(locVector);
    }

    @Override
    public int construct(Item[] items) {
        while(Math.pow(2, this.b_locDimension) < Math.pow(items.length, 2))
            this.b_locDimension++;
        int[] keys = new int[items.length];
        for(int i = 0; i < items.length; i++) keys[i] = items[i].getKey();
        generator.setRequisites(32, this.b_locDimension,keys);
        this.fn = generator.generateFn(false);
        this.table = new Item[(int)Math.pow(2, this.b_locDimension)];
        for(int i = 0; i < items.length; i++) this.table[hash(keys[i])] = items[i];
        return generator.getCountOfRehashing();
    }

    @Override
    public Item<T> contain(int key) {
        return new Item<>(key, table[hash(key)].getValue());
    }

    public Item<T>[] getTable() {
        return table;
    }
}
