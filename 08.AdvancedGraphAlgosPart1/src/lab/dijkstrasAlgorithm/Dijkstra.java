package lab.dijkstrasAlgorithm;

import java.util.*;

public class Dijkstra {

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
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

            for (int i = 0; i < graph.length; i++) {
                if (graph[minNode][i] != 0 && !visited[i]) {
                    queue.add(i);
                    int newDist = distances[minNode] + graph[minNode][i];
                    distances[i] = Math.min(distances[i], newDist);

                    if (newDist == distances[i]) {
                        prev[i] = minNode;
                        queue.remove(i);
                        queue.add(i);
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
