import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Island {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputTokens = reader.readLine().split(" ");
        int[] nums = new int[inputTokens.length];
        int globalMin = Integer.MAX_VALUE;

        int maxArea = 0;
        for (int i = 0; i < inputTokens.length; i++) {
            nums[i] = Integer.parseInt(inputTokens[i]);
            if(nums[i] < globalMin ){
                globalMin = nums[i];
            }

            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j >= 0; j--) {
                if(nums[j] == globalMin){
                    int crntMaxArea = globalMin * (i+1);
                    if(crntMaxArea > maxArea){
                        maxArea = crntMaxArea;
                    }
                    break;
                }
                if(nums[j] < minHeight){
                    minHeight = nums[j];
                }
                int crntMaxArea = minHeight * (i-j + 1);
                if(crntMaxArea > maxArea){
                    maxArea = crntMaxArea;
                }
            }
        }
        System.out.println(maxArea);
    }
}
