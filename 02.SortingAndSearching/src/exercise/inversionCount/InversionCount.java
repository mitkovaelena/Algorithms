package exercise.inversionCount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InversionCount {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split("\\s+");
        int[] nums = new int[line.length];

        for (int i = 0; i < line.length; i++) {
            nums[i] = Integer.parseInt(line[i]);

        }
        System.out.println(mergeSort(nums, new int[nums.length], 0, nums.length-1));
    }

    static int mergeSort(int arr[], int temp[], int left, int right) {
        int mid, inv_count = 0;
        if (right > left) {
            mid = (right + left) / 2;

            inv_count = mergeSort(arr, temp, left, mid);
            inv_count += mergeSort(arr, temp, mid + 1, right);

            inv_count += merge(arr, temp, left, mid + 1, right);
        }
        return inv_count;
    }

    static int merge(int arr[], int temp[], int left, int mid, int right) {
        int i, j, k;
        int inv_count = 0;

        i = left;
        j = mid;
        k = left;
        while ((i <= mid - 1) && (j <= right)) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];

                inv_count = inv_count + (mid - i);
            }
        }

        while (i <= mid - 1)
            temp[k++] = arr[i++];

        while (j <= right)
            temp[k++] = arr[j++];

        for (i = left; i <= right; i++)
            arr[i] = temp[i];

        return inv_count;
    }
}
