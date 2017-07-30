package lab.kruskalsAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KruskalAlgorithm {

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {
        List<Edge> spanningTree = new ArrayList<>();
        edges = edges.stream().sorted().collect(Collectors.toList());
        int[] parent = new int[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = i;
        }

        for (Edge edge : edges) {
            int startNodeRoot = findRoot(edge.getStartNode(), parent);
            int endNodeRoot = findRoot(edge.getEndNode(), parent);
            if (startNodeRoot != endNodeRoot) {
                spanningTree.add(edge);
                parent[endNodeRoot] = startNodeRoot;
            }
        }

        return spanningTree;
    }

    private static int findRoot(int node, int[] parent) {
        while (parent[node] != node) {
            node = parent[node];
        }

        return node;
    }
}
