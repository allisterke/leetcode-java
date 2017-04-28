package a564;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

public class Solution {
//    private boolean checkPalindromic(String n) {
//        for(int i = 0; i < n.length() / 2; ++ i) {
//            if(n.charAt(i) != n.charAt(n.length() - 1 - i)) {
//                return false;
//            }
//        }
//        return true;
//    }

    private Long nearestBiggerPalindromic(String n) {
        long N = Long.valueOf(n);
        long bigger = Long.valueOf(1);
        for(int i = 0; i < n.length(); ++ i) {
            bigger *= 10;
        }
        ++ bigger;
        for(int i = 0; i <= n.length() - i - 1; ++ i) {
            for(char c = n.charAt(i); c <= '9'; ++ c) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < i; ++j) {
                    sb.append(n.charAt(j));
                }
                sb.append(c);
                for (int j = 0; j < n.length() - 2 * i - 2; ++j) {
                    sb.append(0);
                }
                if (i < n.length() - i - 1) {
                    sb.append(c);
                }
                for (int j = i - 1; j >= 0; --j) {
                    sb.append(n.charAt(j));
                }
                long t = Long.valueOf(sb.toString());
                if(t > N) {
                    bigger = Math.min(bigger, t);
                }
            }
        }
        return bigger;
    }

    private Long nearestSmallerPalindromic(String n) {
        long N = Long.valueOf(n);
        long smaller = 1;
        for(int i = 0; i < n.length() - 1; ++ i) {
            smaller *= 10;
        }
        -- smaller;
        for(int i = 0; i <= n.length() - i - 1; ++ i) {
            for(char c = n.charAt(i); c >= '0'; -- c) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < i; ++j) {
                    sb.append(n.charAt(j));
                }
                sb.append(c);
                for (int j = 0; j < n.length() - 2 * i - 2; ++j) {
                    sb.append(9);
                }
                if (i < n.length() - i - 1) {
                    sb.append(c);
                }
                for (int j = i - 1; j >= 0; --j) {
                    sb.append(n.charAt(j));
                }
                long t = Long.valueOf(sb.toString());
                if(t < N) {
                    smaller = Math.max(smaller, t);
                }
            }
        }
        return smaller;
    }

    public String nearestPalindromic(String n) {
        Long N = Long.valueOf(n);
        if(N <= 10) {
            return String.valueOf(N-1);
        }
//        boolean pic = checkPalindromic(n);
//        Long bigger = nearestBiggerPalindromic(pic ? String.valueOf(N+1) : n);
//        Long smaller = nearestSmallerPalindromic(pic ? String.valueOf(N-1) : n);
        Long bigger = nearestBiggerPalindromic(n);
        Long smaller = nearestSmallerPalindromic(n);

        return String.valueOf(bigger - N < N - smaller ? bigger : smaller);
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "123",
                "2",
                "9898",
                "11"
        );
        List<String> results = Arrays.asList(
                "121",
                "1",
                "9889",
                "9"
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.nearestPalindromic(tests.get(i)));
        }
    }
}