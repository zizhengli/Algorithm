package leetcode;

import java.util.ArrayList;
import java.util.List;

public class lc57_InsertInterval {
    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            res.add(newInterval);
            return res;
        }
        int index = 0;

        while (index < intervals.size() && newInterval.start > intervals.get(index).end) {
            res.add(intervals.get(index));
            index++;
        }
        while (index < intervals.size() && newInterval.end >= intervals.get(index).start) {
            newInterval.start = Math.min(newInterval.start, intervals.get(index).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(index).end);
            index++;
        }
        res.add(new Interval(newInterval.start, newInterval.end));
        while (index < intervals.size()) {
            res.add(intervals.get(index));
            index++;
        }
        return res;
    }
}
