package exercise.maxTasksAssignment;

import lab.maxFlow.EdmondsKarp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MaxTasksAssignment {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int personsCount = Integer.parseInt(reader.readLine().split(": ")[1]);
        int tasksCount = Integer.parseInt(reader.readLine().split(": ")[1]);
        int[][] matrix = new int[personsCount + tasksCount + 2][personsCount + tasksCount + 2];

        for (int i = 1; i < personsCount + 1; i++) {
            matrix[0][i] = 1;
        }
        for (int i = 1 + personsCount; i < personsCount + tasksCount + 1; i++) {
            matrix[i][personsCount + tasksCount + 1] = 1;
        }

        for (int i = 1; i < 1 + personsCount; i++) {
            char[] line = reader.readLine().toCharArray();
            for (int j = 1 + personsCount; j < personsCount + tasksCount + 1; j++) {
                if (line[j - 1 - personsCount] == 'Y') {
                    matrix[i][j] = 1;
                }
            }
        }

        EdmondsKarp.findMaxFlow(matrix);
        List<List<Integer>> foundPaths = EdmondsKarp.getPaths();
        int[] finalPaths = new int[personsCount];

        for (List<Integer> path : foundPaths) {
            for (int i = 0; i < path.size()-1; i+=2) {
                finalPaths[path.get(i)-personsCount-1] = path.get(i+1);
            }
        }

        char ch = 'A';
        for (int i = 0; i < finalPaths.length; i++, ch++) {
            System.out.println(Character.toString(ch) + "-" + finalPaths[i]);
        }
    }
}
