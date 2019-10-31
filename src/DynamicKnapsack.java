import java.util.ArrayList;

public class DynamicKnapsack {
    int size, used;
    ArrayList<Item> items;

    public DynamicKnapsack(int size, ArrayList<Item> items) {
        this.size = size;
        this.items = new ArrayList<>();

        int i,j=0;
        double[][] V = new double[items.size()+1][size+1];
        for(i=0;i<items.size();i++)
            V[i][0] = 0;
        for(j=0;j<size;j++)
            V[0][j] = 0;
        for(i=1;i<=items.size();i++){
            for(j=1; j<=size;j++){
                if(items.get(i-1).getWeight() > j)
                    V[i][j] = V[i-1][j];
                else
                    V[i][j] = Math.max(V[i-1][j], items.get(i-1).getProfit() + V[i-1][j - (int)items.get(i-1).getWeight()]);
            }
        }
        j--;
        i--;
        for(i=i;i>0&&V[i][j]>0;i--){
            if(V[i][j]==V[i-1][j])
                continue;
            this.items.add(items.get(i-1));
            j -= items.get(i-1).getWeight();
        }

        used = (int)Item.getTotalSize(this.items);


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
