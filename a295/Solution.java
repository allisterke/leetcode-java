package a295;

import java.util.*;

class MedianFinder {
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> max = new ArrayList<>();
    List<Integer> min = new ArrayList<>();
    int total = 0;

    Random r = new Random();

    private int kthElement(List<Integer> list, int k, int begin, int end) {
        if(begin + 1 == end) {
            return list.get(begin);
        }
        int pivot = r.nextInt(end - begin) + begin;
        int p = list.get(pivot);
        list.set(pivot, list.get(begin));
        list.set(begin, p);
        int left = begin, right = end - 1;
        while (left < right) {
            while (left < right && list.get(right) > p) -- right;
            while (left < right && list.get(left) <= p) ++ left;
            if(left == right) {
                list.set(begin, list.get(left));
                list.set(left, p);
                break;
            }
            list.set(begin, list.get(right));
            list.set(right, list.get(left));
            list.set(left, list.get(begin));
        }
        if(k < left) {
            return kthElement(list, k, begin, left);
        }
        else if(k > left) {
            return kthElement(list, k, left + 1, end);
        }
        else {
            return list.get(k);
        }
    }

    /** initialize your data structure here. */
    public MedianFinder() {
        list.add(new ArrayList<>());
        max.add(Integer.MIN_VALUE);
        min.add(Integer.MAX_VALUE);
    }

    public void addNum(int num) {
        ++ total;
        int i = 0;
        for (; i < list.size(); ++ i) {
            if (i < list.size() - 1 && min.get(i + 1) > num || i == list.size() - 1) {
                list.get(i).add(num);
                max.set(i, Math.max(max.get(i), num));
                min.set(i, Math.min(min.get(i), num));
                break;
            }
        }
        if(list.get(i).size() > 2000 && list.get(i).size() > total / 10) {
            reshape();
        }
    }

    private void reshape() {
        int[] nums = new int[total];
        int k = 0;
        for(int i = 0; i < list.size(); ++ i) {
            for(int j = 0; j < list.get(i).size(); ++ j) {
                nums[k ++] = list.get(i).get(j);
            }
        }
        Arrays.sort(nums);
        list.clear();
        min.clear();
        max.clear();
        for(int i = 0; i < total; i += total / 20 + 1) {
            List<Integer> nl = new ArrayList<>();
            int j = 0;
            for(; j < total / 20 + 1 && i + j < total; ++ j) {
                nl.add(nums[i + j]);
            }
            list.add(nl);
            min.add(nums[i]);
            max.add(nums[i + j - 1]);
        }
    }

    private int nthElement(int n) {
        int acc = 0;

        for (int i = 0; i < list.size(); ++i) {
            if (acc + list.get(i).size() >= n) {
                return kthElement(list.get(i), n - acc - 1, 0, list.get(i).size());
            }
            acc += list.get(i).size();
        }
        return 0;
    }

    public double findMedian() {
        if((total & 1) == 1) {
            return nthElement(total / 2 + 1);
        }
        else {
            return (nthElement(total / 2) + nthElement(total / 2 + 1)) / 2.0;
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(1);
        obj.addNum(3);
        obj.addNum(4);
//        System.out.println(obj.findMedian() == 3);
        System.out.println(obj.findMedian());
        obj.addNum(6);
        obj.addNum(2);
        obj.addNum(2);
//        System.out.println(obj.findMedian() == 2.5);
        System.out.println(obj.findMedian());
    }
}