package binarysearch;

/**
 * Created by zizhengli on 10/10/18.
 */
public class GetMinInSortedRotatedArray {

    static int getMin(int[] array) {
        if(array == null || array.length == 0) {
            return -1;
        }

        int start = 0;
        int end = array.length - 1;

        while(start < end) {
            if(end - start == 1) {
                return end;
            }
            int mid = start + (end - start) / 2;
            if(array[mid] >= array[start]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {4, 5, 6, 7, 8, 1, 2, 3};
        System.out.println(getMin(array));
    }
}
