import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GuitarRecursion {
    private static int[] intervals;
    private static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        intervals = Arrays.stream(reader.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int start = Integer.parseInt(reader.readLine());
        max = Integer.parseInt(reader.readLine());

        System.out.println(findMaxVolume(-1, start));
    }

    private static int findMaxVolume(int ind, int crntVolume) {
        if(crntVolume > max || crntVolume < 0){
            return -1;
        }
        if(ind+1 < intervals.length) {
            int volume1 = findMaxVolume(ind + 1, crntVolume + intervals[ind + 1]);
            int volume2 = findMaxVolume(ind + 1, crntVolume - intervals[ind + 1]);

            return Math.max(volume1, volume2);
        }
        else return crntVolume;
    }
}
