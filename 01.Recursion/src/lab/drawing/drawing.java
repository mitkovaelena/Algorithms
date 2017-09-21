package lab.drawing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class Drawing {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        drawFigure(n);
    }

    private static void drawFigure(int n) {

        if (n == 0) {
            return;
        }

        System.out.println(String.join("", Collections.nCopies(n, "*")));
        drawFigure(n - 1);
        System.out.println(String.join("", Collections.nCopies(n, "#")));
    }
}
