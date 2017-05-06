package a282;

import java.util.*;

public class Solution {
    private long binaryOp(long a, long b, int op) {
        return op == 1 ? a + b : op == 2 ? a - b : a * b;
    }
    private boolean evaluate(String num, int[] ops, int target) {
        for(int i = 0; i < ops.length; ++ i) {
            if((i == 0 || ops[i-1] != 0) && ops[i] == 0 && num.charAt(i) == '0') {
                return false;
            }
        }
        int i = 0;
        long num1 = num.charAt(0) - '0';
        for(; i < ops.length && ops[i] == 0; num1 = num1 * 10 + num.charAt(i+1) - '0', ++ i) ;
        if(i == ops.length) {
            return num1 == target;
        }
        int op1 = ops[i ++];
        long num2 = num.charAt(i) - '0';
        for(; i < ops.length && ops[i] == 0; num2 = num2 * 10 + num.charAt(i+1) - '0', ++ i) ;

        while (i < ops.length) {
            int op2 = ops[i ++];
            long num3 = num.charAt(i) - '0';
            for (; i < ops.length && ops[i] == 0; num3 = num3 * 10 + num.charAt(i + 1) - '0', ++i) ;
            if (op2 == 3) { // multiply
                num2 *= num3;
            } else {
                num1 = binaryOp(num1, num2, op1);
                op1 = op2;
                num2 = num3;
            }
            if (i == ops.length) {
                break;
            }
        }
        return binaryOp(num1, num2, op1) == target;
    }

    private String buildArithmetic(String num, int[] ops) {
        StringBuilder sb = new StringBuilder();
        sb.append(num.charAt(0));
        for(int i = 0; i < ops.length; ++ i) {
            if(ops[i] != 0) {
                sb.append(ops[i] == 1 ? '+' : ops[i] == 2 ? '-' : '*');
            }
            sb.append(num.charAt(i+1));
        }
        return sb.toString();
    }

    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if(!num.isEmpty()) {
            int[] ops = new int[num.length() - 1];
            for (int i = 0; i < (1 << (2 * (num.length() - 1))); ++i) {
                for (int n = i, j = 0; j < num.length() - 1; ops[j] = n % 4, n /= 4, ++j) ;
                if (evaluate(num, ops, target)) {
                    result.add(buildArithmetic(num, ops));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "123",
                "232",
                "105",
                "00",
                "3456237490",
                "1234567890"
        );
        List<Integer> targets = Arrays.asList(
                6,
                8,
                5,
                0,
                9191,
                6
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.addOperators(tests.get(i), targets.get(i)));
        }
    }
}