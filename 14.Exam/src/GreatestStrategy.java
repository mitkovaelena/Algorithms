import java.util.Arrays;
import java.util.Scanner;

public class GreatestStrategy {
    public static void main(String[] args) {
        int tree[];
        int count[];
        int subTreeSum[];
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt(); //points
        int M = scan.nextInt();

        tree = new int[N];
        count = new int[N];
        subTreeSum = new int[N];

        Arrays.fill(count, 1);

        for (int i = 0; i < M; i++) {
            int u1 = scan.nextInt();
            int v1 = scan.nextInt();
            subTreeSum[u1 - 1] = u1;
            subTreeSum[v1 - 1] = v1;

            tree[u1 - 1] = v1;
            count[v1 - 1] += count[u1 - 1];
            subTreeSum[v1 - 1] += subTreeSum[u1 - 1];
            int root = tree[v1 - 1];

            while (root != 0) {
                count[root - 1] += count[u1 - 1];
                subTreeSum[root - 1] += subTreeSum[u1 - 1];
                root = tree[root - 1];
            }
        }

        int maxSum = Integer.MIN_VALUE;
        int counter = -1;
        for (int i = 0; i < count.length; i++) {
            if (count[i] % 2 == 0) {
                counter++;
                subTreeSum[tree[i]] -= subTreeSum[i];
                if(subTreeSum[i] > maxSum && i != 0){
                    maxSum = subTreeSum[i];
                }
            }
        }
        System.out.println(counter);
        System.out.println(maxSum);
    }
}