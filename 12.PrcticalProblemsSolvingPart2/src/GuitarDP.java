import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GuitarDP {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] intervals = Arrays.stream(reader.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int start = Integer.parseInt(reader.readLine());
        int max = Integer.parseInt(reader.readLine());
        int[][] dpMatrix = new int[intervals.length+1][max+1];

        dpMatrix[0][start] = 1;
        for (int i = 1; i <= intervals.length; i++) {
            for (int j = 0; j < dpMatrix[i].length; j++) {
                int indexPrev = intervals[i-1];
                if (dpMatrix[i-1][j] == 1){
                    if(j+indexPrev <= max){
                        dpMatrix[i][j+indexPrev] = 1;
                    }
                    if(j-indexPrev >= 0){
                        dpMatrix[i][j-indexPrev] = 1;
                    }
                }
            }
        }


        for (int i = dpMatrix[0].length - 1; i >= 0; i--) {
            if(dpMatrix[intervals.length][i] == 1){
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}
