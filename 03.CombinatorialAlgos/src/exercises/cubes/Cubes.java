package exercises.cubes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Cubes {
    private static final long FACTORIAL_12 = 479001600L;
        public static void main(String[] args) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] sticks = reader.readLine().split("\\s+");
            Map<String, Integer> sticksCount = new HashMap<>();

            for (String stick : sticks){
                sticksCount.putIfAbsent(stick, 0);
                sticksCount.put(stick, sticksCount.get(stick) + 1);
            }

            long cubes =  FACTORIAL_12;

            for (int count : sticksCount.values()){
                cubes/= factorial(count);
            }

            System.out.println(cubes);
    }

    private static long factorial(int count) {
            long output = 1;
        for (int i = count; i > 1; i--) {
            output *= i;
        }
        return output;
    }
}
