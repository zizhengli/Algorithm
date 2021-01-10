package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class lc480_MedianSlidingWindow {

    /*
    How to keep two heaps balanced ???
    Use an integer, if the integer is less than 0, low heap needs more elements
    if the integer is greater than 0, high head needs more elements.
    Why doing this way, where there a number out, there is no efficient way to update two heaps,
    so that we need to do "lazy removing", which means use a variable to note the current status of
    two heaps and remove it out numbers when it's necessary (those numbers are at top of the heaps)
    */
    static public double[] medianSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return new double[0];
        }
        //Max heap
        PriorityQueue<Integer> low = new PriorityQueue<>(nums.length);
        // Min heap
        PriorityQueue<Integer> high = new PriorityQueue<>(nums.length, Comparator.reverseOrder());
        Map<Integer, Integer> map = new HashMap();

        int index = 0;
        while(index < k) {
            low.add(nums[index++]);
        }
        for(int j = 0; j < k / 2; j++) {
            high.add(low.poll());
        }

        List<Double> medians = new ArrayList<>();
        while(index < nums.length) {
            if(k % 2 == 0) {
                medians.add(((double)low.peek() + (double)high.peek()) * 0.5);
            } else {
                medians.add((double)low.peek());
            }

            int out = nums[index - k];
            int in = nums[index];
            index++;
            int balance = 0;

            if(out <= low.peek()) {
                balance--;
            } else {
                balance++;
            }
            map.put(out, map.getOrDefault(out, 0) + 1);

            if(!low.isEmpty() && in <= low.peek()) {
                balance++;
                low.add(in);
            } else {
                balance--;
                high.add(in);
            }

            // Rebalancing below
            // low needs more valid elements
            if(balance < 0) {
                low.add(high.poll());
                balance++;
            }
            // high needs more valid elements
            if(balance > 0) {
                high.add(low.poll());
                balance--;
            }

            // Remove out numbers
            while(map.containsKey(low.peek()) && map.get(low.peek()) > 0) {
                map.put(low.peek(), map.get(low.peek()) - 1);
                low.poll();
            }

            while(map.containsKey(high.peek()) && map.get(high.peek()) > 0) {
                map.put(high.peek(), map.get(high.peek()) - 1);
                high.poll();
            }
        }

        if(k % 2 == 0) {
            medians.add(((double)low.peek() + (double)high.peek()) * 0.5);
        } else {
            medians.add((double)low.peek());
        }

        double[] res = new double[medians.size()];
        for(int i = 0; i < medians.size(); i++) {
            res[i] = medians.get(i);
        }
        return res;
    }

    public static void main(String[] args) {

        int[] nums = {-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648};
        double[] medians = lc480_MedianSlidingWindow.medianSlidingWindow(nums, 3);

        for(Double d : medians) {
            System.out.println(d);
        }
    }
}
