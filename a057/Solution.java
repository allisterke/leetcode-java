package a057;

import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        int i = 0;
        for(; i < intervals.size(); ++ i) {
            if(intervals.get(i).end < newInterval.start) {
                result.add(intervals.get(i));
            }
            else {
                break;
            }
        }
        if(i == intervals.size()) {
            result.add(newInterval);
        }
        else {
            if(intervals.get(0).start > newInterval.end) {
                result.add(newInterval);
            }
            else {
                int start = Math.min(newInterval.start, intervals.get(i).start);
                for(; i < intervals.size() && newInterval.end >= intervals.get(i).start; ++ i);
                -- i;
                int end = Math.max(newInterval.end, intervals.get(i).end);
                result.add(new Interval(start, end));
                ++ i;
            }
            for(; i < intervals.size(); ++ i) {
                result.add(intervals.get(i));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}