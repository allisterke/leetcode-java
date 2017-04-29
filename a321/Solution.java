package a321;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Solution {
    private int[] maxNumber(int[] nums, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < nums.length; ++ i) {
            while (!stack.isEmpty() && nums[i] > stack.peekLast() && stack.size() + nums.length - i > k) {
                stack.removeLast();
            }
            stack.addLast(nums[i]);
        }
        int[] a = new int[k];
        for(int i = 0; i < k; ++ i) {
            a[i] = stack.pollFirst();
        }
        return a;
    }

    private int compare(StringBuilder sb1, StringBuilder sb2) {
        for(int i = 0; i < sb1.length(); ++ i) {
            if(sb1.charAt(i) < sb2.charAt(i)) {
                return -1;
            }
            else if(sb1.charAt(i) > sb2.charAt(i)) {
                return 1;
            }
        }
        return 0;
    }

    private int[] mergeMaxNumber(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        for(int i1 = 0, i2 = 0; i1 < nums1.length || i2 < nums2.length; ) {
            if(i1 >= nums1.length) {
                result[i1 + i2] = nums2[i2 ++];
            }
            else if(i2 >= nums2.length) {
                result[i1 + i2] = nums1[i1 ++];
            }
            else {
                if(nums1[i1] > nums2[i2]) {
                    result[i1 + i2] = nums1[i1 ++];
                }
                else if(nums1[i1] < nums2[i2]) {
                    result[i1 + i2] = nums2[i2 ++];
                }
                else {
                    int i = 1;
                    for(; i1 + i < nums1.length && i2 + i < nums2.length &&
                            nums1[i1 + i] == nums2[i2 + i] && nums1[i1 + i] >= nums1[i1]; ++ i) ;
                    if(i1 + i >= nums1.length) {
                        result[i1 + i2] = nums2[i2 ++];
                    }
                    else if(i2 + i >= nums2.length) {
                        result[i1 + i2] = nums1[i1 ++];
                    }
                    else {
                        if(nums1[i1 + i] > nums2[i2 + i]) {
                            result[i1 + i2] = nums1[i1 ++];
                        }
                        else {
                            result[i1 + i2] = nums2[i2 ++];
                        }
                    }
                }
            }
        }
        return result;
    }

    private int[] mergeMaxNumber0(int[] nums1, int[] nums2) {
        StringBuilder[][] dp = new StringBuilder[nums1.length + 1][];
        for(int i = 0; i <= nums1.length; ++ i) {
            dp[i] = new StringBuilder[nums2.length + 1];
        }
        for(int i = 0; i <= nums1.length; ++ i) {
            for(int j = 0; j <= nums2.length; ++ j) {
                if(i == 0 && j == 0) {
                    // do nothing
                    dp[i][j] = new StringBuilder();
                }
                else if(i == 0) {
                    dp[i][j] = new StringBuilder(dp[i][j-1]);
                    dp[i][j].append(nums2[j-1]);
                }
                else if(j == 0) {
                    dp[i][j] = new StringBuilder(dp[i-1][j]);
                    dp[i][j].append(nums1[i-1]);
                }
                else {
                    dp[i-1][j].append(nums1[i-1]);
                    dp[i][j-1].append(nums2[j-1]);
                    dp[i][j] = new StringBuilder(compare(dp[i-1][j], dp[i][j-1]) > 0 ? dp[i-1][j] : dp[i][j-1]);
                    dp[i-1][j].setLength(dp[i-1][j].length() - 1);
                    dp[i][j-1].setLength(dp[i][j-1].length() - 1);
                }
            }
        }
        StringBuilder mnb = dp[nums1.length][nums2.length];
        int[] mn = new int[nums1.length + nums2.length];
        for(int i = 0; i < mn.length; ++ i) {
            mn[i] = mnb.charAt(i) - '0';
        }
        return mn;
    }


    private int compare(int[] nums1, int[] nums2) {
        for(int i = 0; i < nums1.length; ++ i) {
            if(nums1[i] < nums2[i]) {
                return -1;
            }
            else if(nums1[i] > nums2[i]) {
                return 1;
            }
        }
        return 0;
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] mn = null;
        for(int i = 0; i <= k && i <= nums1.length; ++ i) {
            if(k - i <= nums2.length) {
                int[] tmn = mergeMaxNumber(maxNumber(nums1, i), maxNumber(nums2, k - i));
                if(mn == null || compare(mn, tmn) < 0) {
                    mn = tmn;
                }
            }
        }
        return mn;
    }

    public static void main(String[] args) {
        List<List<int[]>> tests = Arrays.asList(
                Arrays.asList(new int[] {3, 4, 6, 5}, new int[] {9, 1, 2, 5, 8, 3}),
                Arrays.asList(new int[] {6, 7}, new int[] {6, 0, 4}),
                Arrays.asList(new int[] {3, 9}, new int[] {8, 9})
        );
        List<Integer> ks = Arrays.asList(
                5,
                5,
                3
        );
        List<int[]> results = Arrays.asList(
                new int[] {9, 8, 6, 5, 3},
                new int[] {6, 7, 6, 0, 4},
                new int[] {9, 8, 9}
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(Arrays.equals(results.get(i), s.maxNumber(tests.get(i).get(0), tests.get(i).get(1), ks.get(i))));
        }
    }
}