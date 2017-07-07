package exercise.towersOfHanoi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class towersOfHanoi {
    private static List<Integer> source = new ArrayList<>();
    private static List<Integer> destination = new ArrayList<>();
    private static List<Integer> spare = new ArrayList<>();
    private static Integer step = 1;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        sb.append("Source: ");

        for (int i = n; i > 0; i--) {
            source.add(i);
            sb.append(i).append(", ");
        }

        sb.setLength(sb.lastIndexOf(","));
        sb.append("\nDestination: \nSpare: \n\n");

        hanoiAlgo(n, source, destination, spare);
        System.out.println(sb.toString().trim());
    }

    private static void hanoiAlgo(int n, List<Integer> source, List<Integer> destination, List<Integer> spare) {
        if (n == 1) {
            move(source, destination, spare);
        } else {
            hanoiAlgo(n - 1, source, spare, destination);
            move(source, destination, spare);
            hanoiAlgo(n - 1, spare, destination, source);
        }
    }

    private static void move(List<Integer> source, List<Integer> destination, List<Integer> spare) {
        destination.add(source.remove(source.size() - 1));

        print();
    }

    private static void print() {
        sb.append("Step #").append(step++).append(": Moved disk").append("\n");
        sb.append("Source: ").append(Arrays.toString(source.toArray()).replaceAll("[\\[\\]]", "")).append("\n");
        sb.append("Destination: ").append(Arrays.toString(destination.toArray()).replaceAll("[\\[\\]]", "")).append("\n");
        sb.append("Spare: ").append(Arrays.toString(spare.toArray()).replaceAll("[\\[\\]]", "")).append("\n\n");
    }
}
