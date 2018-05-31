package utils;

/**
 * Created by lim20 on 5/30/2018.
 */
public class Swap {

    public static void swap(int[] array, int a, int b) {
        if(array == null || array.length == 0) {
            return;
        }
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }
}
