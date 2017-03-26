package a507;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean checkPerfectNumber(int num) {
        if(num == 1) {
            return false;
        }
        int sum = 1;
        int i = 2;
        for(; i * i < num; ++ i) {
            if(num % i == 0) {
                sum += i + num / i;
            }
        }
        if(i * i == num) {
            sum += i;
        }
        return sum == num;
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(28);
        List<Boolean> results = Arrays.asList(true);

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.println(i);
            System.out.println(s.checkPerfectNumber(tests.get(i)));
        }
    }
}