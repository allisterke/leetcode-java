package a547;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    private int getSet(int[] friends, int index) {
        if(friends[index] != index) {
            friends[index] = getSet(friends, friends[index]);
        }
        return friends[index];
    }

    private void unionSets(int[] friends, int index1, int index2) {
        int s1 = getSet(friends, index1);
        int s2 = getSet(friends, index2);
        if(s1 != s2) {
            friends[s1] = s2;
        }
    }

    public int findCircleNum(int[][] M) {
        int length = M.length;
        int[] friends = new int[length];
        for(int i = 0; i < length; ++ i) { // init disjoint sets
            friends[i] = i;
        }

        for(int i = 0; i < length; ++ i) {
            for(int j = i+1; j < length; ++ j) {
                if(M[i][j] == 1) {
                    unionSets(friends, i, j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < length; ++ i) {
            set.add(getSet(friends, i));
        }
        return set.size();
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.println(i);
        }
    }
}