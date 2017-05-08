package a051;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    private List<List<String>> result;

    private List<String> buildResult(char[][] board) {
        return Arrays.stream(board).map(line -> new String(line)).collect(Collectors.toList());
    }

    private void checkValid(char[][] board, int level) {
        if(level >= board.length) {
            result.add(buildResult(board));
        }
        else {
            for(int i = 0; i < board.length; ++ i) {
                boolean valid = true;
                for(int j = 0; j < level; ++ j) {
                    if(board[j][i] == 'Q') {
                        valid = false;
                        break;
                    }
                }
                if(!valid) {
                    continue;
                }
                for(int j = 1; j <= Math.min(i, level); ++ j) {
                    if(board[level-j][i-j] == 'Q') {
                        valid = false;
                        break;
                    }
                }
                if(!valid) {
                    continue;
                }
                for(int j = 1; i + j < board.length && level - j >= 0; ++ j) {
                    if(board[level-j][i+j] == 'Q') {
                        valid = false;
                        break;
                    }
                }
                if(!valid) {
                    continue;
                }
                board[level][i] = 'Q';
                checkValid(board, level + 1);
                board[level][i] = '.';
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][];
        for(int i = 0; i < n; ++ i) {
            board[i] = new char[n];
            Arrays.fill(board[i], '.');
        }
        result = new ArrayList<>();
        checkValid(board, 0);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                0,
                4
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.solveNQueens(tests.get(i)));
        }
    }
}