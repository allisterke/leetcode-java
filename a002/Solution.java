package a002;

import java.util.Arrays;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode(0);
        for (ListNode p = head, p1 = l1, p2 = l2; carry > 0 || p1 != null || p2 != null; ) {
            if(p1 != null) {
                carry += p1.val;
                p1 = p1.next;
            }
            if(p2 != null) {
                carry += p2.val;
                p2 = p2.next;
            }
            p = p.next = new ListNode(carry % 10);
            carry /= 10;
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