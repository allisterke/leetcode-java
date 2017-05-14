package a587;

import com.sun.corba.se.internal.Interceptors.PIORB;

import java.util.*;

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}

public class Solution {
    private double getAngle(Point a, Point b, double lastAngle) {
        double angle = 0;
        if (a.x == b.x) {
            angle = b.y > a.y ? Math.PI / 2 : Math.PI / 2 * 3;
        }
        else if(a.y == b.y && b.x > a.x) {
            angle = Math.PI * 2;
        }
        else {
            angle = Math.atan(1.0 * (b.y - a.y) / (b.x - a.x));
            if(b.x < a.x) {
                angle += Math.PI;
            }
            else if(b.y < a.y) {
                angle += 2 * Math.PI;
            }
        }
        if(angle < lastAngle) {
            angle += 2 * Math.PI;
        }
        return angle;
    }
    private double getDistance(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
    public List<Point> outerTrees(Point[] points) {
        if(points.length == 1) {
            return Arrays.asList(points);
        }
        Point bottom = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for(Point point : points) {
            if(point.y < bottom.y) {
                bottom = point;
            }
            else if(point.y == bottom.y && point.x > bottom.x) {
                bottom = point;
            }
        }
        Set<Point> visited = new HashSet<>();
        visited.add(bottom);
        double lastAngle = 0;
        for(Point last = bottom; ; ) {
            Point next = null;
            double angle = 0;
            double distance = 0;
            for(Point point : points) {
                if(point == last) {
                    continue;
                }
                if(next == null) {
                    next = point;
                    angle = getAngle(last, point, lastAngle);
                    distance = getDistance(last, point);
                }
                else {
                    double a = getAngle(last, point, lastAngle);
                    double d = getDistance(last, point);
                    if (a < angle || a == angle && d < distance) {
                        next = point;
                        angle = a;
                        distance = d;
                    }
                }
            }
            if(visited.contains(next)) {
                break;
            }
            visited.add(next);
            last = next;
            lastAngle = angle;
        }
        return new ArrayList<>(visited);
    }

    public static void main(String[] args) {
        List<Point[]> tests = Arrays.asList(
                new Point[][] {
//                        new Point[] {
//                                new Point(1, 1),
//                                new Point(2, 2),
//                                new Point(2, 0),
//                                new Point(2, 4),
//                                new Point(3, 3),
//                                new Point(4, 2)
//                        },
//                        new Point[] {
//                                new Point(0, 2),
//                                new Point(1, 1),
//                                new Point(3, 3),
//                                new Point(4, 2),
//                                new Point(2, 2),
//                                new Point(2, 4)
//                        }
//                        new Point[] {
//                                new Point(1, 2),
//                                new Point(2, 2),
//                                new Point(4, 2)
//                        }
                        new Point[] {
                                new Point(3,7),
                                new Point(6,8),
                                new Point(7,8),
                                new Point(11,10),
                                new Point(4,3),
                                new Point(8,5),
                                new Point(7,13),
                                new Point(4,13)
                        }
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.outerTrees(tests.get(i)));
        }
    }
}