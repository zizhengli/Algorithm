package sorting;

/**
 *
 */
public class MergeSort {

    static void sort(int[] A) {
        if(A == null || A.length == 0) {
            return;
        }
        int[] temp = new int[A.length];
        mergeSort(0, A.length - 1, A, temp);
    }

    private static void mergeSort(int start, int end, int[] array, int[] temp) {
        if(start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(start, mid, array, temp);
        mergeSort(mid + 1, end, array, temp);
        merge(start, mid, end, array, temp);
    }

    private static void merge(int start, int mid, int end, int[] array, int[] temp) {
        int index = start;
        int left = start;
        int right = mid + 1;
        while(left <= mid && right <= end) {
            if(array[left] < array[right]) {
                temp[index++] = array[left++];
            } else {
                temp[index++] = array[right++];
            }
        }
        while(left <= mid) {
            temp[index++] = array[left++];
        }
        while(right <= end) {
            temp[index++] = array[right++];
        }
        for(int i = start; i <= end; i++) {
            array[i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] A = {2, 3, 1, 8, 5, 9, 10, 11, 3, 10, 1};
        MergeSort.sort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
