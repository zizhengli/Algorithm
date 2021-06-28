package leetcode;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * Example:

         Input: [-2,1,-3,4,-1,2,1,-5,4],
         Output: 6
         Explanation: [4,-1,2,1] has the largest sum = 6.
 */
public class lc53_MaximumSubarray {

    /*
    * Approach 1
    * */
    static int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int maxSoFar = nums[0];
        int maxCurrent = nums[0];

        for(int i = 1; i < nums.length; i++) {
            maxCurrent = Math.max(maxCurrent + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxCurrent);
        }
        return maxSoFar;
    }

    /*
    * Divide and conquor
    * */
    static int maxSubArray2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        return subMaxArray(nums, 0, nums.length - 1);
    }

    static private int subMaxArray(int[] nums, int start, int end) {
        if(start == end) {
            return nums[start];
        }
        int mid = start + (end - start) / 2;
        int leftMax = subMaxArray(nums, start, mid);
        int rightMax = subMaxArray(nums, mid + 1, end);
        int middleMax = middleMaxArray(nums, start, mid, end);
        return Math.max(Math.max(leftMax, rightMax), middleMax);
    }

    static private int middleMaxArray(int[] nums, int start, int mid, int end) {
        int i = mid;
        int j = mid + 1;
        int midSum = nums[i] + nums[j];
        int max = midSum;
        while(i >= start) {
            max = Math.max(max, midSum += nums[i--]);
        }
        midSum = max;
        while(j <= end) {
            max = Math.max(max, midSum += nums[j++]);
        }
        return max;
    }
}
