package lab.starsInTheCube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class StarsInTheCube {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[][][] cube = new String[n][n][n];

        Map<String, Integer> letters = new TreeMap<>();
        int starsCount = 0;

        for (int i = 0; i < n; i++) {
            String[] side = reader.readLine().split(" \\| ");
            for (int j = 0; j < n; j++) {
                cube[j][i] = side[j].split(" ");
            }
        }

        for (int a = 1; a < n - 1; a++) {
            for (int b = 1; b < n - 1; b++) {
                for (int c = 1; c < n - 1; c++) {
                    if (Objects.equals(cube[a][b][c], cube[a][b][c - 1]) && Objects.equals(cube[a][b][c], cube[a][b][c + 1]) &&
                            Objects.equals(cube[a][b][c], cube[a - 1][b][c]) && Objects.equals(cube[a][b][c], cube[a + 1][b][c]) &&
                            Objects.equals(cube[a][b][c], cube[a][b - 1][c]) && Objects.equals(cube[a][b][c], cube[a][b + 1][c])) {
                        String letter = cube[a][b][c];
                        letters.putIfAbsent(letter, 0);
                        letters.put(letter, letters.get(letter) + 1);
                        starsCount++;
                    } else if (!Objects.equals(cube[a][b][c], cube[a][b][c + 1])) {
                        c++;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(starsCount).append("\n");
        for (String letter : letters.keySet()) {
            sb.append(String.format("%s -> %d%n", letter, letters.get(letter)));
        }
        System.out.println(sb.toString().trim());
    }
}
