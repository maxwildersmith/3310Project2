import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class GreedyKnapsack {
    private double size, used;
    private ArrayList<Item> items;

    GreedyKnapsack(double size, ArrayList<Item> items) {
        this.size = size;
        this.items = new ArrayList<>();
        Collections.sort(items);
        int i=0;
        while(used<size&&i<items.size()){
            if(size-used<items.get(i).getWeight())
                items.get(i).setWeight(size-used);
            this.items.add(items.get(i++));
            used = Item.getTotalSize(this.items);
        }
    }

    void print(){
        System.out.println("Contents of the Knapsack:");
        for(Item i: items)
            System.out.println("\t"+i);
        System.out.println("Profit: " + Item.getTotalProfit(items));
        System.out.println("Size: "+size);
        System.out.println("Space used: "+used);
        System.out.println("Free space: "+(size-used));
    }
}
