package a502;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    class Project {
        int profit, capital;
        public Project(int p, int c) {
            profit = p;
            capital = c;
        }
    }
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Project[] projects = new Project[Profits.length];
        for(int i = 0; i < projects.length; ++ i) {
            projects[i] = new Project(Profits[i], Capital[i]);
        }
        Arrays.sort(projects, (a, b) -> Integer.compare(a.capital, b.capital));
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        int start = 0;
        while (k > 0) {
            for(; start < projects.length && projects[start].capital <= W; queue.add(projects[start].profit), ++ start) ;
            if(!queue.isEmpty()) {
                W += queue.poll();
                -- k;
            }
            else {
                break;
            }
        }
        return W;
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