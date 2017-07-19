package exercise.egyptianFractions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EgyptianFractions {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        String[] inputLine = input.split("/");
        double sum = Double.valueOf(inputLine[0])/Double.valueOf(inputLine[1]);

        if(sum >= 1){
            System.out.println("Error (fraction is equal to or greater than 1)");
            return;
        }

        List<String> summants = new ArrayList<>();

        int i = 2;
        double factor = Math.pow(10, 15);
        while ( Math.round(sum * factor)/factor > 0){
            double fraction = 1.0/i;
            if(Math.round(sum * factor)/factor >= Math.round(fraction*factor)/factor) {
                sum -= fraction;
                summants.add("1/" + i);
                i = (int) Math.floor(1.0 / sum);
            } else {
                i ++;
            }
        }

        System.out.print(input + " = " + summants.stream().map(String::valueOf)
                .collect(Collectors.joining(" + ")));

    }
}
