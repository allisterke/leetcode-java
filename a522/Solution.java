package a522;
import java.util.*;

public class Solution {
    private boolean isSubString(String str, String sub) {
        if(sub.length() > str.length()) {
            return false;
        }
        int i = 0;
        int j = 0;
        for(; i < str.length() && j < sub.length(); ++i, ++ j) {
            while(i < str.length() && str.charAt(i) != sub.charAt(j)) {
                ++ i;
            }
            if(i >= str.length()) {
                break;
            }
        }
        return j >= sub.length();
    }

    private boolean isSubString(Collection<String> strs, String sub) {
        for(String str : strs) {
            if(isSubString(str, sub)) {
                return true;
            }
        }
        return false;
    }

    public int findLUSlength(String[] strs) {
        TreeMap<Integer, List<String>> map = new TreeMap<>();
        for(String str : strs) {
            map.putIfAbsent(str.length(), new ArrayList<>());
            map.get(str.length()).add(str);
        }

        Set<String> nonqualified = new HashSet<>();
        for(Iterator<Integer> it = map.descendingKeySet().iterator(); it.hasNext();) {
            int length = it.next();
            List<String> list = map.get(length);
            Map<String, Integer> count = new HashMap<>();
            for(String str : list) {
                count.putIfAbsent(str, 0);
                count.put(str, count.get(str) + 1);
            }
            for(String str : count.keySet()) {
                if(count.get(str) == 1) {
                    if(!isSubString(nonqualified, str)) { // test if sub-string
                        return length;
                    }
                }
            }
            nonqualified.addAll(list); // add to non-qualified
        }
        return -1;
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        s.isSubString("abcabc", "cca");
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.println(i);
        }
    }
}