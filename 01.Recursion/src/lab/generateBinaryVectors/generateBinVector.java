package lab.generateBinaryVectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class generateBinVector {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        generateVector(n, "");
    }

    private static void generateVector(int n, String vector) {
        if (n == vector.length()) {
            System.out.println(vector);
            return;
        }
        generateVector(n, vector + "0");
        generateVector(n, vector + "1");
    }
}
