package a544;
import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    public String findContestMatch(int n) {
        List<Deque<Object>> sbl = new ArrayList<>();
        for(int i = 1; i <= n; ++ i) {
            sbl.add(new ArrayDeque<>(Arrays.asList(i)));
        }
        while (sbl.size() > 1) {
            for(int i = 0; i < sbl.size()/2; ++ i) {
                sbl.get(i).add(",");
                sbl.get(i).addAll(sbl.get(sbl.size() - 1 - i));
                sbl.get(i).addFirst("(");
                sbl.get(i).addLast(")");
            }
            int ns = sbl.size() / 2;
            while (sbl.size() > ns) {
                sbl.remove(sbl.size() - 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Object o : sbl.get(0)) {
            sb.append(o.toString());
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        for(int i = 0; i < tests.size(); ++ i) {

        }
    }
}