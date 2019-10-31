import java.util.ArrayList;

public class Item implements Comparable<Item>{
    private double weight;
    private double profit;
    private double ratio;

    Item(double weight, double profit) {
        this.weight = weight;
        this.profit = profit;
        ratio = profit/weight;
    }

    void setWeight(double weight){
        this.weight = weight;
        profit = ratio*weight;
    }

    double getWeight() {
        return weight;
    }

    public double getProfit() {
        return profit;
    }

    private double getRatio() {
        return ratio;
    }

    static double getTotalSize(ArrayList<Item> items){
        double sum = 0;
        for(Item i: items)
            sum+=i.getWeight();
        return sum;
    }

    static double getTotalProfit(ArrayList<Item> items){
        double sum = 0;
        for(Item i: items)
            sum+=i.getProfit();
        return sum;
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                ", profit=" + profit +
                ", ratio=" + ratio +
                '}';
    }

    @Override
    public int compareTo(Item o) {
        return Double.compare(o.getRatio(),ratio);
    }
}
