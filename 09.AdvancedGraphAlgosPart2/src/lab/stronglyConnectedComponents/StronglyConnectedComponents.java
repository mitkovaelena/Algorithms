package lab.stronglyConnectedComponents;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class StronglyConnectedComponents {

    private static List<List<Integer>> stronglyConnectedComponents;
    private static List<Integer> visited;

    public static List<List<Integer>> findStronglyConnectedComponents(List<Integer>[] targetGraph) {
        stronglyConnectedComponents = new ArrayList<>();
        visited = new ArrayList<>();
        Deque<Integer> nodesStack = new ArrayDeque<>();
        for (int i = 0; i < targetGraph.length; i++) {
            if (!visited.contains(i)) {
                DFS(targetGraph, i, nodesStack);
            }
        }
        visited.clear();

        List<Integer>[] reversedGraph = reverseGraph(targetGraph);
        while (!nodesStack.isEmpty()){
            int i = nodesStack.pop();
            if (!visited.contains(i)) {
                List<Integer> component = new ArrayList<>();
                reversedDFS(reversedGraph, i, component);
                stronglyConnectedComponents.add(component);
            }
        }

        return stronglyConnectedComponents;
    }

    private static List<Integer>[] reverseGraph(List<Integer>[] targetGraph) {
        List<Integer>[] reversedGraph = new ArrayList[targetGraph.length];

        for (int i = 0; i < reversedGraph.length; i++) {
            reversedGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < targetGraph.length; i++) {
            for (int j = 0; j < targetGraph[i].size(); j++) {
                reversedGraph[targetGraph[i].get(j)].add(i);
            }
        }

        return reversedGraph;
    }

    private static void DFS(List<Integer>[] graph, int crntNode, Deque<Integer> dfsOrderedNodes) {
        if (!visited.contains(crntNode)) {
            visited.add(crntNode);
            for (int child : graph[crntNode]) {
                DFS(graph, child, dfsOrderedNodes);
            }
            dfsOrderedNodes.push(crntNode);
        }
    }

    private static void reversedDFS(List<Integer>[] graph, int crntNode, List<Integer> dfsOrderedNodes) {
        if (!visited.contains(crntNode)) {
            visited.add(crntNode);
            for (int child : graph[crntNode]) {
                reversedDFS(graph, child, dfsOrderedNodes);
            }
            dfsOrderedNodes.add(crntNode);
        }
    }
}
