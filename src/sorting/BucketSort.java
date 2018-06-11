package sorting;

/**
 *
 */
public class BucketSort {

    private static int[] bucket = new int[5]; // sort numbers from 0-49

    static void sort(int[] A) {
        if(A == null || A.length == 0) {
            return;
        }
        for(int i = 0; i < A.length; i++) {
            bucket[A[i] / 10]++;
        }
        for(int i = 1; i < bucket.length; i++) {
            bucket[i] = bucket[i - 1] + bucket[i];
        }
        int[] temp = new int[A.length];
        for(int i = 0; i < A.length; i++) {
            temp[--bucket[A[i] / 10]] = A[i];
        }
        for(int i = 0; i < A.length; i++) {
            A[i] = temp[i];
        }
        // Using insertion sort
        for(int i = 1; i < A.length; i++) {
            int current = A[i];
            int prev = i - 1;
            while(prev >= 0 && A[prev] > current) {
                A[prev + 1] = A[prev];
                prev--;
            }
            A[prev + 1] = current;
        }
    }

    public static void main(String[] args) {
        int[] A = {16, 3, 1, 8, 5, 9, 10, 11, 3, 11, 20, 49, 23, 7};
        BucketSort.sort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }

}
