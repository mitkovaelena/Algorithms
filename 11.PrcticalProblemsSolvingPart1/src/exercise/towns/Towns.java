package exercise.towns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Towns {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] seq = new int[n];

        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(reader.readLine().split(" ")[0]);
        }

        int[] incLen = new int[seq.length];
        int[] decLen = new int[seq.length];


        int maxLen = 0;
        for (int x = 0; x < seq.length; x++) {
            incLen[x] = 1;
            for (int i = 0; i < x; i++)
                if ((seq[i] < seq[x]) && (incLen[i] + 1 > incLen[x])) {
                    incLen[x] = incLen[i] + 1;
                }
            if (incLen[x] > maxLen) {
                maxLen = incLen[x];
            }
        }

        maxLen = 0;
        for (int x = seq.length-1; x >= 0; x--) {
            decLen[x] = 0;
            for (int i = seq.length-1; i > x; i--)
                if ((seq[i] < seq[x]) && (decLen[i] + 1 > decLen[x])) {
                    decLen[x] = decLen[i] + 1;
                }
            if (decLen[x] > maxLen) {
                maxLen = decLen[x];
            }
        }

        maxLen = 0;
        for (int i = 0; i < seq.length; i++) {
            int temp = incLen[i] + decLen[i];
            if (temp > maxLen) {
                maxLen = temp;
            }
        }
        System.out.println(maxLen);
    }
}
