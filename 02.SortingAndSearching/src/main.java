import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] arr = new int[n - 1];
        String[] arrTokens = reader.readLine().split(", ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(arrTokens[i]);
        }

        System.out.println(findMissingNumber(arr, n));
    }

    public static int findMissingNumber(int arr[], int n) {
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            if (i + 1 != arr[i]) {
                return i + 1;
            }
        }
        return n;
    }
}
