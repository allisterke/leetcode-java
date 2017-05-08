package a025;

import java.util.Arrays;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (head.next != null) {
            ListNode tail = head.next;
            ListNode rh = head.next;
            head.next = head.next.next;
            int i = 1;
            for(; i < k && head.next != null; ++ i) {
                ListNode tmp = head.next;
                head.next = head.next.next;
                tmp.next = rh;
                rh = tmp;
            }
            if(i == k) {
                tail.next = head.next;
                head.next = rh;
                head = tail;
            }
            else {
                head.next = null;
                for(ListNode p = rh; p != tail;) {
                    ListNode tmp = p.next;
                    p.next = head.next;
                    head.next = p;
                    p = tmp;
                }
                tail.next = head.next;
                head.next = tail;
                head = rh;
            }
        }
        return dummy.next;
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