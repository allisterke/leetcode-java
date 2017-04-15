package a440;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private int getKth(int n, int prefix, int offset, int k) {
        if(offset == k) {
            return prefix;
        }
        for(int i = (prefix == 0 ? 1 : 0); i <= 9; ++ i) {
            int count = countRange(n, prefix * 10 + i);
            if(offset + count >= k) {
                return getKth(n, prefix * 10 + i, offset + 1, k);
            }
            else {
                offset += count;
            }
        }
        return 0;
    }

    private int countRange(int n, int prefix) {
        if(prefix > n) {
            return 0;
        }

        int append = 0;
        long rangeMax = prefix;
        for(; rangeMax < n; ++ append) {
            rangeMax = rangeMax * 10 + 9;
        }
        if(String.valueOf(rangeMax).length() > String.valueOf(n).length()) {
            rangeMax /= 10;
            -- append;
        }

        int count = 0;
        for(int i = 0; i <= append; ++ i) {
            int tmp = 1;
            for(int j = 0; j < i; ++ j) {
                tmp *= 10;
            }
            count += tmp;
        }

        long rangeMin = prefix;
        while (rangeMin < n) {
            rangeMin *= 10;
        }
        if(String.valueOf(rangeMin).length() > String.valueOf(n).length()) {
            rangeMin /= 10;
        }

        return (int)(rangeMax > n ? count - (rangeMax - Math.max(n, rangeMin-1)) : count);
    }

    public int findKthNumber(int n, int k) {
        return getKth(n, 0, 0, k);
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                100,
                13,
                13,
                2,
                1000000000,
                681692778);
        List<Integer> ks = Arrays.asList(
                7,
                2,
                12,
                2,
                7788,
                351251360);
        List<Integer> results = Arrays.asList(
                14,
                10,
                8,
                2,
                100007001,
                416126219);

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.findKthNumber(tests.get(i), ks.get(i)));
        }
    }
}