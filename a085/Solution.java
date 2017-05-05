package a085;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Solution {
    private int maximalRectangle(int[] heights) {
        int mr = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> index = new ArrayDeque<>();
        for(int i = 0; i < heights.length; ++ i) {
            int h = heights[i];
            if(h == 0) {
                while (!stack.isEmpty()) {
                    mr = Math.max(mr, stack.peekFirst() * (index.peekLast() - index.peekFirst() + 1));
                    stack.removeFirst();
                    index.removeFirst();
                }
                continue;
            }
            if(stack.isEmpty() || h > stack.peekLast()) {
                if(!stack.isEmpty()) {
                    stack.removeLast();
                    index.removeLast();
                }
                stack.addLast(h);
                stack.addLast(h);
                index.addLast(i);
                index.addLast(i);
//                mr = Math.max(mr, h);
            }
            else {
                int last = i;
                while (!stack.isEmpty() && h <= stack.peekLast()) {
                    mr = Math.max(mr, h * (i - index.peekLast() + 1));
                    mr = Math.max(mr, stack.peekLast() * (i - index.peekLast()));
                    stack.removeLast();
                    last = index.removeLast();
                }
                stack.addLast(h);
                stack.addLast(h);
                index.addLast(last);
                index.addLast(i);
            }
        }
        while (!stack.isEmpty()) {
            mr = Math.max(mr, stack.peekFirst() * (index.peekLast() - index.peekFirst() + 1));
            stack.removeFirst();
            index.removeFirst();
        }
        return mr;
    }

    public int maximalRectangle(char[][] matrix) {
        int R = matrix.length;
        int C = R > 0 ? matrix[0].length : 0;
        if(R == 0 || C == 0) {
            return 0;
        }
        int[] heights = new int[C];
        int mr = 0;
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
            }
            mr = Math.max(mr, maximalRectangle(heights));
        }
        return mr;
    }

    public static void main(String[] args) {
        List<char[][]> tests = Arrays.asList(
                new char[][][] {
                        new char[][] {
                                "01".toCharArray()
                        },
                        new char[][] {
                                "10110".toCharArray(),
                                "10111".toCharArray(),
                                "11111".toCharArray(),
                                "10010".toCharArray()
                        },
                        new char[][] {
                                "01101".toCharArray(),
                                "11010".toCharArray(),
                                "01110".toCharArray(),
                                "11110".toCharArray(),
                                "11111".toCharArray(),
                                "00000".toCharArray()
                        }
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.maximalRectangle(tests.get(i)));
        }
    }
}