package array;

import utils.Print;

/**
 *
 */
public class RemoveKey {

    static int removeKey(int[] array, int key) {
        if(array == null || array.length == 0) {
            return -1;
        }
        int remove = 0;
        for(int i = 1; i < array.length && remove < array.length; i++) {
            if(array[i - 1] != key) {
                remove++;
            }
            array[remove] = array[i];
        }
        return remove;
    }

    public static void main(String[] args) {
        int[] array = {6, 2, 5, 6, 6, 11, 11, 6, 14, 6};
        System.out.println(removeKey(array, 100));
        Print.print(array);
    }
}
