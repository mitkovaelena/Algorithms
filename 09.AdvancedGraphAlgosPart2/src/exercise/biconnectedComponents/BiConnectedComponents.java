package exercise.biconnectedComponents;

import lab.articulationPoints.ArticulationPoints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BiConnectedComponents {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodesCount = Integer.parseInt(reader.readLine().split(": ")[1]);
        int edgesCount = Integer.parseInt(reader.readLine().split(": ")[1]);
        List<Integer>[] graph = new ArrayList[nodesCount];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            int parent = Integer.parseInt(tokens[0]);
            int child = Integer.parseInt(tokens[1]);

            graph[parent].add(child);
            graph[child].add(parent);
        }
        ArticulationPoints.findArticulationPoints(graph);
        List<List<Integer>> foundComponents = ArticulationPoints.getBiconnectedComponents();
        System.out.println("Number of bi-connected components: " + foundComponents.size());
        //System.out.println(Arrays.toString(foundComponents.toArray()));
    }
}
