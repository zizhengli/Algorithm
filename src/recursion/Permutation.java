package recursion;

import utils.Swap;

/**
 *
 */
public class Permutation {

    static void printPermutation(int[] array) {
        if(array == null || array.length == 0) {
            return;
        }
        helper(array, array.length);
    }

    private static void helper(int[] array, int sizeToSwap) {
        if(sizeToSwap == 1) {
            // Print current array
            for(int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
        for(int i = 0; i < sizeToSwap; i++) {
            Swap.swap(array, i, sizeToSwap - 1);
            helper(array, sizeToSwap - 1);
            Swap.swap(array, i, sizeToSwap - 1);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        printPermutation(array);

    }
}
