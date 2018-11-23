package recursion;

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

    private static void helper(int[] array, int k) {
        if(k == 1) {
            // Print current array
            for(int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
        for(int i = 0; i < k; i++) {
            int temp = array[i];
            array[i] = array[k - 1];
            array[k - 1] = temp;
            helper(array, k - 1);
            temp = array[k - 1];
            array[k - 1] = array[i];
            array[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3000_0000};
        printPermutation(array);

    }
}
