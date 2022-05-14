import IHashing.*;
import IHashing.DynamicPHT.DynPerfectHashTable_nSquareSpace;
import IHashing.DynamicPHT.DynamicPerfectHashTable;
import IHashing.StatticPHT.StaticPerfectHashTable;
import IHashing.StatticPHT.StaticPerfectHashTable_nSpace;
import IHashing.StatticPHT.StaticPerfectHashTable_nSquareSpace;

import java.util.LinkedList;
import java.util.Scanner;

public class Main<T> {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Scanner stringReader = new Scanner(System.in);
        byte option = 0;
        System.out.println("Perfect And Universal Hashing\n=============================");
        System.out.print("Select one of these types:\n 1. for Static Perfect Hashing O(N square) space method.\n" +
                " 2. for Static Perfect Hashing O(N) space method.\n" +
                " 3. for Dynamic Perfect Hashing O(N square) space method....extra.\n" +
                " 4. for exit.\n" + "=> ");
        option = reader.nextByte();
        switch (option){
            case 1 :
                StaticPerfectHashTable staticPerfectHashTableSquare = new StaticPerfectHashTable_nSquareSpace();
                ListReader listReader = new ListReader();
                int rehashingNum = staticPerfectHashTableSquare.construct(listReader.read());
                System.out.println("no. of rehashings : " + rehashingNum);
                while (true){
                    System.out.println("select an option :\n" + " 1. look for a key\n" + " 2. exit");
                    System.out.print("=> ");
//                    if(reader.hasNext()) System.out.println("kggkjjgjg");
                    int option1 = reader.nextInt();
//                    if(reader.hasNext()) reader.next();
                    switch (option1){
                        case 1 :
                            System.out.print("enter the key: ");
                            int key = reader.nextInt();
                            Item item = staticPerfectHashTableSquare.contain(key);
                            if(item == null) System.out.println("key not found!!\n");
                            else System.out.println("value: " + item.getValue());
                            break;
                        default:
                            System.exit(1);
                    }
                }
            case 2:
                StaticPerfectHashTable staticPerfectHashTable_nSpace = new StaticPerfectHashTable_nSpace<>();
                ListReader listReader1 = new ListReader();
                int rehashingNum1 = staticPerfectHashTable_nSpace.construct(listReader1.read());
                System.out.println("no. of rehashings : " + rehashingNum1);
                reader = new Scanner(System.in);
                while (true){
                    System.out.println("select an option :\n 1. look for a key\n" + " 2. exit");
                    System.out.print("=> ");
                    option = reader.nextByte();
                    switch (option){
                        case 1 :
                            System.out.print("enter the key: ");
                            int key = reader.nextInt();
                            Item item = staticPerfectHashTable_nSpace.contain(key);
                            if(item == null) System.out.println("key not found!!\n");
                            else System.out.println("value: " + item.getValue());
                            break;
                        default:
                            System.exit(1);
                    }
                }
            case 3:
                DynamicPerfectHashTable dynPerfectHashTable_nSquareSpace= new DynPerfectHashTable_nSquareSpace<>();
                while (true){
                    System.out.println("select an option :\n 1. look for a key\n" +
                            " 2. insert an item\n" + " 3. delete an item\n" + " 4. exit");
                    System.out.print("=> ");
                    option = reader.nextByte();
                    switch (option){
                        case 1:
                            System.out.print("enter the key: ");
                            int key = reader.nextInt();
                            Item item = dynPerfectHashTable_nSquareSpace.contain(key);
                            if(item == null) System.out.println("key not found!!\n");
                            else System.out.println("value: " + item.getValue());
                            break;
                        case 2:
                            System.out.print("enter an item as <key, value> : ");
                            String input = stringReader.nextLine();
                            input = input.replace(" ", "");
                            input = input.replace("<", "");
                            input = input.replace(">", "");
                            String[] pair = input.split(",");
                            int numRehashing = dynPerfectHashTable_nSquareSpace.insert(new Item(Integer.parseInt(pair[0]), pair[1]));
                            System.out.println("no. of rehashings : " + numRehashing);
                            break;
                        case 3:
                            System.out.print("enter the key: ");
                            int key2 = reader.nextInt();
                            Item item2 = dynPerfectHashTable_nSquareSpace.contain(key2);
                            if(item2 == null) System.out.println("key not found!!\n");
                            else System.out.println("value: " + item2.getValue());
                            break;
                        default:
                            System.exit(1);
                    }
                }
            default: System.exit(2);
        }
    }
}
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
//        DynPerfectHashTable_nSquareSpace<String> perfectHashTable = new DynPerfectHashTable_nSquareSpace<>();
//        int count = perfectHashTable.insert(new Item<>(2, "lo"));
//        Item<String>[] table = perfectHashTable.getTable();
//        for (int i = 0; i < table.length; i++){
//            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
//            else System.out.println("loc.: "+ i + "key: " + table[i].getKey() + " & item: " + table[i].getValue());
//        }
//        System.out.println(count);
//        System.out.println();
//        count = perfectHashTable.insert(new Item<>(6, "lol"));
//        table = perfectHashTable.getTable();
//        for (int i = 0; i < table.length; i++){
//            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
//            else System.out.println("loc.: "+ i + " & key: " + table[i].getKey() + " & item: " + table[i].getValue());
//        }
//        System.out.println(count);
//        System.out.println();
//        count = perfectHashTable.insert(new Item<>(100, "lolo"));
//        table = perfectHashTable.getTable();
//        for (int i = 0; i < table.length; i++){
//            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
//            else System.out.println("loc.: "+ i + " & key: " + table[i].getKey() + " & item: " + table[i].getValue());
//        }
//        System.out.println(count);
//        System.out.println();
//        count = perfectHashTable.insert(new Item<>(30, "lolo"));
//        table = perfectHashTable.getTable();
//        for (int i = 0; i < table.length; i++){
//            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
//            else System.out.println("loc.: "+ i + " & key: " + table[i].getKey() + " & item: " + table[i].getValue());
//        }
//        System.out.println(count);
//        System.out.println();
//        count = perfectHashTable.insert(new Item<>(25, "lolo"));
//        table = perfectHashTable.getTable();
//        for (int i = 0; i < table.length; i++){
//            if (table[i] == null) System.out.println("loc: " + i + " & item: " + table[i]);
//            else System.out.println("loc.: "+ i + " & key: " + table[i].getKey() + " & item: " + table[i].getValue());
//        }
//        System.out.println(count);
//        System.out.println();
//        Item t = perfectHashTable.contain(6);
//        System.out.println("t.value: " + t.getValue());
//
//        StaticPerfectHashTable_nSquareSpace<String> pht = new StaticPerfectHashTable_nSquareSpace<>();
//        Item<String> item1 = new Item<>(2, "gg");
//        Item<String> item2 = new Item<>(4, "hh");
//        Item<String> item3 = new Item<>(3, "gh");
//        pht.construct(new Item[]{item1, item2, item3});
//        System.out.println(pht.contain(5));
//        Item<String>[] table1 = pht.getTable();
//        for(int i= 0; i < table1.length; i++){
//            if (table1[i] == null) System.out.println("loc: " + i + " & item: " + table1[i]);
//            else System.out.println("loc.: "+ i + " & key: " + table1[i].getKey() + " & item: " + table1[i].getValue());
//        }

//        StaticPerfectHashTable_nSpace<String> phtn = new StaticPerfectHashTable_nSpace<>();
//        Item<String> it1 = new Item<>(2, "gg");
//        Item<String> it2 = new Item<>(4, "hh");
//        Item<String> it3 = new Item<>(3, "gh");
//        //Item<String> it4 = new Item<>(2, "gh");
//        Item<String> it5 = new Item<>(5, "gh");
//        int x = phtn.construct(new Item[]{it1, it2, it3, it5});
//        Item<String> fnd = phtn.contain(4);
//        System.out.println(fnd.getKey() + " " + fnd.getValue());
//        fnd = phtn.contain(2);
//        System.out.println(fnd.getKey() + " " + fnd.getValue());
//        fnd = phtn.contain(3);
//        System.out.println(fnd.getKey() + " " + fnd.getValue());
//        System.out.println(x);

//    }
//}
