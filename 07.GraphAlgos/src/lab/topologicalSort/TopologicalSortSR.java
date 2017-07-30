package lab.topologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopologicalSortSR {
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
        Map<String, List<String>> incomingNodes = new HashMap<>();
        Map<String, Boolean> isVisited = new HashMap<>();
        int visitedCount = 0;

        for (String node : graph.keySet()) {
            incomingNodes.putIfAbsent(node, new ArrayList<>());
            isVisited.putIfAbsent(node, false);
            for (String dependency : graph.get(node)) {

                if (graph.get(dependency).contains(node)) {
                    throw new IllegalArgumentException();
                }
                incomingNodes.putIfAbsent(dependency, new ArrayList<>());
                incomingNodes.get(dependency).add(node);
            }
        }

        while (visitedCount < isVisited.size()) {
            for (String node : incomingNodes.keySet()) {
                if(!isVisited.get(node) && incomingNodes.get(node).isEmpty()){
                    sortedNodes.add(node);
                    isVisited.put(node, true);
                    visitedCount++;
                    for (String dependency : graph.get(node)) {
                        incomingNodes.get(dependency).remove(node);
                    }
                    break;
                }
            }
        }
        return sortedNodes;
    }
}
