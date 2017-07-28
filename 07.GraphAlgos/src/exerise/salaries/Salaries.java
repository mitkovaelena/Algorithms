package exerise.salaries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Salaries {
    private static List<List<Integer>> employeesMatrix;
    private static long[] salaries;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        employeesMatrix = new ArrayList<>();
        salaries = new long[n];
        int[] managersCount = new int[n];

        for (int i = 0; i < n; i++) {
            char[] line = reader.readLine().toCharArray();
            employeesMatrix.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (line[j] == 'Y') {
                    employeesMatrix.get(i).add(j);
                    managersCount[j]++;
                }
            }
        }

        for (int i = 0; i < managersCount.length; i++) {
            if (managersCount[i] == 0) {
                calculateSalaries(i);
            }
        }

        System.out.println(Arrays.stream(salaries).sum());
    }

    private static long calculateSalaries(int employee) {
        long currentSum = 0;
        if (salaries[employee] != 0) {
            return salaries[employee];
        }

        if (employeesMatrix.get(employee).isEmpty()) {
            salaries[employee] = 1;
            return 1;
        }

        for (int managedEmployee : employeesMatrix.get(employee)) {
            currentSum += calculateSalaries(managedEmployee);
        }
        salaries[employee] = currentSum;

        return currentSum;
    }
}
