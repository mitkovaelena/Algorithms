package travellingPoliceman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TravellingPoliceman {
    private static int[][] maxPrice;
    private static boolean[][] isItemTaken;
    private static List<Report> streets;
    private static int fuel;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        streets = new ArrayList<>();
        fuel = Integer.parseInt(reader.readLine());
        String inputLine = reader.readLine();

        int i = 0;
        while (!inputLine.equals("End")) {
            String[] tokens = inputLine.split(", ");

            Report street = new Report(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
            if (street.getPoints() >= 0) {
                streets.add(street);
            }

            inputLine = reader.readLine();
        }

        maxPrice = new int[streets.size()][fuel + 1];
        isItemTaken = new boolean[streets.size()][fuel + 1];

        if (streets.isEmpty()) {
            System.out.println();
            System.out.println("Total pokemons caught -> 0");
            System.out.println("Total car damage -> 0");
            System.out.println("Fuel Left -> " + fuel);
            return;
        }

        List<Report> takenItems = fillKnapsack();

        int pokemonCaught = 0;
        int carDamage = 0;
        StringBuilder sb = new StringBuilder();
        for (Report report : takenItems) {
            sb.append(report.getName() + " -> ");
            pokemonCaught += report.getPokemons();
            carDamage += report.getCarDamage();
        }
        sb.setLength(sb.length() - 4);
        System.out.println(sb.toString());
        System.out.println("Total pokemons caught -> " + pokemonCaught);
        System.out.println("Total car damage -> " + carDamage);
        System.out.println("Fuel Left -> " + fuel);
    }


    private static List<Report> fillKnapsack() {

        for (int c = 0; c <= fuel; c++) {
            if (streets.get(0).getFuel() <= c) {
                maxPrice[0][c] = streets.get(0).getPoints();
                isItemTaken[0][c] = true;
            }
        }

        for (int i = 1; i < streets.size(); i++) {
            for (int c = 0; c <= fuel; c++) {
                maxPrice[i][c] = maxPrice[i - 1][c];
                int remainingCapacity = c - streets.get(i).getFuel();
                if (remainingCapacity >= 0 && maxPrice[i - 1][remainingCapacity] + streets.get(i).getPoints() > maxPrice[i][c]) {
                    maxPrice[i][c] = maxPrice[i - 1][remainingCapacity] + streets.get(i).getPoints();
                    isItemTaken[i][c] = true;
                }
            }
        }

        List<Report> takenItems = new ArrayList<>();

        for (int i = streets.size() - 1; i >= 0; i--) {
            if (!isItemTaken[i][fuel]) continue;

            Report item = streets.get(i);
            takenItems.add(0, item);

            fuel -= item.getFuel();
        }
        return takenItems;
    }
}
