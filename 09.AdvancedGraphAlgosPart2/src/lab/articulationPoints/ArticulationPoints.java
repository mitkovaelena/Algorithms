package lab.articulationPoints;

import javafx.util.Pair;

import java.util.*;

public class ArticulationPoints {
    private static boolean[] visited;
    private static int[] depth;
    private static int[] lowpoint;
    private static int[] parent;
    private static List<Integer>[] graph;
    private static List<Integer> articulationPoints;
    private static List<List<Integer>> biconnectedComponents = new ArrayList<>();
    private static Deque<Pair<Integer, Integer>> component = new ArrayDeque<>();

    public static List<Integer> findArticulationPoints(List<Integer>[] targetGraph) {
        articulationPoints = new ArrayList<>();
        visited = new boolean[targetGraph.length];
        depth = new int[targetGraph.length];
        lowpoint = new int[targetGraph.length];
        parent = new int[targetGraph.length];
        graph = targetGraph;

        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }

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

                component.push(new Pair<Integer, Integer>(node, childNode));
                if (lowpoint[childNode] >= depth[node]) {
                    isArticulation = true;
                    if(!component.isEmpty()) {
                        List<Integer> newComponent = new ArrayList<>();
                        Pair<Integer, Integer> edge = component.peek();
                        newComponent.add(edge.getKey());

                        do {
                            edge = component.pop();
                            newComponent.add(edge.getValue());
                        } while (!component.isEmpty() && (edge.getKey() != node || component.peek().getKey() == edge.getValue()));

                        biconnectedComponents.add(newComponent);
                    }
                }
                lowpoint[node] = Math.min(lowpoint[node], lowpoint[childNode]);
            } else if (childNode != parent[node]) {
                lowpoint[node] = Math.min(lowpoint[node], depth[childNode]);
            }
        }
        if ((parent[node] != -1 && isArticulation) || (parent[node] == -1 && childCount > 1)) {
            articulationPoints.add(node);
        }
    }

    public static List<List<Integer>> getBiconnectedComponents() {
        return biconnectedComponents;
    }
}
