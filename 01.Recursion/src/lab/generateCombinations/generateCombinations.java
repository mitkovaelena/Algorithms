package lab.generateCombinations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GenerateCombinations {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] intArr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(reader.readLine());

        generateComb(intArr, new int[n], 0, -1);
    }

    private static void generateComb(int[] set, int[] vector, int index, int border) {
        if (index == vector.length) {
            System.out.println(Arrays.stream(vector).mapToObj(String::valueOf)
                    .collect(Collectors.joining(" ")));
        }
        else {
            for (int i = border + 1; i < set.length; i++) {
                vector[index] = set[i];
                generateComb(set, vector, index + 1, i);
            }
        }
    }
}
