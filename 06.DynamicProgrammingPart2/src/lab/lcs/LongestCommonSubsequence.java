package lab.lcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestCommonSubsequence {
    private static char[] seq1;
    private static char[] seq2;
    private static int[][] lcsTable;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        seq1 = reader.readLine().toCharArray();
        seq2 = reader.readLine().toCharArray();
        lcsTable = new int[seq1.length][seq2.length];

        System.out.println(findLCS(seq1.length - 1, seq2.length - 1));
    }

    private static int findLCS(int x, int y) {
        if (x < 0 || y < 0) {
            return 0;
        }

        if (lcsTable[x][y] == 0) {
            lcsTable[x][y] = Math.max(findLCS(x - 1, y), findLCS(x, y - 1));

            if (seq1[x] == seq2[y]) {
                lcsTable[x][y] = Math.max(lcsTable[x][y], 1 + findLCS(x - 1, y - 1));
            }
        }
        return lcsTable[x][y];
    }
}
