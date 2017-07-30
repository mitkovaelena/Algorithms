package exerise.areasInMatrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedMap;
import java.util.TreeMap;

public class AreasInMatrix {
    private static char[][] matrix;
    private static boolean[][] isVisited;
    private static SortedMap<Character, Integer> areas = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        matrix = new char[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = reader.readLine().toCharArray();
        }
        isVisited = new boolean[n][matrix[0].length];

        System.out.println("Areas: " + findAreas());
        for (Character ch : areas.keySet()) {
            System.out.printf("Letter '%c' -> %d\n", ch, areas.get(ch));
        }
    }

    private static int findAreas() {
        int areasCount = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!isVisited[i][j]) {
                    char ch = matrix[i][j];
                    areas.putIfAbsent(ch, 0);
                    areas.put(ch, areas.get(ch) + 1);
                    areasCount++;

                    markAreaCellsVisited(i, j, ch);
                }
            }
        }
        return areasCount;
    }

    private static void markAreaCellsVisited(int row, int col, char ch) {
        isVisited[row][col] = true;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (Math.abs(i) == Math.abs(j) || row + i < 0 || row + i >= matrix.length ||
                        col + j < 0 || col + j >= matrix[0].length) continue;

                if (matrix[row + i][col + j] == ch && !isVisited[row + i][col + j]) {
                    markAreaCellsVisited(row + i, col + j, ch);
                }
            }
        }
    }
}
