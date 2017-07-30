package lab.dijkstrasAlgorithm;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static int[][] GRAPH =
            {
                    // 0   1   2   3   4   5   6   7   8   9  10  11
                    {0, 0, 0, 0, 0, 0, 10, 0, 12, 0, 0, 0}, // 0
                    {0, 0, 0, 0, 20, 0, 0, 26, 0, 5, 0, 6}, // 1
                    {0, 0, 0, 0, 0, 0, 0, 15, 14, 0, 0, 9}, // 2
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0}, // 3
                    {0, 20, 0, 0, 0, 5, 17, 0, 0, 0, 0, 11}, // 4
                    {0, 0, 0, 0, 5, 0, 6, 0, 3, 0, 0, 33}, // 5
                    {10, 0, 0, 0, 17, 6, 0, 0, 0, 0, 0, 0}, // 6
                    {0, 26, 15, 0, 0, 0, 0, 0, 0, 3, 0, 20}, // 7
                    {12, 0, 14, 0, 0, 3, 0, 0, 0, 0, 0, 0}, // 8
                    {0, 5, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0}, // 9
                    {0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0}, // 10
                    {0, 6, 9, 0, 11, 33, 0, 20, 0, 0, 0, 0}, // 11
            };

    public static void main(String[] args) {
        printPath(GRAPH, 0, 9);
    }

    private static void printPath(int[][] graph, int sourceNode, int destinationNode) {
        System.out.println(String.format("Shortest path [%d -> %d]: ", sourceNode, destinationNode));

        List<Integer> path = Dijkstra.dijkstraAlgorithm(graph, sourceNode, destinationNode);

        if (path == null) {
            System.out.println("no path");
        } else {
            int pathLength = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                pathLength += graph[path.get(i)][path.get(i + 1)];
            }
            System.out.println(String.format("%s (length %d)", Arrays.toString(path.toArray()), pathLength));
        }
    }
}
