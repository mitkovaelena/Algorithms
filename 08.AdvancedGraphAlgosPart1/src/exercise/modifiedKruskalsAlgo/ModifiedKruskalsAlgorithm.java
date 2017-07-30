package exercise.modifiedKruskalsAlgo;

import lab.kruskalsAlgorithm.Edge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ModifiedKruskalsAlgorithm {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int edgesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        List<List<Integer>> forest = new ArrayList<>();
        Queue<Edge> edges = new PriorityQueue<>();
        List<Integer> nodeTree = new ArrayList<>();

        for (int i = 0; i < nodesCount; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(i);
            forest.add(temp);
            nodeTree.add(i);
        }

        for (int i = 0; i < edgesCount; i++) {
            String[] edge = reader.readLine().split("\\s+");
            edges.add(new Edge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]), Integer.parseInt(edge[2])));
        }

        int mstWeight = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            int startNodeTree = nodeTree.get(edge.getStartNode());
            int endNodeTree = nodeTree.get(edge.getEndNode());
            if (startNodeTree != endNodeTree) {
                for (Integer node : forest.get(endNodeTree)) {
                    forest.get(startNodeTree).add(node);
                    nodeTree.set(node, startNodeTree);
                }
                forest.get(endNodeTree).clear();
                mstWeight += edge.getWeight();
            }
        }
        System.out.println("Minimum spanning forest weight: " + mstWeight);
    }
}
