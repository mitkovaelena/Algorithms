package lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Combinations {
    private static String[] set;
    private static String[] vector;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        set = reader.readLine().split("\\s+");
        k = Integer.parseInt(reader.readLine());
        vector = new String[k];


        //generateCombinationsWithoutRep(new boolean[set.length],   0, 0);
        generateCombinationsWithRep(0, 0);
    }

    private static void generateCombinationsWithoutRep(boolean[] used, int index, int start) {
        if (index == k) {
            System.out.println(Arrays.stream(vector)
                    .collect(Collectors.joining(" ")));
        } else {
            for (int i = start; i < set.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    vector[index] = set[i];
                    generateCombinationsWithoutRep(used, index + 1, i + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void generateCombinationsWithRep(int index, int start) {
        if (index == k) {
            System.out.println(Arrays.stream(vector)
                    .collect(Collectors.joining(" ")));
        } else {
            for (int i = start; i < set.length; i++) {
                vector[index] = set[i];
                generateCombinationsWithRep(index + 1, i);
            }
        }
    }
}

