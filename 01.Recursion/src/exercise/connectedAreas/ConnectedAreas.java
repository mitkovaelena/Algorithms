package exercise.connectedAreas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedSet;
import java.util.TreeSet;

public class ConnectedAreas {
    private static final char WALL = '*';
    private static char[][] map;
    private static boolean[][] visitedCells;
    private static SortedSet<Area> areasFound = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int r = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());

        map = new char[r][c];
        visitedCells = new boolean[r][c];

        for (int i = 0; i < map.length; i++) {                   //readMatrix
            char[] line = reader.readLine().toCharArray();
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = line[j];
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                findAreas(i, j);
            }
        }

        System.out.println("Total areas found: " + areasFound.size());

        int i = 1;
        for (Area area : areasFound) {
            System.out.println("Area #" + i + " at (" + area.getPositionR() +
                    ", " + area.getPositionC() + "), size: " + area.getSize());
            i++;
        }
    }

    private static void findAreas(int row, int col) {
        if (map[row][col] == WALL || visitedCells[row][col]) {
            return;
        }

        Area area = new Area(row, col);
        fillArea(row, col, area);

        areasFound.add(area);
    }

    private static void fillArea(int row, int col, Area area) {
        if (!isInBounds(row, col) || visitedCells[row][col] || map[row][col] == WALL) {
            return;
        }

        visitedCells[row][col] = true;
        area.incrementSize();

        fillArea(row + 1, col, area);
        fillArea(row, col + 1, area);
        fillArea(row - 1, col, area);
        fillArea(row, col - 1, area);
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && col >= 0 && row < map.length && col < map[row].length;
    }
}
