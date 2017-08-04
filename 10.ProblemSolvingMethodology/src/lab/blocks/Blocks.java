package lab.blocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Blocks {
    private static char[] set;
    private static char[] block;
    private static Set<String> outputBlocks = new TreeSet<>();
    private static Set<String> foundBlocks = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        set = new char[n];
        block = new char[4];

        char a = 'A';
        for (int i = 0; i < n; i++, a++) {
            set[i] = a;
        }
        findBlocks(new boolean[set.length], 0);
        System.out.println("Number of blocks: " + outputBlocks.size());
        StringBuilder sb = new StringBuilder();
        for (String block : outputBlocks) {
            sb.append(block).append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static void findBlocks(boolean[] used, int index) {
        if (index == 4) {
            if (!isFound(String.valueOf(block))) {
                outputBlocks.add(String.valueOf(block));
            }
        } else {
            for (int i = 0; i < set.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    block[index] = set[i];
                    findBlocks(used, index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static boolean isFound(String block) {
        if (foundBlocks.contains(block) || outputBlocks.contains(block)) {
            return true;
        }
        for (int i = 0; i < 3; i++) {
            block = rotateBlock(block);
            if (outputBlocks.contains(block)) {
                return true;
            }
        }
        return false;
    }

    private static String rotateBlock(String block) {
        StringBuilder sb = new StringBuilder();
        sb.append(block.charAt(2)).append(block.charAt(0)).append(block.charAt(3)).append(block.charAt(1));
        foundBlocks.add(sb.toString());
        return sb.toString();
    }
}
