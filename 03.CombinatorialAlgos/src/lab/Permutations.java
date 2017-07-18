package lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Permutations {
    private static String[] set;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        set = reader.readLine().split("\\s+");
        int n = set.length;

        //generatePermutationsWithoutRep(0);
        generatePermutationsWithRep(0);
    }

    private static void generatePermutationsWithoutRep(int index) {
        if (index >= set.length) {
            System.out.println(Arrays.stream(set)
                    .collect(Collectors.joining(" ")));
        } else {
            generatePermutationsWithoutRep(index + 1);
            for (int i = index + 1; i < set.length; i++) {

                String temp = set[i];
                set[i] = set[index];
                set[index] = temp;

                generatePermutationsWithoutRep(index + 1);

                set[index] = set[i];
                set[i] = temp;
            }
        }
    }

    private static void generatePermutationsWithRep(int index) {
        if (index >= set.length) {
            System.out.println(Arrays.stream(set)
                    .collect(Collectors.joining(" ")));
        } else {
            HashSet<String> swapped = new HashSet<>();

            for (int i = index; i < set.length; i++) {
                if (!swapped.contains(set[i])) {
                    String temp = set[i];
                    set[i] = set[index];
                    set[index] = temp;

                    generatePermutationsWithRep(index + 1);

                    set[index] = set[i];
                    set[i] = temp;
                    swapped.add(set[i]);
                }
            }
        }
    }
}