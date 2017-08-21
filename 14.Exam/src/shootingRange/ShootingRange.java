package shootingRange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class ShootingRange {
    private static int sum;
    private static int[] set;
    private static Set<String> variations;
    private static int[] vector;
    private static int k;
    private static int max;
    private static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split("\\s+");
        set = new int[input.length];

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        for (int i = 0; i < input.length; i++) {
            int crnt = Integer.parseInt(input[i]);
            set[i] = crnt;
            if (crnt > max) {
                max = crnt;
            }
            if (crnt < min) {
                min = crnt;
            }
        }

        sum = Integer.parseInt(reader.readLine());
        variations = new HashSet<>();

        findDivison();
    }

    private static void findDivison() {
        int multiplier = 0;
        for (int i = 1; i <= set.length; i++) {
            k = i;
            multiplier += k;
            vector = new int[k];
            if (multiplier * max < sum || multiplier * min > sum) {
                continue;
            }
            generateVariationsWithoutRep(new boolean[set.length], 0);
        }

        StringBuilder sb = new StringBuilder();
        for (String division : variations) {
            sb.append(division + "\n");
        }
        System.out.println(sb.toString().trim());
    }

    private static void generateVariationsWithoutRep(boolean[] used, int index) {
        if (index == k) {
            int s = 0;
            StringBuilder stringBuilder = new StringBuilder("");
            for (int i = 0; i < vector.length; i++) {
                s += vector[i] * (i + 1);
                stringBuilder.append(vector[i] + " ");
            }
            if (s == sum) {
                variations.add(stringBuilder.toString().trim());
            }
        } else {
            for (int i = 0; i < set.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    vector[index] = set[i];
                    generateVariationsWithoutRep(used, index + 1);
                    used[i] = false;
                }
            }
        }
    }
}
