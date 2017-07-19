package exercise.needles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NeedlesIterative {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        String[] inputLine = reader.readLine().split(" ");

        int sequenceLen = inputLine.length;
        int[] sequence = new int[sequenceLen];

        for (int i = 0; i < sequenceLen; i++) {
            sequence[i] = Integer.parseInt(inputLine[i]);
        }

        inputLine = reader.readLine().split("\\s+");
        int[] nums = new int[inputLine.length];

        for (int i = 0; i < inputLine.length; i++) {
            nums[i] = Integer.parseInt(inputLine[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            int mid = sequenceLen / 2;
            int start = 0;
            int end = sequenceLen;
            while (mid > 0 && (sequence[mid] == 0 || sequence[mid] == num)) {
                mid--;
            }
            if (sequence[mid] <= num) start = mid;
            else end = mid + 1;
            sb.append(findIndex(sequence, num, start, end)).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    private static int findIndex(int[] sequence, int num, int start, int end) {
        for (int i = start; i < end; i++) {
            int temp = i;
            while (temp + 1 < end && sequence[temp] == 0) {
                temp++;
            }
            if (sequence[temp] == 0) {
                return i;
            }
            if (sequence[temp] >= num) {
                return i;
            }
        }
        return end;
    }
}
