package exercise.connectingCables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ConnectingCables {
    private static int[] constSet;
    private static int[] givenSet;
    private static int[][] lcsTable;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        givenSet = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        constSet = Arrays.stream(givenSet).sorted().toArray();
        lcsTable = new int[constSet.length][givenSet.length];

        System.out.println("Maximum pairs connected: " + findLCS(constSet.length - 1, givenSet.length - 1));
    }

    private static int findLCS(int x, int y) {
        if (x < 0 || y < 0) {
            return 0;
        }

        if (lcsTable[x][y] == 0) {
            lcsTable[x][y] = Math.max(findLCS(x - 1, y), findLCS(x, y - 1));

            if (constSet[x] == givenSet[y]) {
                lcsTable[x][y] = Math.max(lcsTable[x][y], 1 + findLCS(x - 1, y - 1));
            }
        }
        return lcsTable[x][y];
    }
}
