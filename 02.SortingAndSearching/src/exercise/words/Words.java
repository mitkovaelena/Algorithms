package exercise.words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Words {
    private static int count;
    private static char[] inputStr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        inputStr = reader.readLine().toCharArray();
        if (optimization()) {
            return;
        }

        generatePermutations(0);
        System.out.println(count);
    }

    private static boolean optimization() {
        Set<Character> uniqueElements = new HashSet<Character>();
        for (Character c : inputStr) {
            uniqueElements.add(c);
        }

        if (uniqueElements.size() == inputStr.length) {
            System.out.println(fact(uniqueElements.size()));
            return true;
        }
        return false;
    }

    private static int fact(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }

        return result;
    }

    private static void generatePermutations(int ind) {
        if (ind == inputStr.length) {
            if (isValid()) {
                count++;
            }
            return;
        }

        Set<Character> swapped = new HashSet<Character>();     //generate Permutations without repetition
        for (int i = ind; i < inputStr.length; i++) {
            if (!swapped.contains(inputStr[i])) {
                char currentSymbol = inputStr[ind];
                inputStr[ind] = inputStr[i];
                inputStr[i] = currentSymbol;

                generatePermutations(ind + 1);

                swapped.add(inputStr[ind]);

                inputStr[i] = inputStr[ind];
                inputStr[ind] = currentSymbol;
            }
        }
    }

    private static boolean isValid() {
        for (int i = 0; i < inputStr.length - 1; i++) {
            if (inputStr[i] == inputStr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
