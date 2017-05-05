package a381;

import java.util.*;

class RandomizedCollection {

    Map<Integer, Set<Integer>> index = new HashMap<>();
    List<Integer> collection = new ArrayList<>();

    /** Initialize your data structure here. */
    public RandomizedCollection() {

    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if(!index.containsKey(val)) {
            index.put(val, new HashSet<>());
        }
        index.get(val).add(collection.size());
        collection.add(val);
        return index.get(val).size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(index.containsKey(val)) {
            int i = index.get(val).iterator().next();
            index.get(val).remove(i);
            if(index.get(val).isEmpty()) {
                index.remove(val);
            }
            int j = collection.size() - 1;
            if(i != j) {
                int last = collection.get(j);
                index.get(last).remove(j);
                index.get(last).add(i);
                collection.set(i, last);
            }
            collection.remove(j);
            return true;
        }
        return false;
    }

    Random r = new Random();

    /** Get a random element from the collection. */
    public int getRandom() {
        return collection.get(r.nextInt(collection.size()));
    }
}

public class Solution {


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