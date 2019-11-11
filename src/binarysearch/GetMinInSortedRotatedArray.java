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

    static int findElement(int[] array, int i) {
        if(array == null || array.length == 0) {
            return -1;
        }
        int start = 0;
        int end = array.length - 1;

        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(i < array[mid]) {
                end = mid - 1;
            } else if (i > array[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] array = {4, 5, 6, 7, 8, 1, 2, 3};
        System.out.println(getMin(array));

        int[] array2 = {1, 2, 3, 7, 8, 11, 21, 33};
        System.out.println(findElement(array2, 90));

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 6; j++) {
                break;
            }
            System.out.println(i);
        }
    }
}
