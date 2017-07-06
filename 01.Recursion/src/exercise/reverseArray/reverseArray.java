package exercise.reverseArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class reverseArray {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] intArr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        intArr = reverse(intArr);
        for (int i = 0; i < intArr.length; i++) {
            System.out.print(intArr[i] + " ");
        }
    }

    private static int[] reverse(int[] arr) {
        if(arr.length < 2){
            return arr;
        }
        int temp = arr[0];
        arr[0] = arr[arr.length-1];
        arr[arr.length-1] = temp;

        int[] newArr = new int[arr.length-2];
        System.arraycopy(arr, 1, newArr, 0, newArr.length);
        reverse(newArr);
        System.arraycopy(newArr, 0, arr, (arr.length-newArr.length)/2, newArr.length);

        return arr;
    }
}