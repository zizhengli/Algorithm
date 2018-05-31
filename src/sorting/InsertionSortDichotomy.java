package sorting;

/**
 * Use binary search to find the position to insert the current number and shift all number from the current position to right
 */
public class InsertionSortDichotomy {

    static void sort(int[] A) {
        if(A == null || A.length == 0) {
            return;
        }
        for(int i = 1; i < A.length; i++) {
            int get = A[i];
            int left = 0;
            int right = i - 1;
            // Using a binary search to find the first element equals or greater than the current number among sorted numbers
            while(left <= right) {
                int mid = left + (right - left) / 2;
                if(A[mid] < get) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            for(int j = i - 1; j >= left; j--) {
                A[j + 1] = A[j];
            }
            A[left] = get;
        }
    }

    public static void main(String[] args) {
        int[] A = {2, 3, 1, 8, 5, 9, 10, 2, 20};
        InsertionSortDichotomy.sort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
