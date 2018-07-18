package array;

import utils.Print;
import utils.Swap;

/**
 *
 */
public class ClassifyEvenNumber {

    static void evenOdd(int[] array) {
        if(array == null || array.length == 0) {
            return;
        }
        int evenIdx = array.length - 1;
        int oddIdx = 0;

        while(oddIdx < evenIdx) {
            if((array[oddIdx] & 1) == 1) {
                // odd number
                Swap.swap(array, oddIdx, evenIdx);
                evenIdx--;
            } else {
                // even number
                oddIdx++;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 4, 3, 7, 8, -1 ,10 ,9 , 15};
        evenOdd(array);
        Print.print(array);
    }
}
