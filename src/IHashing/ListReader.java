package IHashing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListReader {
    private String[] list;
    private List<Item<String>> items;

    public ListReader() {
        this.list = new String[0];
        this.items = new ArrayList<>();
    }
    public Item<String>[] read(){
        Scanner reader1 = new Scanner(System.in);
        System.out.print("enter a list to read in the following format: \n" +
                "{<k1, val1>, <k2, val2>, <.., ..>, <.., ..>}\n"+ "....do not press enter till you finish " +
                "; so that all input is read\n" + "=> ");
        String input = reader1.nextLine();
        input = input.replace(" ", "");
        input = input.replace("{", "");
        input = input.replace("}", "");
        this.list = input.split(">,<");
        for(int i = 0; i < this.list.length; i++){
             this.list[i] = this.list[i].replace("<", "");
             this.list[i] = this.list[i].replace(">", "");
            String[] pair = this.list[i].split(",");
             Item item = new Item(Integer.parseInt(pair[0]), pair[1]);
             this.items.add(item);
        }
        Item<String>[] ret = new Item[this.list.length];
        for(int i = 0; i < this.list.length; i++) ret[i] = this.items.get(i);
//        reader1.close();
        return ret;
    }
}
