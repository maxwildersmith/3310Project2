import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.jar.JarFile;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n###########################################\nCS3310 Project #2\nTannaz Damavandi\nby Maximum Wilder-Smith\n###########################################\n");

        System.out.println(readItemsFromText());
        //task1();
        task2();

        System.out.println("\n\nHave a Nice Day!");
    }

    static void task1(){
        System.out.println("Starting Task #1 - Fractional Knapsack");
        boolean run = true;
        while(run) {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the size of the Knapsack: ");
            try {
                double size = in.nextDouble();
                if (size < 0)
                    throw new InputMismatchException();
                boolean input = true;
                ArrayList<Item> items = new ArrayList<>();
                while (input) {
                    System.out.println("Enter the weight of the item: ");
                    double weight = in.nextDouble();
                    if (weight < 0)
                        throw new InputMismatchException();
                    System.out.println("Enter the profit of the item: ");
                    double profit = in.nextDouble();
                    if (profit < 0)
                        throw new InputMismatchException();
                    items.add(0, new Item(weight, profit));
                    System.out.println("Added " + items.get(0) + "\nEnter another? (y/n)");
                    input = in.next().trim().toLowerCase().charAt(0) == 'y';
                }
                GreedyKnapsack knapsack = new GreedyKnapsack(size, items);
                System.out.println("Created knapsack: ");
                knapsack.print();
                System.out.println("Run again? (y/n)");
                run = in.next().trim().toLowerCase().charAt(0) == 'y';
            } catch (InputMismatchException e) {
                System.out.println("Please only enter positive numeric values");
            }
        }
    }

    static void task2(){
        System.out.println("Starting Task #2 - 0/1 Knapsack");
        boolean run = true;
        while(run) {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the size of the Knapsack: ");
            try {
                int size = in.nextInt();
                if (size < 0)
                    throw new InputMismatchException();

                System.out.println("Read from file? (y/n): ");
                boolean input = in.next().trim().toLowerCase().charAt(0) != 'y';
                ArrayList<Item> items = null;
                while(items==null&&!input)
                    items = readItemsFromText();
                items = new ArrayList<>();
                while (input) {
                    System.out.println("Enter the weight of the item: ");
                    double weight = in.nextDouble();
                    if (weight < 0)
                        throw new InputMismatchException();
                    System.out.println("Enter the profit of the item: ");
                    double profit = in.nextDouble();
                    if (profit < 0)
                        throw new InputMismatchException();
                    items.add(0, new Item(weight, profit));
                    System.out.println("Added " + items.get(0) + "\nEnter another? (y/n)");
                    input = in.next().trim().toLowerCase().charAt(0) == 'y';
                }
                DynamicKnapsack knapsack = new DynamicKnapsack(size, items);
                System.out.println("Created knapsack: ");
                knapsack.print();
                System.out.println("Run again? (y/n)");
                run = in.next().trim().toLowerCase().charAt(0) == 'y';
            } catch (InputMismatchException e) {
                System.out.println("Please only enter positive numeric values");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    static ArrayList<Item> readItemsFromText() throws FileNotFoundException {
        JFileChooser fileChooser = new JFileChooser("input.txt");
        fileChooser.setFileFilter(new FileNameExtensionFilter("txt"));
        int returnVal = fileChooser.showOpenDialog(null);
        Scanner file;
        if(returnVal == JFileChooser.APPROVE_OPTION)
            file = new Scanner(fileChooser.getSelectedFile());
        else
            return null;
        ArrayList<Item> items = new ArrayList<>();
        while(file.hasNextLine()){
            String[] item = file.next().split(",");
            items.add(new Item(Double.parseDouble(item[0]),Double.parseDouble(item[1])));
        }
        return items;
    }
}
