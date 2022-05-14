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
                int rehashingNum = staticPerfectHashTableSquare.construct(listReader.randomize(2));
                int s = staticPerfectHashTableSquare.getActualSize();
                System.out.println("no. of rehashings : " + rehashingNum +
                        "\nsizeOccupied = "+ s);
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
                s = staticPerfectHashTable_nSpace.getActualSize();
                System.out.println("no. of rehashings : " + rehashingNum1 +
                        "\nsizeOccupied = "+ s);
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
