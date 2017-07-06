package lab.allPathsInLabyrinth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class allPathsInLabyrinth {
    private static char[][] map;
    private static char[][] visitedCells;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        map = new char[n][m];
        visitedCells = new char[n][m];
        for (int i = 0; i < map.length; i++) {
            char[] line = reader.readLine().toCharArray();
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = line[j];
            }
        }

        findPath(0, 0, "");
    }

    private static void findPath(int row, int col, String path) {
        if (!isInBounds(row, col)) {
            return;
        }
        if (isExit(row, col)) {
            System.out.println(path);
        } else if (!isVisited(row, col) && isFree(row, col)) {
            markAsVisited(row, col);
            findPath(row, col + 1, path + "R");
            findPath(row + 1, col, path + "D");
            findPath(row, col - 1, path + "L");
            findPath(row - 1, col, path + "U");
            unmarkAsVisited(row, col);

        }
    }

    private static void unmarkAsVisited(int row, int col) {
        visitedCells[row][col] = '0';
    }

    private static void markAsVisited(int row, int col) {
        visitedCells[row][col] = '1';
    }

    private static boolean isFree(int row, int col) {
        return !(map[row][col] == '*');
    }

    private static boolean isVisited(int row, int col) {
        return visitedCells[row][col] == '1';
    }

    private static boolean isExit(int row, int col) {
        return map[row][col] == 'e';
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && col >= 0 && row < map.length && col < map[row].length;
    }
}