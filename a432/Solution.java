package a432;

import java.util.*;

class AllOne {
    class Node {
        int previous, next;
        Set<String> keys = new HashSet<>();
    }
    Map<String, Integer> map = new HashMap<>();
    Map<Integer, Node> reverse = new HashMap<>();

    /** Initialize your data structure here. */
    public AllOne() {
        Node zero = new Node();
        Node max = new Node();
        zero.previous = Integer.MAX_VALUE;
        zero.next = Integer.MAX_VALUE;
        max.previous = 0;
        max.next = 0;
        reverse.put(0, zero);
        reverse.put(Integer.MAX_VALUE, max);
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int val = map.containsKey(key) ? map.get(key) : 0;
        map.put(key, val + 1);

        reverse.get(val).keys.remove(key);
        if(reverse.get(val).next != val + 1) {
            Node node = new Node();
            node.previous = val;
            node.next = reverse.get(val).next;
            reverse.get(node.next).previous = val + 1;
            reverse.get(node.previous).next = val + 1;
            reverse.put(val + 1, node);
        }
        reverse.get(val + 1).keys.add(key);

        if(val > 0 && reverse.get(val).keys.isEmpty()) {
            reverse.get(val + 1).previous = reverse.get(val).previous;
            reverse.get(reverse.get(val + 1).previous).next = val + 1;
            reverse.remove(val);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!map.containsKey(key)) {
            return;
        }
        int val = map.get(key);
        if(val == 1) {
            map.remove(key);
        }
        else {
            map.put(key, val - 1);
        }

        reverse.get(val).keys.remove(key);
        if(reverse.get(val).previous != val - 1) {
            Node node = new Node();
            node.next = val;
            node.previous = reverse.get(val).previous;
            reverse.get(node.previous).next = val - 1;
            reverse.get(node.next).previous = val - 1;
            reverse.put(val - 1, node);
        }
        if(val != 1) {
            reverse.get(val - 1).keys.add(key);
        }

        if(reverse.get(val).keys.isEmpty()) {
            reverse.get(val - 1).next = reverse.get(val).next;
            reverse.get(reverse.get(val - 1).next).previous = val - 1;
            reverse.remove(val);
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        try {
            return reverse.get(reverse.get(Integer.MAX_VALUE).previous).keys.iterator().next();
        } catch (Exception e) {
            return "";
        }
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        try {
            return reverse.get(reverse.get(0).next).keys.iterator().next();
        } catch (Exception e) {
            return "";
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        AllOne obj = new AllOne();
        obj.inc("a");
        obj.inc("a");
        obj.inc("b");
        obj.inc("b");
        obj.dec("a");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
    }
}