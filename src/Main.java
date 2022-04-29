import IHashing.*;
import IHashing.DynamicPHT.DynPerfectHashTable_nSquareSpace;
import IHashing.StatticPHT.StaticPerfectHashTable;
import IHashing.StatticPHT.StaticPerfectHashTable_nSquareSpace;

public class Main<T>{

    public static void main(String[] args) {
//        UniversalHashFnGenerator generator = new UniversalHashFnGenerator();
//        generator.setRequisites(32, 3, new int[]{2, 4, 5, 100, 3, 7, 98, 50});
//        Byte[][] mat = generator.generateFn(false);
//        for(int i = 0; i  <mat.length; i++){
//            for(int j = 0; j < mat[0].length; j++)
//                System.out.print(mat[i][j] + " ");
//            System.out.println();
//        }
//        Byte[] loc = generator.getLocations();
//        for(int i = 0; i < loc.length; i++){
//            System.out.println(loc[i]);
//        }
        DynPerfectHashTable_nSquareSpace<String> perfectHashTable = new DynPerfectHashTable_nSquareSpace<>();
        int count = perfectHashTable.insert(new Item<>(2, "lo"));
        Item<String>[] table = perfectHashTable.getTable();
        for (int i = 0; i < table.length; i++){
            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
            else System.out.println("loc.: "+ i + "key: " + table[i].getKey() + " & item: " + table[i].getValue());
        }
        System.out.println(count);
        System.out.println();
        count = perfectHashTable.insert(new Item<>(6, "lol"));
        table = perfectHashTable.getTable();
        for (int i = 0; i < table.length; i++){
            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
            else System.out.println("loc.: "+ i + " & key: " + table[i].getKey() + " & item: " + table[i].getValue());
        }
        System.out.println(count);
        System.out.println();
        count = perfectHashTable.insert(new Item<>(100, "lolo"));
        table = perfectHashTable.getTable();
        for (int i = 0; i < table.length; i++){
            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
            else System.out.println("loc.: "+ i + " & key: " + table[i].getKey() + " & item: " + table[i].getValue());
        }
        System.out.println(count);
        System.out.println();
        count = perfectHashTable.insert(new Item<>(30, "lolo"));
        table = perfectHashTable.getTable();
        for (int i = 0; i < table.length; i++){
            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
            else System.out.println("loc.: "+ i + " & key: " + table[i].getKey() + " & item: " + table[i].getValue());
        }
        System.out.println(count);
        System.out.println();
        count = perfectHashTable.insert(new Item<>(25, "lolo"));
        table = perfectHashTable.getTable();
        for (int i = 0; i < table.length; i++){
            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
            else System.out.println("loc.: "+ i + " & key: " + table[i].getKey() + " & item: " + table[i].getValue());
        }
        System.out.println(count);
        System.out.println();
        Item t = perfectHashTable.contain(6);
        System.out.println("t.value: " + t.getValue());

        StaticPerfectHashTable_nSquareSpace<String> pht = new StaticPerfectHashTable_nSquareSpace<>();
        Item<String> item1 = new Item<>(2, "gg");
        Item<String> item2 = new Item<>(4, "hh");
        Item<String> item3 = new Item<>(3, "gh");
        pht.construct(new Item[]{item1, item2, item3});
        System.out.println(pht.contain(2).getValue());
        Item<String>[] table1 = pht.getTable();
        for(int i= 0; i < table1.length; i++){
            if (table1[i] == null) System.out.println("loc: " + i + " & item: " + table1[i]);
            else System.out.println("loc.: "+ i + " & key: " + table1[i].getKey() + " & item: " + table1[i].getValue());
        }
    }
}
