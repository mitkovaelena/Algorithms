package exercise.fractionalKnapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class FractionalKnapsack {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int capacity = Integer.parseInt(reader.readLine().substring(10));
        int itemsCount = Integer.parseInt(reader.readLine().substring(7));

        SortedMap<Double, Double[]> items = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < itemsCount; i++) {
            String[] inputLine = reader.readLine().split(" -> ");
            double ratio = Double.valueOf(inputLine[0]) / Double.valueOf(inputLine[1]);
            Double[] element = new Double[]{Double.valueOf(inputLine[0]), Double.valueOf(inputLine[1])};
            items.put(ratio, element);
        }

        double totalPrice = chooseItems(items, capacity);
        System.out.printf("Total price: %.2f", totalPrice);
    }

    private static double chooseItems(Map<Double, Double[]> items, int capacity) {
        double totalPrice = 0;

        for (Map.Entry<Double, Double[]> item : items.entrySet()) {
            if (item.getValue()[1] <= capacity) {
                System.out.printf("Take 100%% of item with price %.2f and weight %.2f\n", item.getValue()[0], item.getValue()[1]);
                capacity -= item.getValue()[1];
                totalPrice += item.getValue()[0];
            } else if (capacity != 0 && item.getValue()[1] > capacity) {
                double percent = capacity / item.getValue()[1];
                System.out.printf("Take %.2f%% of item with price %.2f and weight %.2f\n", percent * 100, item.getValue()[0], item.getValue()[1]);
                capacity -= item.getValue()[1] * percent;
                totalPrice += item.getValue()[0] * percent;
            }
        }
        return totalPrice;
    }
}
