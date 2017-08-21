package chainLightning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChainLightning {
    private static Integer[][] graph;
    private static Map<Integer, Integer> damages;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());
        int lightnings = Integer.parseInt(reader.readLine());
        graph = new Integer[nodes][nodes];
        damages = new HashMap<>();

        for (int i = 0; i < nodes; i++) {
            damages.put(i, 0);
        }

        for (int i = 0; i < edges; i++) {
            String[] edgeTokens = reader.readLine().split(" ");
            graph[Integer.parseInt(edgeTokens[0])][Integer.parseInt(edgeTokens[1])] = Integer.parseInt(edgeTokens[2]);
            graph[Integer.parseInt(edgeTokens[1])][Integer.parseInt(edgeTokens[0])] = Integer.parseInt(edgeTokens[2]);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lightnings; i++) {
            String[] lightningTokens = reader.readLine().split(" ");
            max = Math.max(max, strike(Integer.parseInt(lightningTokens[0]), Integer.parseInt(lightningTokens[1])));
        }

        System.out.println(max);
    }

    private static int strike(int district, int damage) {
        int maxDamage = Integer.MIN_VALUE;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(district);
        damages.put(district, damages.get(district) + damage);
        if (maxDamage < damages.get(district)) {
            maxDamage = damages.get(district);
        }
        for (int i = 0; i < graph[district].length; i++) {
            if (graph[district][i] != null) {
                queue.add(new Edge(i, graph[district][i], damage / 2));
            }
        }

        while (visited.size() < graph.length && !queue.isEmpty()) {
            Edge crntEdge = queue.poll();
            if (visited.contains(crntEdge.end)) {
                continue;
            }
            damages.put(crntEdge.end, damages.get(crntEdge.end) + crntEdge.damage);
            if (maxDamage < damages.get(crntEdge.end)) {
                maxDamage = damages.get(crntEdge.end);
            }
            visited.add(crntEdge.end);
            for (int i = 0; i < graph[crntEdge.end].length; i++) {
                if (!visited.contains(i) && graph[crntEdge.end][i] != null) {
                    queue.add(new Edge(i, graph[crntEdge.end][i], crntEdge.damage / 2));
                }
            }
        }

        return maxDamage;
    }

    public static class Edge implements Comparable<Edge> {
        private int end;
        private int distance;
        private int damage;

        public Edge(int end, int distance, int damage) {
            this.end = end;
            this.distance = distance;
            this.damage = damage;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }
    }
}
