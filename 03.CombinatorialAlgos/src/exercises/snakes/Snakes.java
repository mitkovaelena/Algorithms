package exercises.snakes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Snakes {
    private static List<String> foundSnakes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        findSnakes(n, "S");
        System.out.println("Snakes count = " + foundSnakes.size());
    }

    private static void findSnakes(int n, String snake) {
        if (snake.length() == n){
            if(!isFound(snake)) {
                foundSnakes.add(snake);
                System.out.println(snake);
            }
        } else if (snake.length() < n){
            if (!snake.endsWith("L")) {
                findSnakes(n, snake + "R");
            }
            if (!snake.endsWith("U")) {
                findSnakes(n, snake + "D");
            }
            if (!snake.endsWith("R")) {
                findSnakes(n, snake + "L");
            }
            if (!snake.endsWith("D")) {
                findSnakes(n, snake + "U");
            }
        }
    }

    private static boolean isFound(String snake) {
        for (String s : foundSnakes) {
            if (areEqual(snake, s)) {
                return true;
            }
        }
        return false;
    }

    private static boolean areEqual(String snake, String s) {
        if(Objects.equals(snake, s)) return true;

        for (int i = 0; i < 3; i++) {
            String rotatedSnake = rotate(snake, i);
            if (Objects.equals(s, rotatedSnake)) return true;
            String flippedSnake = horizontalFlip(rotatedSnake);
            if (Objects.equals(s, flippedSnake)) return true;
            flippedSnake = verticalFlip(rotatedSnake);
            if (Objects.equals(s, flippedSnake)) return true;
            flippedSnake = horizontalFlip(flippedSnake);
            if (Objects.equals(s, flippedSnake)) return true;
        }

        return false;
    }

    private static String rotate(String snake, int i) {
        for (int j = 0; j < i; j++) {
            snake = snake.replaceAll("R", "A").replaceAll("D", "B").replaceAll("L", "C").replaceAll("U", "E");
            snake = snake.replaceAll("A", "D").replaceAll("B", "L").replaceAll("C", "U").replaceAll("E", "R");
        }
        return snake;
    }

    private static String verticalFlip(String snake) {
        snake = snake.replaceAll("D", "T").replaceAll("U", "D").replaceAll("T", "U");
        return snake;
    }

    private static String horizontalFlip(String snake) {
        snake = snake.replaceAll("R", "T").replaceAll("L", "R").replaceAll("T", "L");
        return snake;
    }
}
