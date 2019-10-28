package sorting;

import utils.Swap;

/**
 *
 */
public class QuickSort {

    static void sort(int[] A) {
        if(A == null || A.length == 0) {
            return;
        }
        quickSort(0, A.length - 1, A);
    }

    private static void quickSort(int start, int end, int[] array) {
        if(start >= end) {
            return;
        }
        int pivot = partition(start, end, array);
        quickSort(start, pivot - 1, array);
        quickSort(pivot + 1, end, array);
    }

    private static int partition(int start, int end, int[] array) {
        int smaller = start - 1;
        int pivot = end;
        for(int i = start; i < end; i++) {
            if(array[i] < array[pivot]) {
                smaller++;
                Swap.swap(array, i, smaller);
            }
        }
        Swap.swap(array, ++smaller, pivot);
        return smaller;
    }

    public static void main(String[] args) {
        int[] A = {16, 3, 1, 8, 5, 9, 10, 11, 3, 11, 20, 54, 23, 7};
        QuickSort.sort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
