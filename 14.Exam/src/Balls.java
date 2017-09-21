import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Balls {
    private static int ballCount = 0;
    private static List<String> divisions = new ArrayList<>();
    private static Integer[] vector;
    private static int pockets = 0;
    private static int capacity = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        pockets = Integer.parseInt(reader.readLine());
        ballCount = Integer.parseInt(reader.readLine());
        capacity = Integer.parseInt(reader.readLine());
        vector = new Integer[pockets];

        generateCombinationsWithRep(0,0);

        StringBuilder sb = new StringBuilder();
        for (String division : divisions) {
            sb.append(division).append("\n");
        }
        System.out.println(sb.toString().trim());
    }

    private static void generateCombinationsWithRep(int index, int crntSum) {
        if (index == pockets && crntSum == ballCount) {
            StringBuilder division = new StringBuilder();

            for (int i = 0; i < vector.length; i++) {
                division.append(vector[i]).append(", ");
            }
            division.setLength(division.length()-2);
            divisions.add(division.toString());
            return;
        }
        else if (crntSum >= ballCount || index >= pockets) {
            return;
        }

        for (int i = 1; i <= capacity; i++) {
            crntSum += i;
            vector[index] = i;
            generateCombinationsWithRep(index+1, crntSum);
            crntSum -= i;
        }
    }
}
