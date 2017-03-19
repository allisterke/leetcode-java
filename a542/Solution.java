package a542;

import java.util.*;

public class Solution {
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        Deque<List<Integer>> edge = new ArrayDeque<>();
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < matrix.size(); ++ i) {
            result.add(new ArrayList<>());
            for(int j = 0; j < matrix.get(i).size(); ++ j) {
                result.get(i).add(Integer.MAX_VALUE);
            }
        }

        for(int i = 0; i < matrix.size(); ++ i) {
            for(int j = 0; j < matrix.get(i).size(); ++ j) {
                if(matrix.get(i).get(j) == 0) {
                    edge.push(Arrays.asList(i, j, 0));
                    result.get(i).set(j, 0);
                }
            }
        }

        while (!edge.isEmpty()) {
            List<Integer> node = edge.pollFirst();
            int i = node.get(0);
            int j = node.get(1);
            int k = node.get(2);
            if(i > 0 && result.get(i-1).get(j) == Integer.MAX_VALUE) {
                result.get(i-1).set(j, k+1);
                edge.add(Arrays.asList(i-1, j, k+1));
            }
            if(j > 0 && result.get(i).get(j-1) == Integer.MAX_VALUE) {
                result.get(i).set(j-1, k+1);
                edge.add(Arrays.asList(i, j-1, k+1));
            }
            if(i+1 < result.size() && result.get(i+1).get(j) == Integer.MAX_VALUE) {
                result.get(i+1).set(j, k+1);
                edge.add(Arrays.asList(i+1, j, k+1));
            }
            if(j+1 < result.get(0).size() && result.get(i).get(j+1) == Integer.MAX_VALUE) {
                result.get(i).set(j+1, k+1);
                edge.add(Arrays.asList(i, j+1, k+1));
            }
        }

        return result;
    }
//    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
//        int[][] dist = new int[matrix.size()][];
//        for(int i = 0; i < dist.length; ++ i) {
//            dist[i] = new int[matrix.get(0).size()];
//            for(int j = 0; j < dist[i].length; ++ j) {
//                dist[i][j] = Integer.MAX_VALUE;
//            }
//        }
//
//        for(int i = 0; i < matrix.size(); ++ i) {
//            for(int j = 0; j < matrix.get(0).size(); ++ j) {
//                if(dist[i][j] == Integer.MAX_VALUE) {
//                    if(matrix.get(i).get(j) == 0) {
//                        dist[i][j] = 0;
//                    }
//                    else {
//                        shortest(matrix, dist, i, j);
//                    }
//                }
//            }
//        }
//
//        List<List<Integer>> dm = new ArrayList<>();
//        for(int i = 0; i < dist.length; ++ i) {
//            dm.add(new ArrayList<>());
//            for(int j = 0; j < dist[i].length; ++ j) {
//                dm.get(i).add(dist[i][j]);
//            }
//        }
//        return dm;
//    }
//
//    int shortest(List<List<Integer>> matrix, int[][] dist, int i, int j) {
//        if(i < 0 || i >= dist.length || j < 0 || j >= dist[0].length) {
//            return Integer.MAX_VALUE;
//        }
//        if(dist[i][j] == Integer.MAX_VALUE) {
//
//            dist[i][j] = Math.min(dist[i][j], shortest(matrix, dist, i+1, j));
//            dist[i][j] = Math.min(dist[i][j], shortest(matrix, dist, i-1, j));
//            dist[i][j] = Math.min(dist[i][j], shortest(matrix, dist, i, j+1));
//            dist[i][j] = Math.min(dist[i][j], shortest(matrix, dist, i, j-1));
//        }
//        return dist[i][j];
//    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        for(int i = 0; i < tests.size(); ++ i) {

        }
    }
}