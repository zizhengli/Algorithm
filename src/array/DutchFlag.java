package array;

import utils.Print;
import utils.Swap;

/**
 *
 */
public class DutchFlag {

    static void dutchFlagPartition(int[] array, int pivot) {
        if(array == null || array.length == 0) {
            return;
        }
        int smaller = -1;
        for(int i = 0; i < array.length; i++) {
            if(array[i] < pivot) {
                smaller++;
                Swap.swap(array, i, smaller);
            }
        }
        int larger = array.length;
        for(int i = array.length - 1; i >= 0; i--) {
            if(array[i] > pivot) {
                larger--;
                Swap.swap(array, i, larger);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {0, 2, 1, 0 , 2 ,1 , 0 , 2, 1, 2 , 1, 0};
        dutchFlagPartition(array, 1);
        Print.print(array);
    }
}
