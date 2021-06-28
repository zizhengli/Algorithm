package sorting;

import utils.Swap;

/**
 *
 */
public class SelectionSort {

    static void sort(int[] A) {
        if(A == null || A.length == 0) {
            return;
        }
        int minIdx = 0;
        for(int i = 0; i < A.length; i++) {
            minIdx = i;
            for(int j = i + 1; j < A.length; j++) {
                if(A[j] < A[minIdx]) {
                    minIdx = j;
                }
            }
            if(minIdx != i) {
                Swap.swap(A, minIdx, i);
            }
        }
    }

    public static void main(String[] args) {
        int[] A = {2, 3, 1, 8, 5, 9, 10, 11, 3};
        SelectionSort.sort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
