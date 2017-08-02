package lab.maxFlow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class EdmondsKarp {
    private static int[] parents;
    private static int[][] graph;
    private static List<List<Integer>> paths = new ArrayList<>();

    public static int findMaxFlow(int[][] targetGraph) {
        int maxFlow = 0;
        graph = targetGraph;
        parents = new int[graph.length];
        int start = 0;
        int end = graph.length - 1;

        for (int i = 0; i < parents.length; i++) {
            parents[i] = -1;
        }

        while (BFS(start, end)) {               // While we can find a path from source to sink
            int pathFlow = Integer.MAX_VALUE;
            int crntNode = end;
            List<Integer> newPath = new ArrayList<>();
            while (crntNode != start) {
                int prevNode = parents[crntNode];
                if(crntNode != end) {
                    newPath.add(crntNode);
                }
                if (pathFlow > graph[prevNode][crntNode]) {
                    pathFlow = graph[prevNode][crntNode];
                }
                crntNode = prevNode;
            }

            paths.add(newPath);
            maxFlow += pathFlow;
            crntNode = end;
            while (crntNode != start) {
                int prevNode = parents[crntNode];
                graph[prevNode][crntNode] -= pathFlow;
                graph[crntNode][prevNode] += pathFlow;
                crntNode = prevNode;
            }

        }
        return maxFlow;
    }

    static boolean BFS(int src, int dest) {
        boolean[] visited = new boolean[graph.length];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(src);
        visited[src] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 0; i < graph[node].length; i++) {
                if (graph[node][i] != 0 && !visited[i]) {
                    queue.add(i);
                    parents[i] = node;
                    visited[i] = true;
                }
            }
        }
        return visited[dest];
    }

    public static List<List<Integer>> getPaths() {
        return paths;
    }

}

