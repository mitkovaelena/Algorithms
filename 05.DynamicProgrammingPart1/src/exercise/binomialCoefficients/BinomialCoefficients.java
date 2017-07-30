package exercise.binomialCoefficients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinomialCoefficients {
    private static long[][] coefficients;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());
        coefficients = new long[n + 1][n + 1];

        for (int i = 0; i < n + 1; i++) {
            coefficients[i][0] = 1;
            coefficients[i][i] = 1;
        }
        System.out.println(calculateCoeff(n, k));
    }

    private static long calculateCoeff(int n, int k) {
        if (coefficients[n][k] != 0) {
            return coefficients[n][k];
        }
        coefficients[n][k] = calculateCoeff(n - 1, k - 1) + calculateCoeff(n - 1, k);
        coefficients[n][n - k] = coefficients[n][k];
        return coefficients[n][k];
    }
}
