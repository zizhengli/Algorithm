package src.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by zizhengli on 1/30/17.
 */
public class lc239_SlidingWindowMaximum {

    /**
     * Using a deque to keep the maximum element
     * */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0) {
            return new int[0];
        }

        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque();
        int index = 0;
        while(index < nums.length) {
            // When sliding window size reached the k size then poll the first element
            if(!deque.isEmpty() && index - deque.peekFirst() >= k) {
                deque.pollFirst();
            }

            // Compare all element in deque with the new element from last element to the first if less than the new
            // element then poll the last element in deque.
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[index]) {
                deque.pollLast();
            }
            deque.add(index);
            if(index + 1 >= k) {
                result[index + 1 - k] = nums[deque.peekFirst()];
            }
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] test = {3,3,5,5,6,7};
        int[] result = lc239_SlidingWindowMaximum.maxSlidingWindow(test, 3);
        for(int i : result) {
            System.out.print(i + " ");
        }
    }
}
