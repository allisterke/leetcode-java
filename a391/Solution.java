package a391;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        Map<Long, int[]> map = new HashMap<>();
        long minx = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE;
        long miny = Integer.MAX_VALUE, maxy = Integer.MIN_VALUE;
        long area = 0;
        for(int[] rect : rectangles) {
            area += ((long)rect[2] - rect[0]) * ((long)rect[3] - rect[1]);
            int[] xs = new int[] { rect[0], rect[2] };
            int[] ys = new int[] { rect[1], rect[3] };
            for(int x : xs) {
                minx = Math.min(minx, x);
                maxx = Math.max(maxx, x);
            }
            for(int y : ys) {
                miny = Math.min(miny, y);
                maxy = Math.max(maxy, y);
            }
            int index = 0;
            for(int x : xs) {
                for(int y : ys) {
                    long point = ((long)x << 32) | ((long)y & 0x00000000ffffffffL);
                    map.putIfAbsent(point, new int[4]);
                    if(map.get(point)[index] == 1) {
                        return false;
                    }
                    map.get(point)[index] = 1;
                    ++ index;
                }
            }
        }
        if(area != (maxx - minx) * (maxy - miny)) {
            return false;
        }
        int odd = 0;
        for(Map.Entry<Long, int[]> entry : map.entrySet()) {
            int count = Arrays.stream(entry.getValue()).filter(mark -> mark == 1).sum();
            if(count != 2 && count != 4) {
                if(count != 1 || ++ odd > 4) {
                    return false;
                }
            }
        }
        return odd == 4;
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