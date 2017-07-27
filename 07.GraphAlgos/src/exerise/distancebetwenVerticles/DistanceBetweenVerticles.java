package exerise.distancebetwenVerticles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DistanceBetweenVerticles {
    private static Map<Integer, List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int p = Integer.parseInt(reader.readLine());
         graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] node = reader.readLine().split(":");
            String[] dependencies = node.length > 1 ? node[1].split(" ") : new String[0];
            List<Integer> temp = new ArrayList<>();
            for (String dependency : dependencies) {
                temp.add(Integer.parseInt(dependency));
            }
            graph.put(Integer.parseInt(node[0]), temp);
        }

        for (int i = 0; i < p; i++) {
            String[] line = reader.readLine().split("-");
            System.out.printf("{%s, %s} -> %d\n",
                    line[0], line[1],findShortestPath(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
        }
    }

    private static int findShortestPath(int start, int finish) {
        Map<Integer, Boolean> visited = new HashMap<>();
        for(Integer node : graph.keySet()){
            visited.put(node, false);
        }

        Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(start,0));
        visited.put(start,true);
        while (!queue.isEmpty()) {
            Pair<Integer,Integer> node = queue.poll();
            if(node.getKey() == finish){
                return node.getValue();
            }
            for (Integer child : graph.get(node.getKey())) {
                if (!visited.get(child)) {
                    queue.add(new Pair<>(child, node.getValue()+1));
                    visited.put(child,true);
                }
            }
        }
        return -1;
    }
}
