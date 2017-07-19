package exercise.needles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NeedlesBinarySearch {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        String[] line = reader.readLine().split("\\s+");
        int[] sequence = new int[line.length];

        for (int i = 0; i < line.length; i++) {
            sequence[i] = Integer.parseInt(line[i]);
        }

        line = reader.readLine().split("\\s+");
        int[] nums = new int[line.length];

        for (int i = 0; i < line.length; i++) {
            nums[i] = Integer.parseInt(line[i]);
        }

        findIndexes(sequence, nums);
    }

    private static void findIndexes(int[] sequence, int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(binarySearch(sequence, num, 0, sequence.length - 1)).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    private static int binarySearch(int[] sequence, int key, int lo, int hi) {

        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            while (mid > 0 && sequence[mid] == 0) {
                mid--;
            }

            if (mid == 0 && (sequence[mid] == 0 || sequence[mid] >= key)) {
                return mid;
            }
            if (mid == sequence.length - 1 && sequence[mid] < key) {
                return mid + 1;
            }

            if (key < sequence[mid]) {
                if (mid >= 1 && sequence[mid - 1] != 0 && sequence[mid - 1] < key) {
                    return mid;
                }
                return binarySearch(sequence, key, lo, mid);

            } else if (key > sequence[mid]) {
                int temp = mid;
                while (mid + 2 < sequence.length && sequence[mid + 1] == 0) {
                    mid++;
                }
                if (sequence[mid + 1] >= key) {
                    return temp + 1;
                }
                return binarySearch(sequence, key, mid + 1, hi);

            } else {
                while (mid >= 0 && (sequence[mid] == key || sequence[mid] == 0)) {
                    mid--;
                }
                return mid + 1;
            }
        }

        if (lo == hi && sequence[lo] == key) {
            while (lo >= 0 && (sequence[lo] == key || sequence[lo] == 0)) {
                lo--;
            }
            return lo + 1;
        }
        if (lo == hi && sequence[lo] > key) {
            return lo;
        }
        return lo + 1;
    }
}
