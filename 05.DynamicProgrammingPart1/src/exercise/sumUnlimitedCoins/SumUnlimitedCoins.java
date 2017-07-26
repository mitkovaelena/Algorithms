package exercise.sumLimitedCoins;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SumUnlimitedCoins {
    private static int sum = 0;
    private static int[] coins;
    private static int divisionsCount;
    private static int k = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        coins = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        sum = Integer.parseInt(reader.readLine());

        generateCombinationsWithRep(0, 0);
        System.out.println(divisionsCount);
    }

    private static void generateCombinationsWithRep(int start, int crntSum) {
        if (crntSum == sum) {
            divisionsCount++;
            return;
        }
        if (crntSum > sum) {
            return;
        }

        for (int i = start; i < coins.length; i++) {
            crntSum += coins[i];
            generateCombinationsWithRep(i, crntSum);
            crntSum -= coins[i];
        }
    }
}
