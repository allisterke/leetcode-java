package a480;

import java.util.*;

public class Solution {
    class DoubleWrapper implements Comparable<DoubleWrapper> {
        double v;
        int index;
        public DoubleWrapper(double v, int index) {
            this.v = v;
            this.index = index;
        }
        public int compareTo(DoubleWrapper wrapper) {
            return v != wrapper.v ? Double.compare(v, wrapper.v): Integer.compare(index, wrapper.index);
        }
    }

    private double getMedian(TreeSet<DoubleWrapper> smaller, TreeSet<DoubleWrapper> bigger) {
        return smaller.size() == bigger.size() ?
            (smaller.last().v + bigger.first().v) / 2 : smaller.last().v;
    }

    private void replace(TreeSet<DoubleWrapper> smaller, TreeSet<DoubleWrapper> bigger,
                         DoubleWrapper src, DoubleWrapper dst) {
        smaller.remove(src);
        bigger.remove(src);
        if(smaller.isEmpty() || smaller.last().compareTo(dst) < 0) {
            bigger.add(dst);
        }
        else {
            smaller.add(dst);
        }
        while (smaller.size() - bigger.size() > 1) {
            bigger.add(smaller.pollLast());
        }
        while (smaller.size() < bigger.size()) {
            smaller.add(bigger.pollFirst());
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        DoubleWrapper[] wrappers = new DoubleWrapper[nums.length];
        for (int i = 0; i < nums.length; ++ i) {
            wrappers[i] = new DoubleWrapper(nums[i], i);
        }
        double[] result = new double[nums.length - k + 1];
        TreeSet<DoubleWrapper> smaller = new TreeSet<>();
        TreeSet<DoubleWrapper> bigger = new TreeSet<>();
        for(int i = 0; i < k; ++ i) {
            smaller.add(wrappers[i]);
        }
        for(int i = 0; i < k/2; ++ i) {
            bigger.add(smaller.pollLast());
        }
        for(int i = k-1; i < nums.length; ++ i) {
            if(i >= k) {
                replace(smaller, bigger, wrappers[i-k], wrappers[i]);
            }
            result[i - k + 1] = getMedian(smaller, bigger);
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
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            for (double d : s.medianSlidingWindow(tests.get(i), ks.get(i))) {
                System.out.print(d);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}