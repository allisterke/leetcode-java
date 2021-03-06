package a521;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int findLUSlength(String a, String b) {
        return a.equals(b) ? 0 : Math.max(a.length(), b.length());
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