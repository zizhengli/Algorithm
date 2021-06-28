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
        int[] pivot = threeWayPartition(array, start, end);
        quickSort(start, pivot[0] - 1, array);
        quickSort(pivot[1] + 1, end, array);
    }

    private static int partition(int start, int end, int[] array) {
        int indexToPlace = start - 1;
        int pivot = end;
        for(int i = start; i < end; i++) {
            if(array[i] < array[pivot]) {
                indexToPlace++;
                Swap.swap(array, i, indexToPlace);
            }
        }
        Swap.swap(array, ++indexToPlace, pivot);
        return indexToPlace;
    }

    public static int[] threeWayPartition(int[] array, int low, int high) {
        if(array == null || array.length == 0) {
            return new int[] {-1, -1};
        }

        int pivot = high;
        int index = low;
        high--;

        while(index <= high) {
            if(array[index] > array[pivot]) {
                Swap.swap(array, index, high--);
            } else if(array[index] < array[pivot]) {
                Swap.swap(array, index++, low++);
            } else {
                index++;
            }
        }
        Swap.swap(array, index, pivot);
        return new int[] {low, index};
    }

    public static void main(String[] args) {
        int[] A = {16, 3, 7, 8, 7, 9, 10, 11, 3, 7, 20, 7, 23, 7, 16, 3, 8, 21, 54, 10, 7};
        QuickSort.sort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println("==================");
        /*A = new int[] {16, 3, 7, 8, 7, 9, 10, 11, 3, 7, 20, 7, 23, 7};
        int[] res = threeWayPartition(A, 0, A.length - 1);
        System.out.print(res[0] + " " + res[1]);*/

        boolean[] array = new boolean[Integer.MAX_VALUE];
    }
}
