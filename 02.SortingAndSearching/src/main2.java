import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[][] intervals = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] intervalTokens = reader.readLine().split(",");
            intervals[i][0] = Integer.parseInt(intervalTokens[0]);
            intervals[i][1] = Integer.parseInt(intervalTokens[1]);
        }

        sortByFirstCoordinate(intervals);

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][0] == intervals[i + 1][0] ||
                    intervals[i][1] >= intervals[i + 1][0] ||
                    intervals[i + 1][1] <= intervals[i][1]) {
                System.out.println("true");
                return;
            }
        }
        System.out.println("false");

    }

    public static void sortByFirstCoordinate(int[][] nums) {
        for (int i = 0; i < nums.length; i++) {
            int[] minimumElement = nums[i];
            int minimumIndex = i;

            for (int j = i; j < nums.length; j++) {    //find the min element in the following elements
                if (minimumElement[0] > nums[j][0]) {
                    minimumElement = nums[j];
                    minimumIndex = j;
                }
            }

            nums[minimumIndex] = nums[i];             //swap
            nums[i] = minimumElement;
        }
    }
}
