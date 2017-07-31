package lab.articulationPoints;

import java.util.ArrayList;
import java.util.List;

public class ArticulationPoints {
    private static boolean[] visited;
    private static int[] depth;
    private static int[] lowpoint;
    private static Integer[] parent;
    private static List<Integer>[] graph;
    private static List<Integer> articulationPoints;

    public static List<Integer> findArticulationPoints(List<Integer>[] targetGraph) {
        articulationPoints = new ArrayList<>();
        visited = new boolean[targetGraph.length];
        depth = new int[targetGraph.length];
        lowpoint = new int[targetGraph.length];
        parent = new Integer[targetGraph.length];
        graph = targetGraph;

        findArticulationPointsDFS(0, 0);
        return articulationPoints;
    }

    private static void findArticulationPointsDFS(int node, int d) {
        visited[node] = true;
        depth[node] = d;
        lowpoint[node] = d;
        int childCount = 0;
        boolean isArticulation = false;
        for (int childNode : graph[node]) {
            if (!visited[childNode]) {
                parent[childNode] = node;
                findArticulationPointsDFS(childNode, d + 1);
                childCount = childCount + 1;
                if (lowpoint[childNode] >= depth[node]) {
                    isArticulation = true;
                }
                lowpoint[node] = Math.min(lowpoint[node], lowpoint[childNode]);
            } else if (parent[node] != null && childNode != parent[node]) {
                lowpoint[node] = Math.min(lowpoint[node], depth[childNode]);
            }
        }
        if ((parent[node] != null && isArticulation) || (parent[node] == null && childCount > 1)) {
            articulationPoints.add(node);
        }
    }
}
