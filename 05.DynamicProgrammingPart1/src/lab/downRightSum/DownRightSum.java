package lab.downRightSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DownRightSum {
    private static int[][] map;
    private static int[][] dpMatrix;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        map = new int[n][m];
        dpMatrix = new int[n][m];
        for (int i = 0; i < map.length; i++) {
            String[] line = reader.readLine().split("\\s+");
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        buildDPMatrix();
        printPath();
    }

    private static void buildDPMatrix() {
        dpMatrix[0][0] = map[0][0];

        for (int i = 1; i < map.length; i++) {
            dpMatrix[0][i] = dpMatrix[0][i - 1] + map[0][i];
        }
        for (int i = 1; i < map[0].length; i++) {
            dpMatrix[i][0] = dpMatrix[i - 1][0] + map[i][0];
        }

        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[i].length; j++) {
                dpMatrix[i][j] = Math.max(dpMatrix[i - 1][j], dpMatrix[i][j - 1]) + map[i][j];
            }
        }
    }

    private static void printPath() {
        StringBuilder sb = new StringBuilder();
        int x = dpMatrix.length - 1;
        int y = dpMatrix[0].length - 1;
        while (x >= 0 && y >= 0) {
            sb.insert(0, String.format("[%d, %d] ", x, y));
            if (x == 0) {
                y--;
                continue;
            }
            if (y == 0) {
                x--;
                continue;
            }
            if (dpMatrix[x - 1][y] > dpMatrix[x][y - 1]) {
                x--;
            } else {
                y--;
            }
        }
        System.out.println(sb);
    }
}
