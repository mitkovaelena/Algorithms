import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ChildAndToy {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] dimensions = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] energyNeeded = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int minEnergy = 0;
        for (int i = 0; i < dimensions[1]; i++) {
            int[] ropeTokens = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            minEnergy += Math.min(energyNeeded[ropeTokens[0]-1], energyNeeded[ropeTokens[1]-1]);
        }
        System.out.println(minEnergy);
    }
}
