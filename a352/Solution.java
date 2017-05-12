package a352;

import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
    public String toString() {
        return "[" + start + "," + end + "]";
    }
}

class SummaryRanges {

    TreeSet<Integer> set = new TreeSet<>();

    /** Initialize your data structure here. */
    public SummaryRanges() {

    }

    public void addNum(int val) {
        set.add(val);
    }

    public List<Interval> getIntervals() {
        List<Interval> result = new ArrayList<>();
        Interval interval = null;
        for(int n : set) {
            if(interval == null) {
                interval = new Interval(n, n);
            }
            else {
                if(n == interval.end + 1) {
                    ++ interval.end;
                }
                else {
                    result.add(interval);
                    interval = new Interval(n, n);
                }
            }
        }
        result.add(interval);
        return result;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {1, 3, 7, 2, 6}
        );
        List results = Arrays.asList();

        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            SummaryRanges s = new SummaryRanges();
            for(int n : tests.get(i)) {
                s.addNum(n);
            }
            System.out.println(s.getIntervals());
        }
    }
}