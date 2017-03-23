package a420;

import java.util.*;

public class Solution {

    public int strongPasswordChecker(String s) {
        if(s.length() < 6) {
            return editDistanceForShort(s);
        }
        else if(s.length() >= 6 && s.length() <= 20) {
            return editDistanceForMedium(s);
        }
        else {
            return editDistanceForLong(s);
        }
    }

    private int checkLUD(String s) {
        boolean[] lud = new boolean[3];
        for(int i = 0; i < s.length(); ++ i) {
            if(Character.isLowerCase(s.charAt(i))) {
                lud[0] = true;
            }
            else if(Character.isUpperCase(s.charAt(i))) {
                lud[1] = true;
            }
            else if(Character.isDigit(s.charAt(i))) {
                lud[2] = true;
            }
        }
        int types = 0;
        for(boolean t : lud) {
            types += t ? 1 : 0;
        }
        return types;
    }

    // edit for more than 20 characters
    private int editDistanceForLong(String s) {
        int lud = checkLUD(s);

        int[] r = new int[3];
        int keep = 0;
        for(int i = 0; i < s.length(); ) {
            int j = i + 1;
            for(; j < s.length() && s.charAt(j) == s.charAt(i); ++ j) ;
            int n = j - i;
            if(n < 3) {
                keep += n;
            }
            else {
                keep += 2;
                n -= 2;
                r[0] += n / 3;
                if(n % 3 != 0) {
                    ++r[n % 3];
                }
            }
            i = j;
        }
        if(keep >= 20) {
            return s.length() - 20 + (3 - lud);
        }
        else {
            int replace = 0;
            for(int i = 0; keep < 20 && i < r.length; ++ i) {
                int n = (20 - keep) / (3 - i);
                n = Math.min(n, r[i]);
                keep += n * (3 - i);
                replace += n;
            }
            if(keep < 20) {
                ++ replace;
            }
            return s.length() - 20 + Math.max(replace, 3 - lud);
        }
    }

    // edit for 6 - 20 characters
    private int editDistanceForMedium(String s) {
        int replace = 0;
        for(int i = 0; i < s.length(); ) {
            if(i + 2 < s.length() && s.charAt(i) == s.charAt(i+1) && s.charAt(i) == s.charAt(i+2)) {
                ++ replace;
                i += 3;
            }
            else {
                ++ i;
            }
        }
        if(replace >= 3) {
            return replace;
        }
        else {
            int lud = checkLUD(s);
            if(lud == 0) {
                return 3;
            }
            else if(lud == 1) {
                return 2;
            }
            else if(lud == 2) {
                return Math.max(replace, 1);
            }
            else { // if(lud == 3) {
                return replace;
            }
        }
    }

    // edit for less than 6 characters
    private int editDistanceForShort(String s) {
        if(s.length() <= 4) {
            return 6 - s.length();
        }
        else if(s.length() == 5) {
            int lud = checkLUD(s);
            if(lud == 0) {
                return 3;
            }
            else if(lud == 1) {
                return 2;
            }
            else if(lud == 2) {
                return 1;
            }
            else { // if(lud == 3) {
                return 1;
            }
        }
        else { // if(s.length() == 6) {
            int lud = checkLUD(s);
            if(lud == 0) {
                return 3;
            }
            else if(lud == 1) {
                return 2;
            }
            else if(lud == 2) {
                return 1;
            }
            else { // if(lud == 3) {
                boolean three = false;
                for(int i = 0; i < s.length(); ++ i) {
                    if(i + 2 < s.length() && s.charAt(i+1) == s.charAt(i) && s.charAt(i+2) == s.charAt(i)) {
                        three = true;
                        break;
                    }
                }
                return three ? 1 : 0;
            }
        }
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "aaaaaaaaaaaaaaaaaaaaa",
                "1010101010aaaB10101010",
                "1234567890123456Baaaaa");
        List<Integer> results = Arrays.asList(7, 2, 3);

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.println(i);
            System.out.println(s.strongPasswordChecker(tests.get(i)));
            System.out.println(s.strongPasswordChecker(tests.get(i)) == results.get(i));
        }
    }
}