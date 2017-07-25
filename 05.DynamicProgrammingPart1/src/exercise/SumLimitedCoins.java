package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class SumLimitedCoins {
    private static int sum = 0;
    private static int[] coins;
    private static Set<int[]> divisions;
    private static int k = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        coins = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        sum = Integer.parseInt(reader.readLine());

        divisions = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] s1, int[] s2) {
                if (Arrays.equals(s1, s2)) {
                    return 0;
                }
                return 1;
            }
        });

        findDivison();
    }

    private static void findDivison() {
        for (int i = 1; i <= sum; i++) {
            k = i;
            generateCombinationsWithRep(new int[k], 0, 0, 0);
        }

        System.out.println(divisions.size());
    }

    private static void generateCombinationsWithRep(int[] crntDivision, int index, int start, int crntSum) {
        if (index == k) {
            if (crntSum == sum) {
                int[] newArr = new int[k];
                System.arraycopy(crntDivision, 0, newArr, 0, k);
                divisions.add(newArr);
            }
        } else {
            for (int i = start; i < coins.length; i++) {
                crntSum += coins[i];
                crntDivision[index] = coins[i];
                generateCombinationsWithRep(crntDivision, index + 1, i, crntSum);
                crntSum -= coins[i];
            }
        }
    }
}
