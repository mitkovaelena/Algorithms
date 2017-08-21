package tourDeSofia;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TourDeSofia {
    private static int start;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int junctions = Integer.parseInt(reader.readLine());
        int streets = Integer.parseInt(reader.readLine());
        start = Integer.parseInt(reader.readLine());
        List<List<Integer>> graph = new ArrayList<>(junctions);

        for (int i = 0; i < junctions; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < streets; i++) {
            String[] line = reader.readLine().split("\\s+");
            graph.get(Integer.parseInt(line[0])).add(Integer.parseInt(line[1]));

        }
        System.out.println(getConnectedComponents(graph));
    }

    public static int getConnectedComponents(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        Set<Integer> bfsOrderedNodes = new HashSet<>();
        return BFS(graph, start, visited, bfsOrderedNodes);
    }

    private static int BFS(List<List<Integer>> graph, int crntNode, boolean[] visited, Set<Integer> bfsOrderedNodes) {
        Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(crntNode, 0));
        visited[crntNode] = true;
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> node = queue.poll();
            bfsOrderedNodes.add(node.getKey());
            for (int child : graph.get(node.getKey())) {
                if (child == start) {
                    return node.getValue() + 1;
                }
                if (!visited[child]) {
                    queue.add(new Pair(child, node.getValue() + 1));
                    visited[child] = true;
                }
            }
        }
        return bfsOrderedNodes.size();
    }
}
