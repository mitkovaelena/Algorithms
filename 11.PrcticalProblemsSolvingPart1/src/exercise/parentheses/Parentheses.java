package exercise.parentheses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Parentheses {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        StringBuilder sb = new StringBuilder();
        brackets(n, 0, "", sb);
        System.out.println(sb.toString().trim());
    }

    private static void brackets(int openStock, int closeStock, String s, StringBuilder sb) {
        if (openStock == 0 && closeStock == 0) {
            sb.append(s + "\n");
        }
        if (openStock > 0) {
            brackets(openStock-1, closeStock+1, s + "(", sb);
        }
        if (closeStock > 0) {
            brackets(openStock, closeStock-1, s + ")", sb);
        }
    }
}
