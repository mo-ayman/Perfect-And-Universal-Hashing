package IHashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PerfectHashTable_nSquareSpace <T> implements PerfectHashTable<T>{
    private final UniversalHashFnGenerator generator;
    private int b_locationDimensions;
    private int actualSize;
    private Item[] table;
    private Byte[][] function;
    private List<Item<T>> keys;


    public PerfectHashTable_nSquareSpace() {
        this.generator = new UniversalHashFnGenerator();
        this.b_locationDimensions = 2;
        this.actualSize = 0;
        this.table = new Item[(int)(Math.pow(2, b_locationDimensions))];
        this.generator.setRequisites(32, 2, new int[0]);
        this.function = generator.generateFn(true);
        keys = new ArrayList<>();
    }

    private int hash(int x){
        Byte[] keyVector = generator.decompose(x);
        Byte[] locVector = new Byte[this.b_locationDimensions];
        Arrays.fill(locVector, Byte.parseByte("0"));
        for (int j = 0; j < keyVector.length; j++) {
            if (keyVector[j] == 1) {
                for (int k = 0; k < this.b_locationDimensions; k++) {
                    int val = locVector[k].intValue() ^ this.function[k][j] ;
                    locVector[k] = Byte.parseByte(Integer.toString(val));
                }
            }
        }
        return generator.aggregate(locVector);
    }

    public Item[] getTable() {return table;}

    @Override
    public void insert(Item<T> item) {
        keys.add(item);
        int location = hash(item.getKey());
        this.actualSize++;
        if(this.table[location] == null) this.table[location] = item;
        else { ////collision occurs....you need to rehash
            System.out.println("Collision occurs...");
            while (Math.pow(2, this.b_locationDimensions) < Math.pow(this.actualSize, 2))
                this.b_locationDimensions++;
            //////now construct a new fn. !!!
            int[] keysArray = new int[keys.size()];
            for(int i = 0; i < keysArray.length; i++) keysArray[i] = keys.get(i).getKey();
            this.generator.setRequisites(32, this.b_locationDimensions, keysArray);
            this.function = this.generator.generateFn(false);
            this.table = new Item[(int)(Math.pow(2, this.b_locationDimensions))];
            for(int i = 0; i < keys.size() ; i++)
                this.table[hash(keys.get(i).getKey())] = this.keys.get(i);
        }
    }

    @Override
    public Item<T> contain(int key) {return new Item<>(key, (T) this.table[hash(key)].getValue());}

    @Override
    public Item<T> delete(int key) {
        int loc = 0;
        for(int i = 0; i < this.keys.size(); i++) if(this.keys.get(i).getKey() == key) loc = i;
        this.keys.remove(loc);
        Item<T> item = new Item<>(key, (T) this.table[hash(key)].getValue());
        this.table[key] = null;
        return item;
    }
}
