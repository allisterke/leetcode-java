package a239;

import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    class MutableInteger{
        int n;
        public MutableInteger(int n) {
            this.n = n;
        }
    }

    private void insert(TreeMap<Integer, MutableInteger> map, int n) {
        if(!map.containsKey(n)) {
            map.put(n, new MutableInteger(1));
        }
        else {
            ++ map.get(n).n;
        }
    }
    private void remove(TreeMap<Integer, MutableInteger> map, int n) {
        MutableInteger mi = map.get(n);
        if(mi != null) {
            if(mi.n == 1) {
                map.remove(n);
            }
            else {
                -- mi.n;
            }
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length - k + 1];
        TreeMap<Integer, MutableInteger> map = new TreeMap<>();
        for(int i = 0; i < k-1; ++ i) {
            insert(map, nums[i]);
        }
        for(int i = k-1; i < nums.length; ++ i) {
            insert(map, nums[i]);
            result[i - k + 1] = map.lastKey();
            remove(map, nums[i - k + 1]);
        }
        return result;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {1,3,-1,-3,5,3,6,7}
        );
        List<Integer> ks = Arrays.asList(
                3
        );
        List<int[]> results = Arrays.asList(
                new int[] {3,3,5,5,6,7}
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(Arrays.stream(s.maxSlidingWindow(tests.get(i), ks.get(i))).boxed().collect(Collectors.toList()));
        }
    }
}