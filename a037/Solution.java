package a037;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private boolean solveSudokuRecursively(char[][] board) {
        for(int i = 0; i < 9; ++ i) {
            for(int j = 0; j < 9; ++ j) {
                if(board[i][j] == '.') {
                    boolean[] used = new boolean[9];
                    for(int k = 0; k < 9; ++ k) {
                        if(board[i][k] != '.') {
                            used[board[i][k] - '1'] = true;
                        }
                        if(board[k][j] != '.') {
                            used[board[k][j] - '1'] = true;
                        }
                        if(board[i/3*3+k/3][j/3*3+k%3] != '.') {
                            used[board[i/3*3+k/3][j/3*3+k%3] - '1'] = true;
                        }
                    }
                    for(int k = 0; k < 9; ++ k) {
                        if(!used[k]) {
                            board[i][j] = (char)('1' + k);
                            if(solveSudokuRecursively(board)) {
                                return true;
                            }
                        }
                    }
                    board[i][j] = '.';
                    return false;
                }
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        solveSudokuRecursively(board);
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