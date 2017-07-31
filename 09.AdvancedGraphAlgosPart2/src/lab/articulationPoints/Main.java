package lab.articulationPoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer>[] graph = new ArrayList[12];
        graph[0] = new ArrayList<>(Arrays.asList(1, 2, 6, 7, 9));
        graph[1] = new ArrayList<>(Arrays.asList(0, 6));
        graph[2] = new ArrayList<>(Arrays.asList(0, 7));
        graph[3] = new ArrayList<>(Arrays.asList(4));
        graph[4] = new ArrayList<>(Arrays.asList(3, 6, 10));
        graph[5] = new ArrayList<>(Arrays.asList(7));
        graph[6] = new ArrayList<>(Arrays.asList(0, 1, 4, 8, 10, 11));
        graph[7] = new ArrayList<>(Arrays.asList(0, 2, 5, 9));
        graph[8] = new ArrayList<>(Arrays.asList(6, 11));
        graph[9] = new ArrayList<>(Arrays.asList(0, 7));
        graph[10] = new ArrayList<>(Arrays.asList(4, 6));
        graph[11] = new ArrayList<>(Arrays.asList(6, 8));

        List<Integer> articulationPoints = ArticulationPoints.findArticulationPoints(graph);
        System.out.println(articulationPoints);
    }
}
