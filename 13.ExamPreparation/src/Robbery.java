import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Robbery {
    private static char WHITE = 'w';
    private static int waitingEnergy;
    private static Map<Integer, Boolean> cellColor;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] points = reader.readLine().split(" ");
        cellColor = new HashMap<>();

        for (int i = 0; i < points.length; i++) {
            cellColor.put(i, points[i].charAt(points[i].length() - 1) == WHITE);
        }

        int startingEnergy = Integer.parseInt(reader.readLine());
        waitingEnergy = Integer.parseInt(reader.readLine());
        int start = Integer.parseInt(reader.readLine());
        int end = Integer.parseInt(reader.readLine());
        int edgesCount = Integer.parseInt(reader.readLine());

        int[][] graph = new int[points.length][points.length];

        for (int i = 0; i < edgesCount; i++) {
            String[] edge = reader.readLine().split("\\s+");
            graph[Integer.parseInt(edge[0])][Integer.parseInt(edge[1])] = Integer.parseInt(edge[2]);
        }

        Integer shortestPath = dijkstraAlgorithm(graph, start, end);
        if (startingEnergy - shortestPath > 0) {
            System.out.println(startingEnergy - shortestPath);
        } else {
            System.out.println("Busted - need " + (shortestPath - startingEnergy) + " more energy");
        }
    }

    public static Integer dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
        Integer[] distances = new Integer[graph.length];
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> distances[i]));

        for (int i = 0; i < distances.length; i++) {
            if (i == sourceNode) {
                distances[i] = 0;
            } else {
                distances[i] = Integer.MAX_VALUE;
            }
        }
        queue.add(sourceNode);

        while (!queue.isEmpty()) {
            int minNode = queue.poll();
            visited[minNode] = true;
            if (distances[minNode] == Integer.MAX_VALUE || minNode == destinationNode) {
                break;
            }

            for (int i = 0; i < graph.length; i++) {
                if (graph[minNode][i] != 0 && !visited[i]) {
                    queue.add(i);
                    int newDist = distances[minNode] + graph[minNode][i];

                    if ((!cellColor.get(minNode) && !cellColor.get(i)) || (cellColor.get(minNode) && cellColor.get(i))) {
                        newDist += waitingEnergy;
                        turn();
                    }

                    distances[i] = Math.min(distances[i], newDist);
                    queue.remove(i);
                    queue.add(i);
                }
            }
        }
        return distances[destinationNode];
    }

    private static void turn() {
        for (Integer cell : cellColor.keySet()) {
            cellColor.put(cell, !cellColor.get(cell));
        }
    }
}