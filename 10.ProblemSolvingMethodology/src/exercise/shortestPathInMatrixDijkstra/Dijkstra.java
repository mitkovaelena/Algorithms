package exercise.shortestPathInMatrixDijkstra;

import java.util.*;

public class Dijkstra {

    public static List<Integer> dijkstraAlgorithm(List<Integer>[] graph, int sourceNode, int destinationNode, Map<Integer, Cell> cells) {
        List<Integer> shortestPath = new ArrayList<>();
        Integer[] distances = new Integer[graph.length];
        Integer[] prev = new Integer[graph.length];
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> distances[i]));

        for (int i = 0; i < distances.length; i++) {
            if (i == sourceNode) {
                distances[i] = 0;
            } else {
                distances[i] = Integer.MAX_VALUE;
            }
        }
        queue.add(sourceNode);

        while (!queue.isEmpty()) {
            int minNode = queue.poll();
            visited[minNode] = true;
            if (distances[minNode] == Integer.MAX_VALUE) {
                break;
            }

            for (Integer cellIndex : graph[minNode]) {
                if (!visited[cellIndex]) {
                    queue.add(cellIndex);
                    int newDist = distances[minNode] + cells.get(cellIndex).getWeight();
                    distances[cellIndex] = Math.min(distances[cellIndex], newDist);

                    if (newDist == distances[cellIndex]) {
                        prev[cellIndex] = minNode;
                        queue.remove(cellIndex);
                        queue.add(cellIndex);
                    }
                }
            }
        }

        if (distances[destinationNode] == Integer.MAX_VALUE) {
            return null;
        }

        while (prev[destinationNode] != null) {
            shortestPath.add(0, destinationNode);
            destinationNode = prev[destinationNode];
        }
        shortestPath.add(0, sourceNode);

        return shortestPath;
    }
}