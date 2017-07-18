package lab.sumOfCoins;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = reader.readLine().substring(7).split(", ");
        int[] coins = new int[elements.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }

        int targetSum = Integer.parseInt(reader.readLine().substring(5));
        Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);

        int sum = usedCoins.values().stream().mapToInt(Integer::valueOf).sum();
        System.out.println("Number of coins to take: " + sum);
        for(Integer coin : usedCoins.keySet()){
            System.out.println(usedCoins.get(coin) + " coin(s) with value " + coin);
        }
    }

    public static Map<Integer, Integer> chooseCoins(int[] elements, int targetSum) {
        SortedSet<Integer> coins = new TreeSet<>(Comparator.reverseOrder());
        for (int i = 0; i < elements.length; i++) {
            coins.add(elements[i]);
        }
        
        Map<Integer, Integer> usedCoins = new TreeMap<>(Comparator.reverseOrder());
            for(Integer coin : coins){
                int count = targetSum/coin;
                if(count > 0) {
                    usedCoins.putIfAbsent(coin, 0);
                    usedCoins.put(coin, usedCoins.get(coin) + count);
                    targetSum -= coin * count;
                }
            }

        if(targetSum != 0)
            throw new IllegalArgumentException();
        return usedCoins;
    }
}
