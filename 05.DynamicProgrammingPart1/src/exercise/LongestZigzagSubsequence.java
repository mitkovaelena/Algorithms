package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LongestZigzagSubsequence {                     //only SLS case implemented
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] seq = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] len = new int[seq.length];
        int[] prev = new int[seq.length];

        int maxLen = 0;
        int lastIndex = -1;
        for (int x = 0; x < seq.length; x++) {
            boolean isEven = false;
            len[x] = 1;
            prev[x] = -1;
            for (int i = 0; i < x; i++)
                if ((!isEven && (seq[i] < seq[x]) && (len[i] + 1 > len[x])) || (isEven && (seq[i] > seq[x]) && (len[i] + 1 > len[x]))) {
                    len[x] = len[i] + 1;
                    prev[x] = i;
                    isEven = !isEven;
                }
            if (len[x] > maxLen) {
                maxLen = len[x];
                lastIndex = x;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (lastIndex != -1) {
            sb.insert(0, seq[lastIndex] + " ");
            lastIndex = prev[lastIndex];
        }
        System.out.println(sb.toString().trim());
    }
}
