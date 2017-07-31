package lab.stronglyConnectedComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer>[] graph = new ArrayList[14];
        graph[0] = new ArrayList<>(Arrays.asList(1, 11, 13));
        graph[1] = new ArrayList<>(Arrays.asList(6));
        graph[2] = new ArrayList<>(Arrays.asList(0));
        graph[3] = new ArrayList<>(Arrays.asList(4));
        graph[4] = new ArrayList<>(Arrays.asList(3, 6));
        graph[5] = new ArrayList<>(Arrays.asList(13));
        graph[6] = new ArrayList<>(Arrays.asList(0, 11));
        graph[7] = new ArrayList<>(Arrays.asList(12));
        graph[8] = new ArrayList<>(Arrays.asList(6, 11));
        graph[9] = new ArrayList<>(Arrays.asList(0));
        graph[10] = new ArrayList<>(Arrays.asList(4, 6, 10));
        graph[11] = new ArrayList<>(Arrays.asList());
        graph[12] = new ArrayList<>(Arrays.asList(7));
        graph[13] = new ArrayList<>(Arrays.asList(12, 9));

        List<List<Integer>> result = StronglyConnectedComponents.findStronglyConnectedComponents(graph);

        for (List<Integer> component : result) {
            System.out.println(component);
        }
    }
}
