package sorting;

import java.util.*;

/**
 * Write a program that takes a set of events, and determines the maximum number of
 events that take place concurrently.

 solution : Focus on end point, and as we only need to count the number of overlapped interval,
            try to consider each interval as two separated points.
 */
public class MaxOverlappedInterval {

    public static int getMaxNumOverlappedInterval(List<Event> events) {
        if(events == null || events.size() == 0) {
            return 0;
        }
        List<Endpoint> list = new ArrayList<>();
        for(Event e : events) {
            list.add(new Endpoint(e.start, true));
            list.add(new Endpoint(e.end, false));
        }
        Collections.sort(list);
        int maxOverlapped = 0;
        int currentOverlapped = 0;
        for(Endpoint endpoint : list) {
            if(endpoint.isStart) {
                currentOverlapped++;
            } else {
                currentOverlapped--;
            }
            maxOverlapped = Math.max(maxOverlapped, currentOverlapped);
        }
        return maxOverlapped;
    }

    static class Event {
        int start;
        int end;

        Event(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    static class Endpoint implements Comparable<Endpoint> {
        int point;
        boolean isStart;

        public Endpoint(int p, boolean isStart) {
            this.point = p;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Endpoint o) {
            if(this.point != o.point) {
                return Integer.compare(this.point, o.point);
            }
            return this.isStart && !o.isStart ? -1 : !this.isStart && o.isStart ? 1 : 0;
        }
    }

    public static void main(String[] args) {
        List<Event> input = new ArrayList<>();
        input.add(new Event(1, 5));
        input.add(new Event(2, 7));
        input.add(new Event(4, 5));
        input.add(new Event(6, 10));
        input.add(new Event(8, 9));
        input.add(new Event(9, 17));
        input.add(new Event(11, 13));
        input.add(new Event(12, 15));
        input.add(new Event(14, 15));
        input.add(new Event(12, 13));

        System.out.print(getMaxNumOverlappedInterval(input));
    }
}
