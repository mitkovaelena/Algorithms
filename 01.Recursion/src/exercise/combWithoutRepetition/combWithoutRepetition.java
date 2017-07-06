package exercise.combWithoutRepetition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class combWithoutRepetition {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());

        StringBuilder sb = new StringBuilder();
        generateComb(n, new int[k], 0, sb);
        System.out.println(sb.toString().trim());
    }

    private static void generateComb(int n, int[] combination, int ind, StringBuilder output) {
        if (ind == combination.length) {
            output.append(Arrays.toString(combination).replaceAll("[\\[,\\]]", "")).append("\n");
            return;
        }
        int i = ind - 1 >= 0 ? combination[ind-1]+1 : 1;
        for (; i <= n; i++) {
            combination[ind] = i;
            generateComb(n, combination, ind + 1, output);
        }
    }
}
