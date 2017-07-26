package lab.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Knapsack {
    private static int[][] maxPrice;
    private static boolean[][] isItemTaken;
    private static List<Item> items = new ArrayList<>();
    private static int capacity;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        capacity = Integer.parseInt(reader.readLine());
        String[] line = reader.readLine().split(" ");
        while (line[0].compareTo("end") != 0) {
            Item item = new Item(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]));
            items.add(item);
            line = reader.readLine().split(" ");
        }

        maxPrice = new int[items.size()][capacity + 1];
        isItemTaken = new boolean[items.size()][capacity + 1];

        Set<Item> takenItems = fillKnapsack();

        System.out.printf("Total Weight: %d\nTotal Value: %d\n",
                takenItems.stream().mapToInt(Item::getWeight).sum(),
                takenItems.stream().mapToInt(Item::getValue).sum());
        for (Item i : takenItems) {
            System.out.println(i.getName());
        }
    }

    private static Set<Item> fillKnapsack() {

        for (int c = 0; c <= capacity; c++) {
            if (items.get(0).getWeight() <= c) {
                maxPrice[0][c] = items.get(0).getValue();
                isItemTaken[0][c] = true;
            }
        }

        for (int i = 1; i < items.size(); i++) {
            for (int c = 0; c <= capacity; c++) {
                maxPrice[i][c] = maxPrice[i - 1][c];
                int remainingCapacity = c - items.get(i).getWeight();
                if (remainingCapacity >= 0 && maxPrice[i-1][remainingCapacity] + items.get(i).getValue() > maxPrice[i][c]) {
                    maxPrice[i][c] = maxPrice[i-1][remainingCapacity] + items.get(i).getValue();
                    isItemTaken[i][c] = true;
                }
            }
        }

        Set<Item> takenItems = new TreeSet<>(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        int index = items.size()-1;

        while (index >= 0){
            boolean isTaken = false;
            for (int i = 0; i <= capacity; i++) {
                if (isItemTaken[index][i]){
                    isTaken = true;
                    break;
                }
            }
            if(isTaken){
                takenItems.add(items.get(index));
                capacity -= items.get(index).getWeight();
            }
            index--;
        }

        return takenItems;
    }
}

