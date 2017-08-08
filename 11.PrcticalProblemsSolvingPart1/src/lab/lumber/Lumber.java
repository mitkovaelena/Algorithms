package lab.lumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Lumber {
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputTokens = reader.readLine().split("\\s+");
        int n = Integer.parseInt(inputTokens[0]);
        int m = Integer.parseInt(inputTokens[1]);

        Rectangle[] rectangles = new Rectangle[n];
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            inputTokens = reader.readLine().split("\\s+");
            int x1 = Integer.parseInt(inputTokens[0]);
            int y1 = Integer.parseInt(inputTokens[1]);
            int x2 = Integer.parseInt(inputTokens[2]);
            int y2 = Integer.parseInt(inputTokens[3]);
            Rectangle rectangle = new Rectangle(x1, y1, x2, y2);
            graph.add(new ArrayList<>());
            for (int j = 0; j < rectangles.length; j++) {
                if (rectangles[j] != null && rectangle.intersects(rectangles[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
            rectangles[i] = rectangle;
        }

        boolean[] visited = new boolean[graph.size()];
        int[] componentId = new int[n];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                BFS(graph, i, visited, componentId);
                count++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            inputTokens = reader.readLine().split("\\s+");
            int r1 = Integer.parseInt(inputTokens[0]) - 1;
            int r2 = Integer.parseInt(inputTokens[1]) - 1;

            if (componentId[r1] == componentId[r2]) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb.toString().trim());
    }

    private static void BFS(List<List<Integer>> graph, int crntNode, boolean[] visited, int[] id) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(crntNode);
        visited[crntNode] = true;
        id[crntNode] = count;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int child : graph.get(node)) {
                if (!visited[child]) {
                    queue.add(child);
                    visited[child] = true;
                    id[child] = count;
                }
            }
        }
    }
}
