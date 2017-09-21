import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Evacuation {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] exitTokens = reader.readLine().split(" ");
        int[] exits = new int[exitTokens.length];
        int[][] graph = new int[n][n];

        for (int i = 0; i < exitTokens.length; i++) {
            exits[i] = Integer.parseInt(exitTokens[i]);
        }

        int m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            String[] egdesTokens = reader.readLine().split(" ");
            String[] minutesTokens = egdesTokens[2].split(":");
            int seconds = Integer.parseInt(minutesTokens[0]) * 60 + Integer.parseInt(minutesTokens[1]);
            graph[Integer.parseInt(egdesTokens[0])][Integer.parseInt(egdesTokens[1])] = seconds;
            graph[Integer.parseInt(egdesTokens[1])][Integer.parseInt(egdesTokens[0])] = seconds;
        }

        String[] timeTokens = reader.readLine().split(":");
        int time = Integer.parseInt(timeTokens[0]) * 60 + Integer.parseInt(timeTokens[1]);

        int maxEvacuationTime = -1;
        int maxEvacuationRoom = -1;
        Map<Integer, Integer> unsafeRooms = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int minTime = Integer.MAX_VALUE;
            for (int exit : exits) {
                Integer crntTime = Dijkstra.dijkstraAlgorithm(graph, i, exit);
                minTime = Math.min(minTime, crntTime);
            }

            if (minTime <= time){
                if(maxEvacuationTime < minTime){
                    maxEvacuationTime = minTime;
                    maxEvacuationRoom = i;
                }
            } else {
                unsafeRooms.put(i, minTime);
            }
        }

        StringBuilder sb = new StringBuilder();
        if(unsafeRooms.isEmpty()){
            sb.append("Safe\n").append(
                    String.format("%d (%02d:%02d:%02d)%n",
                            maxEvacuationRoom, maxEvacuationTime/3600, maxEvacuationTime/60, maxEvacuationTime%60));
            System.out.println(sb.toString());
        } else {
            sb.append("Unsafe\n");
            for(Map.Entry<Integer, Integer> room : unsafeRooms.entrySet()) {
                if (room.getValue() == Integer.MAX_VALUE) {
                    sb.append(String.format("%d (unreachable), ", room.getKey()));
                } else {
                    sb.append(String.format
                            ("%d (%02d:%02d:%02d), ",
                                    room.getKey(), room.getValue() / 3600, room.getValue() / 60, room.getValue() % 60));
                }
            }
            sb.setLength(sb.length()-2);
            System.out.println(sb.toString());
        }
    }
}
