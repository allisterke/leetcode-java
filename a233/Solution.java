package a233;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private long pow(long n, long k) {
        long N = 1;
        for(; k -- > 0; N *= n);
        return N;
    }

    private long countDigits(long k) {
        return k == 0 ? 0 : k == 1 ? 1 : 10 * countDigits(k - 1) + pow(10, k - 1);
    }

    private long countDigitOne(long n) {
        if(n == 0) {
            return 0;
        }
        long N = 1;
        int i = 0;
        for(; N < n; N *= 10, ++ i) ;
        if(N > n) {
            N /= 10;
            -- i;
        }
        long k = n / N;
        return k == 1 ? countDigits(i) + (n - N + 1) + countDigitOne(n - N) :
                k * countDigits(i) + N + countDigitOne(n % N);
    }
    public int countDigitOne(int n) {
        return (int)countDigitOne(Math.max((long)n, 0L));
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                13,
                -1,
                100
        );
        List<Integer> results = Arrays.asList(
                6,
                0
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.countDigitOne(tests.get(i)));
        }
    }
}