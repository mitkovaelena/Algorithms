import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sowing {
    private static String[] field;
    private static int n;
    private static StringBuilder plantings;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        field = reader.readLine().split(" ");
        plantings = new StringBuilder();

        plant(0, field, 0);

        System.out.println(plantings.toString().trim());
    }

    private static void plant(int ind, String[] crntField, int sewedCount) {
        if (ind == field.length) {
            if (sewedCount == n) {
                for (int i = 0; i < crntField.length; i++) {
                    plantings.append(crntField[i]);
                }
                plantings.append("\n");
            }
            return;
        }

        if (sewedCount == n) {
            for (int i = 0; i < crntField.length; i++) {
                plantings.append(crntField[i]);
            }
            plantings.append("\n");
            return;
        }

        if (canPlant(crntField, ind)) {
            String prev = crntField[ind];
            crntField[ind] = ".";
            plant(ind + 1, crntField, sewedCount + 1);
            crntField[ind] = prev;
            if (sewedCount < n) {
                plant(ind + 1, crntField, sewedCount);
            }
        } else {
            plant(ind + 1, crntField, sewedCount);
        }
    }

    private static boolean canPlant(String[] crntField, int ind) {
        return ind < field.length && (crntField[ind].equals("1") && ((ind == 0) || !crntField[ind - 1].equals(".")));
    }
}
