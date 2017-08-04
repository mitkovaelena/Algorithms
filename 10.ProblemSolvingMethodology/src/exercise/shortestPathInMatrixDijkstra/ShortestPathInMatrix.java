package exercise.shortestPathInMatrixDijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestPathInMatrix {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        List<Integer>[] graph = new ArrayList[n * m];
        Map<Integer, Cell> cellIndex = new HashMap<>();
        for (int j = 0; j < n * m; j++) {
            graph[j] = new ArrayList<>();
        }
        int index = 0;
        for (int i = 0; i < n; i++) {
            String[] inputTokens = reader.readLine().split("\\s+");
            for (int j = 0; j < m; j++) {
                int weight = Integer.parseInt(inputTokens[j]);
                cellIndex.put(index, new Cell(index, weight));
                if (index - 1 >= 0 && index - 1 < n * m && index % m != 0) {
                    graph[index].add(index - 1);
                    graph[index - 1].add(index);
                }
                if (index - m >= 0 && index - m < n * m) {
                    graph[index].add(index - m);
                    graph[index - m].add(index);
                }
                index++;
            }
        }

        List<Integer> shortestPath = Dijkstra.dijkstraAlgorithm(graph, 0, n * m - 1, cellIndex);

        StringBuilder sb = new StringBuilder();
        long pathLen = 0;
        for (Integer cell : shortestPath) {
            int len = cellIndex.get(cell).getWeight();
            sb.append(len + " ");
            pathLen += len;
        }
        System.out.println("Length: " + pathLen);
        System.out.println("Path: " + sb.toString().trim());
    }
}
