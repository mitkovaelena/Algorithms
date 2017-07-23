package lab.fibonacci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacci {
    private static int[] fibSequence;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        fibSequence = new int[n + 2];
        fibSequence[0] = 0;
        fibSequence[1] = 1;

        System.out.println(fibonacci(n));
    }

    private static int fibonacci(int n) {
        if (n == 0 || fibSequence[n] != 0) {
            return fibSequence[n];
        }
        fibSequence[n] = fibonacci(n - 1) + fibonacci(n - 2);
        return fibSequence[n];
    }
}
