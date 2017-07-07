package exercise.nestedLoops;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NestedLoops {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        generateComb(new int[n], 0);
    }

    private static void generateComb(int[] combination, int ind) {
        if (ind == combination.length) {
            System.out.println(Arrays.toString(combination).replaceAll("[\\[,\\]]", ""));
            return;
        }
        for (int i = 1; i <= combination.length; i++) {
            combination[ind] = i;
            generateComb(combination, ind+1);
        }
    }
}
