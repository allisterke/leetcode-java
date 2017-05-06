package a164;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int maximumGap(int[] nums) {
        if(nums.length < 2) {
            return 0;
        }
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        if(max == min) {
            return 0;
        }
        int gap = (int)(Math.ceil((max - min) / (nums.length - 1.)));
        int buckets = (int)Math.ceil((max - min + 1) / (double)gap);
        int[] m1 = new int[buckets];
        int[] m2 = new int[buckets];
        Arrays.fill(m1, Integer.MAX_VALUE);
        Arrays.fill(m2, -1);
        for(int n : nums) {
            int bucket = (n - min) / gap;
            m1[bucket] = Math.min(m1[bucket], n);
            m2[bucket] = Math.max(m2[bucket], n);
        }
        int maxGap = 0;
        int lastBucketMax = -1;
        for(int i = 0; i < buckets; ++ i) {
            if(m2[i] != -1) {
                if(lastBucketMax != -1) {
                    maxGap = Math.max(maxGap, m1[i] - lastBucketMax);
                }
                lastBucketMax = m2[i];
            }
        }
        return maxGap;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
//                new int[] {1,4,2,8,5,7}
                new int[] {1,4,2,8,5,7,2,3,6,8,11,23,22,10,17}
        );
        List<Integer> results = Arrays.asList(
                2
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.maximumGap(tests.get(i)));
        }
    }
}