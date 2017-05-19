package a019;

import java.util.Arrays;
import java.util.List;
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode _ = new ListNode(0);
        _.next = head;
        ListNode tail = head;
        for(int i = 0; i < n; ++ i) tail = tail.next;
        for(head = _; tail != null; head = head.next, tail = tail.next) ;
        head.next = head.next.next;
        return _.next;
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