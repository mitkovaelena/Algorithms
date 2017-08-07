package lab.elections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Elections {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int sum = Integer.parseInt(reader.readLine());
        int partiesCount = Integer.parseInt(reader.readLine());
        List<Integer> votes = new ArrayList<>();

        int maxSum = 0;
        for (int i = 0; i < partiesCount; i++) {
            int votesCount = Integer.parseInt(reader.readLine());
            votes.add(votesCount);
            maxSum += votesCount;
        }

        BigDecimal[] votesArr = new BigDecimal[maxSum + 1];

        for (int i = 0; i <= maxSum; i++) {
            votesArr[i] = BigDecimal.ZERO;
        }
        votesArr[0] = BigDecimal.ONE;

        for (Integer v : votes) {
            for (int i = votesArr.length - 1; i >= 0; i--) {
                if (votesArr[i].compareTo(BigDecimal.ZERO) > 0) {
                    votesArr[i + v] = votesArr[i + v].add(votesArr[i]);
                }
            }
        }

        BigDecimal combinationsCount = new BigDecimal(0);

        for (int i = sum; i < votesArr.length; i++) {
            combinationsCount = combinationsCount.add(votesArr[i]);
        }
        System.out.println(combinationsCount.toString());
    }
}