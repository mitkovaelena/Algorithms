package lab.eightQueens;

public class Queens {
    private static final int NUMBER_OF_QUEENS = 8;

    public static void main(String[] args) {
        enumerate(new int[NUMBER_OF_QUEENS], 0);
    }

    public static void enumerate(int[] queensPositions, int ind) {
        if (ind == NUMBER_OF_QUEENS) {
            printQueens(queensPositions);
        } else {
            for (int i = 0; i < NUMBER_OF_QUEENS; i++) {    //backtrack
                queensPositions[ind] = i;
                if (isConsistent(queensPositions, ind)) {
                    enumerate(queensPositions, ind + 1);
                }
            }
        }
    }

    public static boolean isConsistent(int[] queensPositions, int ind) {
        for (int i = 0; i < ind; i++) {
            if (queensPositions[i] == queensPositions[ind]) {
                return false;   // same column
            }
            if ((queensPositions[i] - queensPositions[ind]) == (ind - i)) {
                return false;   // same left diagonal
            }
            if ((queensPositions[ind] - queensPositions[i]) == (ind - i)) {
                return false;   // same right diagonal
            }
        }
        return true;
    }

    public static void printQueens(int[] queensPositions) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < NUMBER_OF_QUEENS; i++) {
            for (int j = 0; j < NUMBER_OF_QUEENS; j++) {
                if (queensPositions[i] == j) {
                    output.append("* ");
                } else {
                    output.append("- ");
                }
            }
            output.append("\n");
        }

        System.out.println(output);
    }
}