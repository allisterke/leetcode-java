package a483;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private int compare(long base, int m, long n) {
        long N = 0;
        while (m -- > 0) {
            if((Long.MAX_VALUE - 1) / base >= N) {
                N = N * base + 1;
            }
            else {
                return 1;
            }
        }
        return Long.compare(N, n);
    }

    private long calculateBase(long n, int m) {
        long left = 2, right = n;
        while (left < right) {
            long mid = (left + right) / 2;
            int status = compare(mid, m, n);
            if(status > 0) {
                right = mid;
            }
            else if(status < 0) {
                left = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

    public String smallestGoodBase(String n) {
        long N = Long.valueOf(n);
        for(int m = 63; m > 2; -- m) {
            long base = calculateBase(N, m);
            if(base > 0) {
                return String.valueOf(base);
            }
        }
        return String.valueOf(N-1);
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "13",
                "4681",
                "1000000000000000000"
        );
        List<String> results = Arrays.asList(
                "3",
                "8",
                "999999999999999999"
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.smallestGoodBase(tests.get(i)));
        }
    }
}