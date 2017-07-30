package exercise.mostReliablePath;

import lab.dijkstrasAlgorithm.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MostReliablePath {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        String[] path = reader.readLine().split(" ");
        int start = Integer.parseInt(path[1]);
        int end = Integer.parseInt(path[3]);
        int edgesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        int[][] graph = new int[nodesCount][nodesCount];

        for (int i = 0; i < edgesCount; i++) {
            String[] edge = reader.readLine().split("\\s+");
            graph[Integer.parseInt(edge[0])][Integer.parseInt(edge[1])] = (-1) * Integer.parseInt(edge[2]);  //so to use ready
            graph[Integer.parseInt(edge[1])][Integer.parseInt(edge[0])] = (-1) * Integer.parseInt(edge[2]);  //dijikstras algorithm
        }

        List<Integer> shortestPath = Dijkstra.dijkstraAlgorithm(graph, start, end);

        if(shortestPath == null){
            throw new IllegalArgumentException();
        }

        double reliability = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(shortestPath.get(0)).append(" -> ");
        int previousCity = start;
        for (int i = 1; i < shortestPath.size(); i++) {
            int city = shortestPath.get(i);
            reliability *= ((-1) * graph[previousCity][city]) * 0.01;
            sb.append(city).append(" -> ");
            previousCity = city;
        }

        sb.setLength(sb.length() - 4);
        System.out.printf("Most reliable path reliability: %.2f%%%n", reliability * 100);
        System.out.println(sb.toString());
    }
}
