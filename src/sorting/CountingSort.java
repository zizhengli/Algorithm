package sorting;

/**
 *
 */
public class CountingSort {

    private static int[] C = new int[100]; // sort number from 0 to 99

    static void sort(int[] A) {
        if(A == null || A.length == 0) {
            return;
        }
        for (int i = 0; i < C.length; i++) {
            C[i] = 0;
        }
        // Array C it used to sort the numbers in array A by using the index of array
        for(int i = 0; i < A.length; i++) {
            C[A[i]]++;
        }
        // Count the duplicate numbers in C array
        for(int i = 1; i < C.length; i++) {
            C[i] = C[i - 1] + C[i];
        }
        int[] B = new int[A.length];
        // At this moment, the new position of each number in array A is sorted in array C,
        // because there might be duplicate number, so we should iterate array C in reverse order.
        for (int i = A.length - 1; i >= 0; i--) {
            B[--C[A[i]]] = A[i];
        }

        for (int i = 0; i < A.length; i++) {
            A[i] = B[i];
        }
    }

    public static void main(String[] args) {
        int[] A = {16, 3, 1, 8, 5, 9, 10, 11, 3, 11, 20, 54, 23, 7};
        CountingSort.sort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
