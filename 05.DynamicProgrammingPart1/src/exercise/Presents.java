package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presents {
    private static int sum = 0;
    private static int[] presents;
    private static List<int[]> divisions;
    private static int k = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        presents = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        sum = Arrays.stream(presents).sum();
        divisions = new ArrayList<>();

        findDivison();
    }

    private static void findDivison() {
        for (int i = presents.length; i > 0; i--) {
            k = i;
            generateCombinationsWithoutRep(new int[presents.length], new boolean[presents.length], 0, 0);
        }

        int minDifference = Integer.MAX_VALUE;
        int firstPart = 0;
        int[] bestDivision = null;
        for (int[] division : divisions) {
            int divisionSum = Arrays.stream(division).sum();
            int difference = sum - 2 * divisionSum;

            if (Math.abs(difference) < Math.abs(minDifference)) {
                minDifference = difference;
                bestDivision = division;
                firstPart = divisionSum;
            }
        }
        System.out.println("Difference: " + Math.abs(minDifference));
        if (minDifference > 0) {
            StringBuilder sb = new StringBuilder();
            for (int present : bestDivision) {
                if (present != 0)
                    sb.insert(0, present + " ");
            }
            System.out.printf("Alan:%d Bob:%d\nAlan takes: %s\nBob takes the rest.",
                    firstPart, sum - firstPart, sb.toString());
        } else {
            List<Integer> bestDivisionList = new ArrayList<>();
            for (int p : presents) {
                bestDivisionList.add(p);
            }
            for (int p : bestDivision) {
                bestDivisionList.remove(Integer.valueOf(p));
            }
            StringBuilder sb = new StringBuilder();
            for (int present : bestDivisionList) {
                sb.insert(0, present + " ");
            }
            System.out.printf("Alan:%d Bob:%d\nAlan takes: %s\nBob takes the rest.",
                    sum - firstPart, firstPart, sb.toString());
        }
    }

    private static void generateCombinationsWithoutRep(int[] crntDivision, boolean[] used, int index, int start) {
        if (index == k) {
            int[] newArr = new int[crntDivision.length];
            System.arraycopy(crntDivision, 0, newArr, 0, crntDivision.length);
            divisions.add(newArr);
        } else {
            for (int i = start; i < presents.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    crntDivision[index] = presents[i];
                    generateCombinationsWithoutRep(crntDivision, used, index + 1, i + 1);
                    used[i] = false;
                }
            }
        }
    }
}
