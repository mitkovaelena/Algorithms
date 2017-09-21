import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class main1 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] arr = new int[n];
        String[] arrTokens = reader.readLine().split(", ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(arrTokens[i]);

        }
        int m = Integer.parseInt(reader.readLine());

        System.out.printf("Min Difference is %d.", findMinDiff(arr, n, m));
    }

    public static int findMinDiff(int arr[], int n, int m) {
        // if there are no chocolates or number of students is 0
        if (m==0 || n==0)
            return 0;

        // Sort the given packets
        Arrays.sort(arr);

        // Largest number of chocolates
        int min_diff = Integer.MAX_VALUE;

        // Find the subarray of size m such that difference between last and first elements is minimum.
        int first = 0, last = 0;
        for (int i=0; i+m-1<n; i++)
        {
            int diff = arr[i+m-1] - arr[i];
            if (diff < min_diff)
            {
                min_diff = diff;
                first = i;
                last = i + m - 1;
            }
        }
        return (arr[last] - arr[first]);
    }
}
