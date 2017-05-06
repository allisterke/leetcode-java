package a327;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    class SegmentTree {
        class Segment {
            int begin, end;
            int count;
            public Segment(int b, int e, int c) {
                begin = b;
                end = e;
                count = c;
            }
        }
        Segment[] segments;
        public SegmentTree(int capacity) {
            segments = new Segment[capacity << 2];
            init(0, 0, capacity);
        }
        private void init(int root, int begin, int end) {
            segments[root] = new Segment(begin, end, 0);
            if(begin + 1 < end) {
                int mid = (begin + end) / 2;
                init(root * 2 + 1, begin, mid);
                init(root * 2 + 2, mid, end);
            }
        }
        public int count(int begin, int end) {
            return count(0, begin, end);
        }
        private int count(int root, int begin, int end) {
            if(begin <= segments[root].begin && end >= segments[root].end) {
                return segments[root].count;
            }
            if(begin >= segments[root].end || end <= segments[root].begin) {
                return 0;
            }
            return count(root * 2 + 1, begin, end) + count(root * 2 + 2, begin, end);
        }
        public void increment(int index) {
            increment(0, index);
        }
        private void increment(int root, int index) {
            if(segments[root].begin <= index && segments[root].end > index) {
                ++ segments[root].count;
                if(segments[root].begin + 1 != segments[root].end) {
                    increment(root * 2 + 1, index);
                    increment(root * 2 + 2, index);
                }
            }
        }
    }
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sums = new long[nums.length + 1];
        for(int i = 0; i < nums.length; ++ i) {
            sums[i+1] = sums[i] + nums[i];
        }
        long[] sorted = Arrays.copyOf(sums, sums.length);
        Arrays.sort(sorted);
        TreeMap<Long, Integer> map = new TreeMap<>();
        map.put(sorted[0], 0);
        for(int i = 1; i < sorted.length; ++ i) {
            if(sorted[i] != sorted[i-1]) {
                map.put(sorted[i], map.size());
            }
        }
        SegmentTree tree = new SegmentTree(map.size());
        int count = 0;
        tree.increment(map.get(sums[0]));
        for(int i = 1; i < sums.length; ++ i) {
            Map.Entry<Long, Integer> entry;
            int lb = (entry = map.ceilingEntry(sums[i] - upper)) != null ? entry.getValue() : map.size();
            int ub = (entry = map.floorEntry(sums[i] - lower)) != null ? entry.getValue() + 1 : 0;
            count += tree.count(lb, ub);
            tree.increment(map.get(sums[i]));
        }
        return count;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {-2, 5, -1},
                new int[] {2147483647,-2147483648,-1,0},
                new int[] {0,-1,-2,-3,0,2}
        );
        List<Integer> lower = Arrays.asList(
                -2,
                -1,
                3
        );
        List<Integer> upper = Arrays.asList(
                2,
                0,
                5
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.countRangeSum(tests.get(i), lower.get(i), upper.get(i)));
        }
    }
}