package lab.connectedComponents;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ConnectedComponents {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split("\\s+");
            if ((line[0]).equals("")) {
                graph.add(new ArrayList<>());
            } else {
                List<Integer> temp = new ArrayList<>();

                for (int j = 0; j < line.length; j++) {
                    temp.add(Integer.parseInt(line[j]));
                }
                graph.add(temp);
            }
        }
        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph);
        for (Deque component : connectedComponents) {
            System.out.println("Connected component: " + Arrays.toString(component.toArray()).replaceAll("[\\[\\],]", ""));
        }
    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> connectedComponents = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                Deque<Integer> dfsOrderedNodes = new ArrayDeque<>();
                DFS(graph, i, visited, dfsOrderedNodes);
                connectedComponents.add(dfsOrderedNodes);
            }
        }
        return connectedComponents;
    }

    private static void DFS(List<List<Integer>> graph, int crntNode, boolean[] visited, Deque<Integer> dfsOrderedNodes) {
        if (!visited[crntNode]) {
            visited[crntNode] = true;
            for (int child : graph.get(crntNode)) {
                DFS(graph, child, visited, dfsOrderedNodes);
            }
            dfsOrderedNodes.add(crntNode);
        }
    }

    private static void BFS(List<List<Integer>> graph, int crntNode, boolean[] visited, Deque<Integer> bfsOrderedNodes) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(crntNode);
        visited[crntNode] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            bfsOrderedNodes.add(node);
            for (int child : graph.get(node)) {
                if (!visited[child]) {
                    queue.add(child);
                    visited[child] = true;
                }
            }
        }
    }
}
