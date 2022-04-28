import IHashing.*;

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
        PerfectHashTable_nSquareSpace<String> perfectHashTable = new PerfectHashTable_nSquareSpace<>();
        perfectHashTable.insert(new Item<>(2, "lo"));
        Item<String>[] table = perfectHashTable.getTable();
        for (int i = 0; i < table.length; i++){
            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
            else System.out.println("loc.: "+ i + "key: " + table[i].getKey() + " & item: " + table[i].getValue());
        }
        System.out.println();
        perfectHashTable.insert(new Item<>(6, "lol"));
        table = perfectHashTable.getTable();
        for (int i = 0; i < table.length; i++){
            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
            else System.out.println("loc.: "+ i + " & key: " + table[i].getKey() + " & item: " + table[i].getValue());
        }
        System.out.println();
        perfectHashTable.insert(new Item<>(100, "lolo"));
        table = perfectHashTable.getTable();
        for (int i = 0; i < table.length; i++){
            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
            else System.out.println("loc.: "+ i + " & key: " + table[i].getKey() + " & item: " + table[i].getValue());
        }
        System.out.println();
        perfectHashTable.insert(new Item<>(30, "lolo"));
        table = perfectHashTable.getTable();
        for (int i = 0; i < table.length; i++){
            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
            else System.out.println("loc.: "+ i + " & key: " + table[i].getKey() + " & item: " + table[i].getValue());
        }
        System.out.println();
        perfectHashTable.insert(new Item<>(25, "lolo"));
        table = perfectHashTable.getTable();
        for (int i = 0; i < table.length; i++){
            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
            else System.out.println("loc.: "+ i + " & key: " + table[i].getKey() + " & item: " + table[i].getValue());
        }
        System.out.println();
        Item t = perfectHashTable.contain(6);
        System.out.println("t.value: " + t.getValue());
    }
}
