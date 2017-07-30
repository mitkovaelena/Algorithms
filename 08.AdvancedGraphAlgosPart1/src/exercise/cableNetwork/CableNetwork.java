package exercise.cableNetwork;

import lab.kruskalsAlgorithm.Edge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CableNetwork {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int budget = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int nodesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int edgesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int usedBudget = 0;
        boolean[] isConnected = new boolean[nodesCount];
        List<Edge> unconnectedEdges = new ArrayList<>();

        for (int i = 0; i < edgesCount; i++) {
            String[] edge = reader.readLine().split("\\s+");
            if (edge.length > 3) {
                isConnected[Integer.parseInt(edge[0])] = true;
                isConnected[Integer.parseInt(edge[1])] = true;
            } else {
                unconnectedEdges.add(new Edge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]), Integer.parseInt(edge[2])));
            }
        }

        unconnectedEdges = unconnectedEdges.stream().sorted().collect(Collectors.toList());
        int index = 0;
        while (index < unconnectedEdges.size()) {
            Edge edge = unconnectedEdges.get(index);
            if (edge.getWeight() + usedBudget > budget) {
                break;
            }
            if ((isConnected[edge.getStartNode()] && !isConnected[edge.getEndNode()])
                    || (!isConnected[edge.getStartNode()] && isConnected[edge.getEndNode()])) {
                usedBudget += edge.getWeight();
                isConnected[edge.getStartNode()] = true;
                isConnected[edge.getEndNode()] = true;
                index = 0;
            }
            index++;
        }
        System.out.println("Budget used: " + usedBudget);
    }
}
