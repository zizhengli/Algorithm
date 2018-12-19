package sorting;

import utils.Swap;

/**
 *
 */
public class HeapSort {

    static void sort(int[] A) {
        if(A == null || A.length == 0) {
            return;
        }
        for (int i = A.length / 2 - 1; i >= 0; i--) {
            heapify(A, i, A.length);
        }
        for(int i = A.length - 1; i >= 0; i--) {
            Swap.swap(A, i, 0);
            heapify(A, 0, i);
        }
    }

    private static void heapify(int[] array, int index, int size) {
        int left = 0;
        int right = 0;
        int largest = index;
        while(index >= 0) {
            left = 2 * index + 1;
            right = 2 * index + 2;
            if(left < size && array[largest] < array[left]) {
                largest = left;
            }
            if(right < size && array[largest] < array[right]) {
                largest = right;
            }
            if(largest != index) {
                Swap.swap(array, largest, index);
                index = largest;
            } else {
                break;
            }
        }
    }

    private static void heapifyRecursive(int[] array, int index, int size) {
        int max = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if(left < size && array[left] > array[max]) {
            max = left;
        }
        if(right < size && array[right] > array[max]) {
            max = right;
        }
        if(max != index) {
            Swap.swap(array, max, index);
            heapifyRecursive(array, max, size);
        }
    }

    public static void main(String[] args) {
        int[] A = {16, 3, 1, 8, 5, 9, 10, 11, 3, 10, 20};
        HeapSort.sort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}