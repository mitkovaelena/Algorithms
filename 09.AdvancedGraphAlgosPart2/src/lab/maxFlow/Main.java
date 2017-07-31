package lab.maxFlow;

public class Main {

    public static void main(String[] args) {

        int[][] graph = new int[][]
                {
                        new int[] { 0, 10, 10, 0, 0, 0 },
                        new int[] { 0, 0, 2, 4, 8, 0},
                        new int[] { 0, 0, 0, 0, 9, 0},
                        new int[] { 0, 0, 0, 0, 0, 10 },
                        new int[] { 0, 0, 0, 6, 0, 10 },
                        new int[] { 0, 0, 0, 0, 0, 0 },
                };

        int maxFlow = EdmondsKarp.findMaxFlow(graph);
        System.out.println(maxFlow);
    }
}
