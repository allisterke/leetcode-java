package a149;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}

class MyPoint extends Point {
    MyPoint(int a, int b) { super(a, b); }
    public int hashCode() {
        return x ^ y;
    }
    public boolean equals(Object o) {
        if(o != null && o instanceof MyPoint) {
            MyPoint oo = (MyPoint)o;
            return x == oo.x && y == oo.y;
        }
        return false;
    }
}

public class Solution {
    private List<BigInteger> buildLine(MyPoint p1, MyPoint p2) {
        List<BigInteger> line;
        if(p1.x == p2.x) {
            line = Arrays.asList(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.ZERO, BigInteger.valueOf(p1.x), BigInteger.ONE);
        }
        else if(p1.y == p2.y) {
            line = Arrays.asList(BigInteger.ZERO, BigInteger.ONE, BigInteger.valueOf(p1.y), BigInteger.ONE);
        }
        else {
            BigInteger k1 = BigInteger.valueOf(p1.y - p2.y);
            BigInteger k2 = BigInteger.valueOf(p1.x - p2.x);
            BigInteger c1 = k1.gcd(k2);
            BigInteger b1 = BigInteger.valueOf((long)p2.x * p1.y - (long)p1.x * p2.y);
            BigInteger b2 = BigInteger.valueOf((long)p2.x - p1.x);
            BigInteger c2 = b1.gcd(b2);

            line = Arrays.asList(
                    k1.divide(c1), k2.divide(c1), b1.divide(c2), b2.divide(c2)
            );
        }
        return line;
    }
    public int maxPoints(Point[] points) {
        MyPoint[] myPoints = new MyPoint[points.length];
        for(int i = 0; i < points.length; ++ i) {
            myPoints[i] = new MyPoint(points[i].x, points[i].y);
        }

        Map<MyPoint, Long> pc = Arrays.stream(myPoints).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if(pc.size() <= 2) {
            return myPoints.length;
        }
        Map<List<BigInteger>, Set<MyPoint>> lc = new HashMap<>();

        MyPoint[] dps = new MyPoint[pc.keySet().size()];
        pc.keySet().toArray(dps);
        for(int i = 0; i < dps.length; ++ i) {
            for(int j = i+1; j < dps.length; ++ j) {
                List<BigInteger> line = buildLine(dps[i], dps[j]);
                lc.putIfAbsent(line, new HashSet<>());
                lc.get(line).add(dps[i]);
                lc.get(line).add(dps[j]);
            }
        }

        int mp = 0;
        for(Set<MyPoint> sp : lc.values()) {
            int pn = 0;
            for(MyPoint p : sp) {
                pn += pc.get(p);
            }
            mp = Math.max(mp, pn);
        }

        return mp;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        List<MyPoint[]> tests = Arrays.asList(
                new MyPoint[] {},
                new MyPoint[]{new MyPoint(1,2), new MyPoint(3,4)},
                new MyPoint[]{new MyPoint(0,0), new MyPoint(1,1), new MyPoint(1, -1)},
                new MyPoint[]{new MyPoint(0,0), new MyPoint(94911151, 94911150), new MyPoint(94911152, 94911151)}
        );
        List<Integer> results = Arrays.asList(0, 2, 2, 2);
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.format("%d of %d\n", i, tests.size());
            int r = s.maxPoints(tests.get(i));
            System.out.println(r);
            assert r == results.get(i);
        }
    }
}