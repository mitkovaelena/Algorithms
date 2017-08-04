package exercise.shortestPathInMatrixAStar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ShortestPathInMatrixAStar {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split("\\s+");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        List<Node> shortestPath = AStar.getPath(new Node(0, 0), new Node(n - 1, m - 1), map);

        StringBuilder sb = new StringBuilder();
        long pathLen = 0;
        for (Node node : shortestPath) {
            int len = map[node.getRow()][node.getCol()];
            sb.append(len + " ");
            pathLen += len;
        }
        System.out.println("Length: " + pathLen);
        System.out.println("Path: " + sb.toString().trim());
    }
}
