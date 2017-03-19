package a126;

import java.util.*;

public class Solution {
    private boolean testReachable(String a, String b) {
        int diff = 0;
        for(int i = 0; i < a.length(); ++ i) {
            if(a.charAt(i) != b.charAt(i)) {
                if(diff > 0) {
                    return false;
                }
                else {
                    diff = 1;
                }
            }
        }
        return true;
    }

    private void addReachablePath(String a, String b, Map<String, Set<String>> map) {
        map.putIfAbsent(a, new HashSet<>());
        map.get(a).add(b);
    }

    private Set<String> getNextLevel(Set<String> currentLevel, Set<String> words, Map<String, Set<String>> map) {
        Set<String> nextLevel = new HashSet<>();
        currentLevel.forEach(word -> {
            char[] a = word.toCharArray();
            for(int i = 0; i < a.length; ++ i) {
                char oc = a[i];
                for(char nc = 'a'; nc <= 'z'; ++ nc) {
                    if(nc == oc) {
                        continue;
                    }
                    a[i] = nc;
                    String ns = new String(a);
                    if(words.contains(ns)) {
                        nextLevel.add(ns);
                        map.putIfAbsent(word, new HashSet<>());
                        map.get(word).add(ns);
                    }
                }
                a[i] = oc;
            }
        });
        words.removeAll(nextLevel);
        return nextLevel;
    }

    private Map<String, Set<String>> buildReachableMap2(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>();
        words.addAll(wordList);
        words.add(endWord);

        Set<String> currentLevel = new HashSet<>(Arrays.asList(beginWord));
        Map<String, Set<String>> map = new HashMap<>();
        while(!currentLevel.isEmpty()) {
            currentLevel = getNextLevel(currentLevel, words, map);
        }
        return map;
    }

    private Map<String, Set<String>> buildReachableMap(String beginWord, String endWord, List<String> wordList) {
        Map<String, Set<String>> map = new HashMap<>();
        if(testReachable(beginWord, endWord)) {
            addReachablePath(beginWord, endWord, map);
        }
        else {
            for(String word : wordList) {
                if(testReachable(beginWord, word)) {
                    addReachablePath(beginWord, word, map);
                }
                if(testReachable(word, endWord)) {
                    addReachablePath(word, endWord, map);
                }
            }
            for(Iterator<String> it1 = wordList.iterator(); it1.hasNext(); ) {
                String a = it1.next();
                for(Iterator<String> it2 = wordList.iterator(); it2.hasNext(); ) {
                    String b = it2.next();
                    if(a != b && testReachable(a, b)) {
                        addReachablePath(a, b, map);
                    }
                }
            }
        }
        return map;
    }

    private int findShortestPath(String beginWord, String endWord, Map<String, Set<String>> map) {
        Deque<String> queue = new ArrayDeque<>();
        Deque<Integer> dist = new ArrayDeque<>();
        queue.add(beginWord);
        dist.add(0);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while(!queue.isEmpty()) {
            String w = queue.poll();
            int d = dist.poll();
            Set<String> set = map.get(w);
            if(set == null) {
                continue;
            }
            for(String s : set) {
                if(s.equals(endWord)) {
                    return d + 1;
                }
                if(!visited.contains(s)) {
                    visited.add(s);
                    queue.add(s);
                    dist.add(d + 1);
                }
            }
        }
        return -1;
    }

    private void collectShortestPath(String current, String endWord, Map<String, Set<String>> map, int dist,
                                     Set<String> visited, List<String> path, List<List<String>> paths) {
        path.add(current);
        visited.add(current);
        if(dist <= 0) {
            if(current.equals(endWord)) {
                paths.add(new LinkedList<>(path));
            }
        }
        else {
            Set<String> set = map.get(current);
            if(set != null) {
                set.forEach(s -> {
                    if(!visited.contains(s)) {
                        collectShortestPath(s, endWord, map, dist-1, visited, path, paths);
                    }
                });
            }
        }
        visited.remove(current);
        path.remove(path.size()-1);
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
//        Map<String, Set<String>> map = buildReachableMap(beginWord, endWord, wordList);
        Map<String, Set<String>> map = buildReachableMap2(beginWord, endWord, wordList);
        List<String> path = new ArrayList<>();
        List<List<String>> paths = new ArrayList<>();
        if(wordList.contains(endWord)) {
            int dist = findShortestPath(beginWord, endWord, map);
            if (dist > 0) {
                Set<String> visited = new HashSet<>();
                collectShortestPath(beginWord, endWord, map, dist, visited, path, paths);
            }
        }
        return paths;
    }

    public static void main(String[] args) {
        List<String> beginWords = Arrays.asList(new String[]{"hit", "hit"});
        List<String> endWords = Arrays.asList(new String[]{"cog", "cog"});
        List<List<String>> wordLists = new ArrayList<>();
        wordLists.add(Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"}));
        wordLists.add(Arrays.asList(new String[]{"hot","dot","dog","lot","log"}));

        Solution s = new Solution();
        for(int i = 0; i < beginWords.size(); ++ i) {
            System.out.format("%d of %d\n", i, beginWords.size());
            List<List<String>> paths = s.findLadders(beginWords.get(i), endWords.get(i), wordLists.get(i));
            paths.forEach(path -> {
                path.forEach(w -> {
                    System.out.print(w);
                    System.out.print(' ');
                });
                System.out.println();
            });
        }
    }
}