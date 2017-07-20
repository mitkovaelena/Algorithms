package exercise.knightsTour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KnightsTour {
    private static final int[] moveX = new int[]{1, -1, 1, -1, 2, 2, -2, -2};
    private static final int[] moveY = new int[]{2, +2, -2, -2, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int[][] chessboard = new int[n][n];
        int i = 1;
        int x = 0;
        int y = 0;

        while (i <= n * n) {
            chessboard[x][y] = i;

            int[] nextStepCoordinates = findNextStep(chessboard, x, y);
            x = nextStepCoordinates[0];
            y = nextStepCoordinates[1];
            i++;
        }

        StringBuilder sb = new StringBuilder();
        for (int[] row : chessboard) {
            for (int col : row) {
                sb.append(String.format("%3d ", col));
            }
            sb.setLength(sb.length() - 1);
            sb.append("\n ");
        }
        System.out.println(sb.toString().trim());
    }

    private static int[] findNextStep(int[][] chessboard, int x, int y) {
        int minSteps = Integer.MAX_VALUE;
        int[] coordinates = new int[]{x, y};

        for (int i = 0; i < 8; i++) {
            if (x + moveX[i] < chessboard.length && x + moveX[i] >= 0 &&
                    y + moveY[i] < chessboard.length && y + moveY[i] >= 0
                    && chessboard[x + moveX[i]][y + moveY[i]] == 0) {
                int steps = countPossibleSteps(chessboard, x + moveX[i], y + moveY[i]);
                if (steps < minSteps) {
                    minSteps = steps;
                    coordinates[0] = x + moveX[i];
                    coordinates[1] = y + moveY[i];
                }
            }
        }

        return coordinates;
    }

    private static int countPossibleSteps(int[][] chessboard, int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (x + moveX[i] < chessboard.length && x + moveX[i] >= 0 &&
                    y + moveY[i] < chessboard.length && y + moveY[i] >= 0
                    && chessboard[x + moveX[i]][y + moveY[i]] == 0) {
                count++;
            }
        }
        return count;
    }
}

