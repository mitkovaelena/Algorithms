package exerise.cyclesInGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CyclesInGraph {
    private static Map<String, List<String>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        while (line != null && !line.equals("")) {
            String[] connection = line.split("–");
            graph.putIfAbsent(connection[0], new ArrayList<>());
            graph.putIfAbsent(connection[1], new ArrayList<>());
            graph.get(connection[0]).add(connection[1]);
            graph.get(connection[1]).add(connection[0]);

            line = reader.readLine();
        }

        Set<String> visited = new HashSet<>();
        for (String node : graph.keySet()) {
            Set<String> cycle = new HashSet<>();
            boolean cyclic = searchForCycles(node, visited, cycle, null);
            if (cyclic) {
                System.out.println("Acyclic: No");
                return;
            }
        }
        System.out.println("Acyclic: Yes");
    }

    private static boolean searchForCycles(String node, Set<String> visited, Set<String> cycle, String parent) {
        boolean output = false;
        if (cycle.contains(node)) {
            return true;
        }
        if (!visited.contains(node)) {
            visited.add(node);
            cycle.add(node);
            if (graph.containsKey(node)) {
                for (String child : graph.get(node)) {
                    if (!child.equals(parent)) {
                        output = output || searchForCycles(child, visited, cycle, node);
                    }
                }
                cycle.remove(node);
            }
        }
        return output;
    }
}