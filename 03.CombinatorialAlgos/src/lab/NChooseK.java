package lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NChooseK {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());

        long numerator = 1;
        long denominator = 1;

        for (int i = n; i >= n - k + 1; i--) {
            numerator *= i;
        }
        for (int i = k; i > 1; i--) {
            denominator *= i;
        }

        System.out.println(numerator/denominator);
    }
}
