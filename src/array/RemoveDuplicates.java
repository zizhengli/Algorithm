package array;

import utils.Print;

/**
 *
 */
public class RemoveDuplicates {

    static void removeDup(int[] array) {
        if(array == null || array.length == 0) {
            return;
        }
        int repeated = 1;
        for(int i = 1; i < array.length; i++) {
            if(array[i] != array[i - 1]) {
                array[repeated++] = array[i];
            }
        }
        while(repeated < array.length) {
            array[repeated++] = 0;
        }
    }

    public static void main(String[] args) {

        int[] array = {1, 1, 5, 5, 11, 11, 11, 11};
        removeDup(array);
        Print.print(array);
    }
}
