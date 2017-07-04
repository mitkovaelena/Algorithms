package lab.arraySum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class arraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] intArr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        System.out.println(sum(intArr, 0));
    }

    private static int sum(int[] arr, int startInd) {
        if (arr.length - 1 == startInd) {
            return arr[startInd];
        }
        return arr[startInd] + sum(arr, startInd + 1);
    }
}
