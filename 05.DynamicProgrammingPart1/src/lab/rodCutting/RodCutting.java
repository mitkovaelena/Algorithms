package lab.rodCutting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RodCutting {
    private static int[] prices;
    private static int[] bestPrices;
    private static int[] bestCombination;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        prices = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(reader.readLine());
        bestPrices = new int[n + 1];
        bestCombination = new int[n + 1];

        System.out.println(cutRod(n));
        reconstructSolution(n);
    }

    private static int cutRod(int n) {
        for (int i = 1; i <= n; i++) {
            int currentBest = 0;
            for (int j = 1; j <= i; j++) {
                currentBest = Math.max(bestPrices[i], prices[j] + bestPrices[i - j]);
                if (currentBest > bestPrices[i]) {
                    bestPrices[i] = currentBest;
                    bestCombination[i] = j;
                }
            }
        }
        return bestPrices[n];
    }

    private static void reconstructSolution(int n) {
        while (n - bestCombination[n] != 0) {
            System.out.print(bestCombination[n] + " ");
            n = n - bestCombination[n];
        }
        System.out.println(bestCombination[n]);
    }
}
