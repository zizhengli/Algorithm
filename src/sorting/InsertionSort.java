package sorting;

import utils.Swap;

/**
 *
 */
public class InsertionSort {

    static void sort(int[] A) {
        if(A == null || A.length == 0) {
            return;
        }
        for(int i = 1; i < A.length; i++) {
            int get = A[i];
            int j = i - 1;
            while(j >= 0 && A[j] > get) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = get;
        }
    }

    public static void main(String[] args) {
        int[] A = {2, 3, 1, 8, 5, 9, 10, 11, 3};
        InsertionSort.sort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
