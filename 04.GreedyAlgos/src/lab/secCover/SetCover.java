package lab.secCover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SetCover {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] elements = reader.readLine().substring(10).split(", ");
        int[] universe = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            universe[i] = Integer.parseInt(elements[i]);
        }

        int numberOfSets = Integer.parseInt(reader.readLine().substring(16));
        List<int[]> sets = new ArrayList<>();
        for (int i = 0; i < numberOfSets; i++) {
            String[] setElements = reader.readLine().split(", ");
            int[] set = new int[setElements.length];
            for (int j = 0; j < setElements.length; j++) {
                set[i] = Integer.parseInt(setElements[i]);
            }
        }

        List<int[]> choosenSets = chooseSets(sets, universe);
        System.out.printf("Sets to take (%d):\n", choosenSets.size());

        for (int[] set : sets) {
            System.out.println(Arrays.toString(set));
        }
    }

    public static List<int[]> chooseSets(List<int[]> sets, int[] universe) {
        List<int[]> choosenSets = new ArrayList<>();
        Set<Integer> elemsToBeChosen = new HashSet<>();
        for (int element : universe) {
            elemsToBeChosen.add(element);
        }

        while (elemsToBeChosen.size() > 0) {
            int numberOfUnchosenElems = 0;
            int[] chosenSet = sets.get(0);

            for (int[] set : sets) {
                int count = 0;
                for (int elem : set) {
                    if (elemsToBeChosen.contains(elem)) count++;
                }
                if (numberOfUnchosenElems < count) {
                    numberOfUnchosenElems = count;
                    chosenSet = set;
                }
            }
            choosenSets.add(chosenSet);
            for (int elem : chosenSet) {
                elemsToBeChosen.remove(elem);
            }
        }

        return choosenSets;
    }
}
