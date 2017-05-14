package a582;

import java.util.*;

public class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, Set<Integer>> tree = new HashMap<>();
        for(int i = 0; i < pid.size(); ++ i) {
            if(!tree.containsKey(ppid.get(i))) {
                tree.put(ppid.get(i), new HashSet<>());
            }
            tree.get(ppid.get(i)).add(pid.get(i));
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(kill);
        List<Integer> dead = new ArrayList<>();
        dead.add(kill);
        while (!queue.isEmpty()) {
            Integer killed = queue.poll();
            if(tree.containsKey(killed)) {
                for(Integer child : tree.get(killed)) {
                    dead.add(child);
                    queue.add(child);
                }
            }
        }
        return dead;
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