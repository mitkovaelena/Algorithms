package lab.searchingAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SearchingAlgos {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> intList = redIntsToList(reader);
        int key = Integer.parseInt(reader.readLine());

        System.out.println(fibonacciSearch(intList, key));
    }


    private static int linearSearch(List<Integer> nums, int key) {
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == key) {
                return i;
            } else if (nums.get(i) > key) {
                return -1;
            }
        }
        return -1;
    }


    private static int binarySearch(List<Integer> nums, int key, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < nums.get(mid)) {
                return binarySearch(nums, key, lo, mid);

            } else if (key > nums.get(mid)) {
                return binarySearch(nums, key, mid + 1, hi);

            } else {
                return mid;
            }
        }

        if (lo == hi && nums.get(lo) == key) {
            return hi;
        }

        return -1;
    }


    private static int fibonacciSearch(List<Integer> nums, int key) {
        int fibPrev = 0;
        int fibCrnt = 1;
        int fibNext = fibPrev + fibCrnt;

        while (fibNext < nums.size()) {
            fibPrev = fibCrnt;
            fibCrnt = fibNext;
            fibNext = fibPrev + fibCrnt;
        }

        int offset = -1;         // Marks the eliminated range from front

        while (fibNext > 1) {
            int mid = Math.min(offset + fibPrev, nums.size() - 1);    // cut nums in proportion finPrev : fibCrnt

            if (nums.get(mid) < key) {            //cut from offset to mid
                fibNext = fibCrnt;
                fibCrnt = fibPrev;
                fibPrev = fibNext - fibCrnt;
                offset = mid;
            } else if (nums.get(mid) > key) {     //cut from mid+1 to nums.size
                fibNext = fibPrev;
                fibCrnt = fibCrnt - fibPrev;
                fibPrev = fibNext - fibCrnt;
            } else return mid;
        }

        if (fibCrnt != 0 && offset + 1 < nums.size() && nums.get(offset + 1) == key) {
            return offset + 1;
        }

        return -1;
    }

    private static List<Integer> redIntsToList(BufferedReader reader) throws IOException {
        List<Integer> list = new ArrayList<>();

        String[] line = reader.readLine().split("\\s+");

        for (String elem : line) {
            list.add(Integer.parseInt(elem));
        }

        return list;
    }
}
