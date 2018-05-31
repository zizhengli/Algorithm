package sorting;

import utils.Swap;

/**
 * Based on bubble sort, try to sort from both ends
 */
public class CocktailSort {
    static void sort(int A[]) {
        if(A == null || A.length == 0) {
            return;
        }
        int left = 0;
        int right = A.length - 1;

        while(left <= right) {
            for(int i = left; i < right; i++) {
                if(A[i] > A[i + 1]) {
                    Swap.swap(A, i, i + 1);
                }
            }
            right--;
            for(int j = right; j > left; j--) {
                if(A[j - 1] > A[j]) {
                    Swap.swap(A, j - 1, j);
                }
            }
            left++;
        }
    }

    public static void main(String[] args) {
        int[] A = {2, 3, 1, 8, 5, 9, 10, 11, 3};
        CocktailSort.sort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
