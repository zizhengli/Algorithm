package sorting;

import utils.Swap;

/**
 * HeapSort algorithm :
 * The idea is that if we try to increaseSort array in increasing order, we need to do following steps :
 * 1. Implement a max heapify function, and max heapify first half of the array to make the array as heap
 * 2. Set up an index pointing to the last element, Swap it with last element in the array to get max number,
 *     heapify the array without last element. (The last element is max number so far)
 * 3. Move the index to the one before the last one, and repeat step 2.
 */
public class HeapSort {

    static void increaseSort(int[] A) {
        if(A == null || A.length == 0) {
            return;
        }
        for (int i = A.length / 2 - 1; i >= 0; i--) {
            maxHeapify(A, i, A.length);
        }
        for(int i = A.length - 1; i >= 0; i--) {
            Swap.swap(A, i, 0);
            maxHeapify(A, 0, i);
        }
    }

    private static void maxHeapify(int[] array, int index, int size) {
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

    static void decreaseSort(int[] A) {
        if(A == null || A.length == 0) {
            return;
        }
        for(int index = A.length / 2 - 1; index >= 0; index--) {
            minHeapify(A, index, A.length);
        }
        for(int index = A.length - 1; index >= 0; index--) {
            Swap.swap(A, index, 0);
            minHeapify(A, 0, index);
        }
    }

    private static void minHeapify(int[] array, int index, int size) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        while(index >= 0) {
            left = 2 * index + 1;
            right = 2 * index + 2;
            if(left < size && array[smallest] > array[left]) {
                smallest = left;
            }
            if(right < size && array[smallest] > array[right]) {
                smallest = right;
            }
            if(smallest != index) {
                Swap.swap(array, smallest, index);
                index = smallest;
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
        HeapSort.increaseSort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
        HeapSort.decreaseSort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}