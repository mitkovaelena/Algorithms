package lab.parkingZones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParkingZones {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int zonesCount = Integer.parseInt(reader.readLine());
        List<Zone> zones = new ArrayList<>();

        for (int i = 0; i < zonesCount; i++) {
            String[] inputTokens = reader.readLine().split(": ");
            String zoneName = inputTokens[0];
            String[] coordinatesPrice = inputTokens[1].split(", ");
            Zone zone = new Zone(zoneName, Integer.parseInt(coordinatesPrice[0]),
                    Integer.parseInt(coordinatesPrice[1]),
                    Integer.parseInt(coordinatesPrice[2]),
                    Integer.parseInt(coordinatesPrice[3]),
                    Double.parseDouble(coordinatesPrice[4]));
            zones.add(zone);
        }

        String[] freeSpacesTokens = reader.readLine().split("; ");
        int[][] freeSpaces = new int[freeSpacesTokens.length][2];

        for (int i = 0; i < freeSpaces.length; i++) {
            String[] coordinates = freeSpacesTokens[i].split(", ");
            freeSpaces[i][0] = Integer.parseInt(coordinates[0]);
            freeSpaces[i][1] = Integer.parseInt(coordinates[1]);
        }

        String[] targetTokens = reader.readLine().split(", ");
        int[] target = {Integer.parseInt(targetTokens[0]), Integer.parseInt(targetTokens[1])};
        double k = Integer.parseInt(reader.readLine())/60d;

        int[] bestPlace = new int[2];
        double price = Integer.MAX_VALUE;
        double distance = 0;
        Zone zone = null;

        for (int i = 0; i < freeSpaces.length; i++) {
            for (Zone z: zones){
                if (z.containsSpace(freeSpaces[i])){
                    int crntDist = Math.abs(target[0] - freeSpaces[i][0]) + Math.abs(target[1] - freeSpaces[i][1])-1;
                    double crntPrice = Math.ceil(crntDist*2*k)*z.getPrice();
                    if(crntPrice < price || crntPrice == price && crntDist < distance ){
                        price = crntPrice;
                        zone = z;
                        bestPlace = freeSpaces[i];
                        distance = crntDist;
                    }
                    break;
                }
            }
        }

        System.out.printf("Zone Type: %s; X: %d; Y: %d; Price: %.2f", zone.getType(), bestPlace[0],
                bestPlace[1], price);
    }
}
