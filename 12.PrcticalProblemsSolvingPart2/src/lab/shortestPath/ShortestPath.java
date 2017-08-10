package lab.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShortestPath {
    private static String path;
    private static int len;
    private static int starsCount;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        path = reader.readLine();
        List<String> directions = new ArrayList<>();
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '*') {
                starsCount++;
            }
        }

        findPaths(0, "", directions);
        System.out.println(len);
        StringBuilder sb = new StringBuilder();
        for (String direction : directions) {
            String temp = new StringBuilder(path).toString();
            for (int j = 0; j < direction.length(); j++) {
                temp = temp.replaceFirst("\\*", String.valueOf(direction.charAt(j)));
            }
            sb.append(temp + "\n");
        }
        System.out.println(sb);
    }

    private static void findPaths(int ind, String dirStr, List<String> directions) {
        if (ind == starsCount) {
            directions.add(dirStr);
            len++;
        } else if (ind < starsCount) {
            findPaths(ind + 1, dirStr + "L", directions);
            findPaths(ind + 1, dirStr + "R", directions);
            findPaths(ind + 1, dirStr + "S", directions);
        }
    }
}
