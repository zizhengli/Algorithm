package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Design an algorithm that takes as input a set of intervals, and outputs their union
   expressed as a set of disjoint intervals.
 */
public class ComputeIntervals {

    static class Point {
        int val;
        boolean isClosed;

        Point(int val, boolean closed) {
            this.val = val;
            this.isClosed = closed;
        }
    }

    static class Interval implements Comparable<Interval> {

        Point start;
        Point end;

        Interval(Point s, Point e) {
            this.start = s;
            this.end = e;
        }

        @Override
        public int compareTo(Interval o) {
            if(this.start.val != o.start.val) {
                return this.start.val - o.start.val;
            }
            return this.end.val - o.end.val;
        }

        public String toString() {
            return (start.isClosed ? "[" : "(") + (start.val) + ", " + (end.val) + (end.isClosed ? "]" : ")");
        }
    }

    public static List<Interval> getUionOfIntervals(List<Interval> input) {
        List<Interval> res = new ArrayList<>();
        if(input == null || input.size() == 0) {
            return res;
        }
        Collections.sort(input);
        Point start = input.get(0).start;
        Point end = input.get(0).end;
        for(int i = 1; i < input.size(); i++) {
            if(end.val < input.get(i).start.val) {
                res.add(new Interval(start, end));
                start = input.get(i).start;
                end = input.get(i).end;
            } else if (end.val == input.get(i).start.val) {
                if(end.isClosed || input.get(i).start.isClosed) {
                    end = input.get(i).end;
                } else {
                    res.add(new Interval(start, end));
                    start = input.get(i).start;
                    end = input.get(i).end;
                }
            } else {
                if(end.val < input.get(i).end.val) {
                    end = input.get(i).end;
                } else if(end.val == input.get(i).end.val) {
                    end.isClosed = end.isClosed || input.get(i).end.isClosed;
                }
            }
        }
        res.add(new Interval(start, end));
        return res;
    }

    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(new Point(0, false), new Point(3, false)));
        list.add(new Interval(new Point(1, true), new Point(1, true)));
        list.add(new Interval(new Point(2, true), new Point(4, true)));
        list.add(new Interval(new Point(3, true), new Point(4, false)));
        list.add(new Interval(new Point(5, true), new Point(7, false)));
        list.add(new Interval(new Point(7, true), new Point(8, false)));
        list.add(new Interval(new Point(8, true), new Point(11, false)));
        list.add(new Interval(new Point(9, false), new Point(11, true)));
        list.add(new Interval(new Point(12, true), new Point(14, true)));
        list.add(new Interval(new Point(12, false), new Point(16, true)));
        list.add(new Interval(new Point(13, false), new Point(15, false)));
        list.add(new Interval(new Point(16, false), new Point(17, true)));

        List<Interval> result = getUionOfIntervals(list);

        System.out.println(result);
    }
}
