package a315;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : sorted) {
            if(!map.containsKey(n)) {
                map.put(n, map.size());
            }
        }
        int[] result = new int[nums.length];
        if(nums.length > 0) {
            SegmentTree tree = new SegmentTree(map.size());
            for (int i = nums.length - 1; i >= 0; --i) {
                result[i] = tree.count(0, map.get(nums[i]));
                tree.increment(map.get(nums[i]));
            }
        }
        return Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {5,6,2,1}
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.countSmaller(tests.get(i)));
        }
    }
}