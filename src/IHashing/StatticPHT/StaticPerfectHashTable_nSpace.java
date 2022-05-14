package IHashing.StatticPHT;


import IHashing.Item;
import IHashing.UniversalHashFnGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class StaticPerfectHashTable_nSpace <T> implements StaticPerfectHashTable<T> {
    private Byte[][] fn;
//    private LinkedList[] tableA;
    // create array of linked list to store the items
    private LinkedList<Item<T>>[] tableA;
    private ArrayList<StaticPerfectHashTable_nSquareSpace<T>> tableB;
    private int b_locDimension;
    private UniversalHashFnGenerator generator;

    public StaticPerfectHashTable_nSpace() {
        this.fn = new Byte[0][0];
        this.b_locDimension = 0;
        this.tableA = new LinkedList[0];
        this.tableB = new ArrayList<>();
        generator = new UniversalHashFnGenerator();
    }


    private int hash(int x) {
        Byte[] keyVector = generator.decompose(x);
        Byte[] locVector = new Byte[this.b_locDimension];
        Arrays.fill(locVector, Byte.parseByte("0"));
        for (int j = 0; j < keyVector.length; j++) {
            if (keyVector[j] == 1) {
                for (int k = 0; k < this.b_locDimension; k++) {
                    int val = locVector[k].intValue() ^ this.fn[k][j];
                    locVector[k] = Byte.parseByte(Integer.toString(val));
                }
            }
        }
        return generator.aggregate(locVector);
    }


    @Override
    public int construct(Item[] items) {
        int length = items.length;
        for(int i = 0; i < items.length; i++){
            boolean minus = false;
            for(int j = i + 1; j < items.length; j++){
                if(items[i].getKey() == items[j].getKey()){
                    minus= true;
                    break;
                }
            }
            if(minus == true) continue;
            for(int j = 0; j < i; j++){
                if(items[i].getKey() == items[j].getKey())
                    length--;
            }
        }
        while (Math.pow(2, this.b_locDimension) < (length))
            this.b_locDimension++;
        int[] keys = new int[items.length];
        int level = 0;
        for (int i = 0; i < items.length; i++) keys[i] = items[i].getKey();
        generator.setRequisites(32, this.b_locDimension, keys);
        this.fn = generator.generateFn(true);
        this.tableA = new LinkedList[(int) Math.pow(2, this.b_locDimension)];
        for (int i = 0; i < items.length; i++) {
            int hash = hash(items[i].getKey());
            if (tableA[hash] == null) {
                tableA[hash] = new LinkedList<>();
            }
            boolean temp = true;
            for(int z = 0; z < tableA[hash].size(); z++){
                if (tableA[hash].get(z).getKey() == items[i].getKey())
                    temp = false;
            }
            if(temp == false) continue;
            this.tableA[hash].add(items[i]);
        }
        int noOfEntryRehashing = checkCollision();
        return generator.getCountOfRehashing() + noOfEntryRehashing;
    }

    private int checkCollision() {
        int noOfRehashing = 0;
        for (int i = 0; i < this.tableA.length; i++) {
            if (this.tableA[i] != null && this.tableA[i].size() > 1) {
                System.out.println("Collision at index " + i + " with size of " + this.tableA[i].size());
                StaticPerfectHashTable_nSquareSpace<T> newTable = new StaticPerfectHashTable_nSquareSpace<>();
                // Array of items to be added to the new table
                Item<T>[] items = new Item[this.tableA[i].size()];

                // add items to the new table
                for (int j = 0; j < this.tableA[i].size(); j++) {
                    items[j] =  this.tableA[i].get(j);
                }
                noOfRehashing += newTable.construct(items);
                this.tableB.add(newTable);

            }
        }

        return noOfRehashing;
    }

    @Override
    public Item<T> contain(int key) {
        int hash = hash(key);
        if (this.tableA[hash] != null && tableA[hash].size() == 1) return tableA[hash].get(0);


        for (int i = 0; i < tableB.size(); i++) {
            Item<T> item = tableB.get(i).contain(key);
            if (item != null) return item;
        }

        return null;
    }




}
