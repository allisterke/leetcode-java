package a551;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean checkRecord(String s) {
        int ac = 0;
        for(int i = 0; i < s.length(); ++ i) {
            if(s.charAt(i) == 'A') {
                ++ ac;
                if(ac >= 2) {
                    return false;
                }
            }
        }
        for(int i = 0; i < s.length() - 2; ++ i) {
            if(s.charAt(i) == 'L' && s.charAt(i+1) == 'L' && s.charAt(i+2) == 'L') {
                return false;
            }
        }
        return true;
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