package lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Variations {
    private static String[] set;
    private static String[] vector;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        set = reader.readLine().split("\\s+");
        k = Integer.parseInt(reader.readLine());
        vector = new String[k];


        //generateVariationsWithoutRep(new boolean[set.length],   0);
        generateVariationsWithRep(0);
    }

    private static void generateVariationsWithoutRep(boolean[] used, int index) {
        if (index == k) {
            System.out.println(Arrays.stream(vector)
                    .collect(Collectors.joining(" ")));
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

    private static void generateVariationsWithRep(int index) {
        if (index == k) {
            System.out.println(Arrays.stream(vector)
                    .collect(Collectors.joining(" ")));
        } else {
            for (int i = 0; i < set.length; i++) {
                vector[index] = set[i];
                generateVariationsWithRep(index + 1);
            }
        }
    }
}
