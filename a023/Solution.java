package a023;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        ListNode head = new ListNode(0);
        ListNode tail = head;
        for(ListNode node : lists) {
            if(node != null) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;
            if(tail.next != null) {
                queue.add(tail.next);
            }
        }
        return head.next;
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