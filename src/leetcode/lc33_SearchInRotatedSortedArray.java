package leetcode;

/**
 *
 */
public class lc33_SearchInRotatedSortedArray {

    static int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;

        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            if(nums[mid] >= nums[low]) {
                // Make sure the target can be in the increasing range
                if(nums[mid] > target && target >= nums[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if(nums[mid] <= nums[high]) {
                // Make sure the target can be in the increasing range
                if(nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
