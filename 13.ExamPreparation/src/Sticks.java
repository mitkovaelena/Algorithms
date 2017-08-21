import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sticks {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int sticksCount = Integer.parseInt(reader.readLine());
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < sticksCount; i++) {
            graph.put(i, new ArrayList<>());
        }
        int placingsCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < placingsCount; i++) {
            String[] placing = reader.readLine().split(" ");
            graph.get(Integer.parseInt(placing[0])).add(Integer.parseInt(placing[1]));
        }

        List<Integer> sortedNodes = topSort(graph);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sortedNodes.size(); i++) {
            sb.append(sortedNodes.get(i) + " ");
        }

        System.out.println(sb.toString().trim());
    }

    public static List<Integer> topSort(Map<Integer, List<Integer>> graph) {
        List<Integer> sortedNodes = new ArrayList<>();
        Map<Integer, List<Integer>> incomingNodes = new TreeMap<>(Comparator.reverseOrder());
        Map<Integer, Boolean> isVisited = new HashMap<>();
        int visitedCount = 0;

        for (Integer node : graph.keySet()) {
            incomingNodes.putIfAbsent(node, new ArrayList<>());
            isVisited.putIfAbsent(node, false);
            for (Integer dependency : graph.get(node)) {
                incomingNodes.putIfAbsent(dependency, new ArrayList<>());
                incomingNodes.get(dependency).add(node);
            }
        }

        while (visitedCount < isVisited.size()) {
            boolean hasChanged = false;
            for (Integer node : incomingNodes.keySet()) {
                if (!isVisited.get(node) && incomingNodes.get(node).isEmpty()) {
                    sortedNodes.add(node);
                    hasChanged = true;
                    isVisited.put(node, true);
                    visitedCount++;
                    for (Integer dependency : graph.get(node)) {
                        incomingNodes.get(dependency).remove(node);
                    }
                    break;
                }
            }
            if (!hasChanged) {
                System.out.println("Cannot lift all sticks");
                break;
            }
        }
        return sortedNodes;
    }
}
