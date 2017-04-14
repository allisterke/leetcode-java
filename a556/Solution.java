package a556;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Solution {
    public int nextGreaterElement(int n) {
        String str = String.valueOf(n);
        char[] a = str.toCharArray();
        int i = a.length - 1;
        for(; i > 0 && a[i-1] >= a[i]; -- i) ;
        if(i <= 0) {
            return -1;
        }
        else {
            int p = i;
            for(int j = i+1; j < a.length; ++ j) {
                if(a[j] > a[i-1] && a[j] < a[p]) {
                    p = j;
                }
            }
            char tmp = a[p];
            a[p] = a[i-1];
            a[i-1] = tmp;
            Arrays.sort(a, i, a.length);
        }
        try {
            return Integer.valueOf(new String(a));
        }
        catch (Exception e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(12, 21, 45321, 12443322);
        List<Integer> results = Arrays.asList(21, -1, 51234, 13222344);

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.nextGreaterElement(tests.get(i)) == results.get(i));
        }
    }
}