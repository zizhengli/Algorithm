package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class lc56_MergeIntervals {
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

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return new ArrayList<>();
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval int1, Interval int2) {
                if (int1.start != int2.start) {
                    return int1.start - int2.start;
                }
                return int1.end - int2.end;
            }
        });
        List<Interval> res = new ArrayList<>();
        int currStart = intervals.get(0).start;
        int currEnd = intervals.get(0).end;
        for (int i = 1; i < intervals.size(); i++) {
            if (currEnd >= intervals.get(i).start) {
                currEnd = Math.max(currEnd, intervals.get(i).end);
            } else {
                res.add(new Interval(currStart, currEnd));
                currStart = intervals.get(i).start;
                currEnd = intervals.get(i).end;
            }
        }
        res.add(new Interval(currStart, currEnd));
        return res;
    }
}
