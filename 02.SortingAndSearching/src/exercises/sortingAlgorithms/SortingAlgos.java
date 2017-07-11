package exercises.sortingAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SortingAlgos {
    private static final int BUCKET_SIZE = 30;

    public static void main(String[] args) throws IOException {
        List<Integer> intList = redIntsToList();
        bucketSort(intList);
        printList(intList);
    }


    public static void bubbleSort(List<Integer> nums) {
        for (int k = 0; k < nums.size(); k++) {
            boolean hasChanged = false;
            for (int i = 0; i < nums.size() - 1; i++) {
                if (nums.get(i) > nums.get(i + 1)) {
                    int temp = nums.get(i);
                    nums.set(i, nums.get(i + 1));
                    nums.set(i + 1, temp);
                    hasChanged = true;
                }
            }
            if (!hasChanged) {
                break;
            }
        }
    }


    public static void insertionSort(List<Integer> nums) {
        for (int k = 1; k < nums.size(); k++) {
            int r = nums.get(k);
            int ind = k - 1;
            while (ind >= 0 && nums.get(ind) > r) {    //iterate back until you find smaller element
                nums.set(ind + 1, nums.get(ind));
                ind--;
            }
            nums.set(ind + 1, r);                     //put the element right after
        }
    }


    public static void selectionSort(List<Integer> nums) {
        for (int i = 0; i < nums.size(); i++) {
            int minimumElement = nums.get(i);
            int minimumIndex = i;

            for (int j = i; j < nums.size(); j++) {    //find the min element in the following elements
                if (minimumElement > nums.get(j)) {
                    minimumElement = nums.get(j);
                    minimumIndex = j;
                }
            }

            nums.set(minimumIndex, nums.get(i));             //swap
            nums.set(i, minimumElement);
        }
    }


    public static void shellSort(List<Integer> nums) {
        for (int gap = nums.size() / 2; gap > 0; gap /= 2)
            for (int i = gap; i < nums.size(); i++)
                for (int j = i - gap; j >= 0 && nums.get(j) > nums.get(j + gap); j -= gap) {  //analogical to insertionSort
                    int temp = nums.get(j);
                    nums.set(j, nums.get(j + gap));
                    nums.set(j + gap, temp);
                }
    }

    private static void mergeSort(List<Integer> nums, int r, int l) {
        if (r >= l) {
            return;
        }
        int middle = r + (l - r) / 2;
        mergeSort(nums, r, middle);
        mergeSort(nums, middle + 1, l);
        merge(nums, r, middle, l);
    }

    private static void merge(List<Integer> nums, int r, int middle, int l) {
        int[] helper = new int[nums.size()];

        for (int i = r; i <= l; i++) {
            helper[i] = nums.get(i);
        }

        int i = r;
        int j = middle + 1;
        int k = r;

        while (i <= middle && j <= l) {             // Copy the smallest values from either the left or the right side
            if (helper[i] <= helper[j]) {
                nums.set(k, helper[i]);
                i++;
            } else {
                nums.set(k, helper[j]);
                j++;
            }
            k++;
        }

        while (i <= middle) {                       // Copy the rest of the left side of the array into the target array
            nums.set(k, helper[i]);
            k++;
            i++;
        }
    }


    private static void quickSort(List<Integer> nums, int r, int l) {
        if (r >= l) {
            return;
        }

        int pivotInd = partition(nums, r, l);
        quickSort(nums, r, pivotInd - 1);
        quickSort(nums, pivotInd + 1, l);
    }

    private static int partition(List<Integer> nums, int r, int l) {
        int i = r;
        int j = l + 1;
        while (true) {
            while (nums.get(++i) < nums.get(r)) {
                if (i == l) break;
            }
            while (nums.get(r) < nums.get(--j)) {
                if (j == r) break;
            }

            if (i >= j) break;

            int temp = nums.get(j);       //swap smaller than array[r] goes left
            nums.set(j, nums.get(i));       // bigger than array[r] goes right
            nums.set(i, temp);
        }

        int temp = nums.get(j);           //swap the first element with the pivot
        nums.set(j, nums.get(r));
        nums.set(r, temp);

        return j;
    }


    public static void bucketSort(List<Integer> nums) {
        int maxElem = nums.get(0);
        int minElem = nums.get(0);
        for (int j = 1; j < nums.size(); j++) {
            if (maxElem < nums.get(j)) {
                maxElem = nums.get(j);
            }
            if (minElem > nums.get(j)) {
                minElem = nums.get(j);
            }
        }

        int bucketCount = (maxElem - minElem) / BUCKET_SIZE + 1;     //initialize buckets
        List<List<Integer>> buckets = new ArrayList<List<Integer>>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < nums.size(); i++) {                    //distribute elements into the buckets
            buckets.get((nums.get(i) - minElem) / BUCKET_SIZE).add(nums.get(i));
        }

        int currentIndex = 0;
        for (int i = 0; i < buckets.size(); i++) {
            List<Integer> bucketArray = buckets.get(i);
            insertionSort(bucketArray);               //sort each bucket
            for (int j = 0; j < bucketArray.size(); j++) {
                nums.set(currentIndex++, bucketArray.get(j));
            }
        }
    }

    private static void printList(List<Integer> nums) {
        StringBuilder sb = new StringBuilder();

        for (int elem : nums) {
            sb.append(elem).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    private static List<Integer> redIntsToList() throws IOException {
        List<Integer> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split("\\s+");

        for (String elem : line) {
            list.add(Integer.parseInt(elem));
        }

        return list;
    }
}
