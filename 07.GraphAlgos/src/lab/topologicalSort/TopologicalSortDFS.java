package lab.topologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopologicalSortDFS {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Map<String, List<String>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] node = reader.readLine().split(" ->");
            String[] dependencies = node.length > 1 ? node[1].split(", ") : new String[0];
            List<String> temp = new ArrayList<>();
            for (String dependency : dependencies) {
                temp.add(dependency.trim());
            }
            graph.put(node[0], temp);
        }

        List<String> sortedNodes = topSort(graph);
        System.out.println("Topological sorting: " + Arrays.toString(sortedNodes.toArray()).replaceAll("[\\[\\]]", ""));
    }

    public static List<String> topSort(Map<String, List<String>> graph) {
        List<String> sortedNodes = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Set<String> cycles = new HashSet<>();

        for (String node : graph.keySet()) {
            topSortDFS(graph, node, visited, cycles, sortedNodes);
        }

        return sortedNodes;
    }

    private static void topSortDFS(Map<String, List<String>> graph, String node, Set<String> visited, Set<String> cycles, List<String> sortedNodes) {
        if (cycles.contains(node)) {
            throw new IllegalArgumentException();
        }
        if (!visited.contains(node)) {
            visited.add(node);
            cycles.add(node);
            for (String child : graph.get(node)) {
                topSortDFS(graph, child, visited, cycles, sortedNodes);
            }
            cycles.remove(node);
            sortedNodes.add(0, node);
        }
    }
}
