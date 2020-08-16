package binarysearch;

public class BinarySearch_Template2 {

    static int binarySearch(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length;
        while(left < right){
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if(nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Post-processing:
        // End Condition: left == right
        if(left != nums.length)
            return left;
        return -1;
    }

    public static void main(String[] args) {

        int[] test = {1,2,3,4,6,7,8,10};

        System.out.println(BinarySearch_Template2.binarySearch(test, 9));

    }
}
