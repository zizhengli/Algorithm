package sorting;

/**
 *
 */
public class ShellSort {

    static void sort(int[] A) {
        if(A == null || A.length == 0) {
            return;
        }
        int h = 0;
        while(h < A.length - h - 1) {
            h = 3 * h + 1;
        }
        while(h >= 1) {
            for(int i = h; i >= 0; i = i - h) {
                int j = i - h;
                int get = A[i];
                while(j >= 0 && A[j] > get) {
                    A[j + h] = A[j];
                    j = j - h;
                }
                A[j + h] = get;
            }
            h = (h - 1) /3;
        }
    }

    public static void main(String[] args) {
        int[] A = {2, 3, 1, 8, 5, 9, 10, 2, 20};
        ShellSort.sort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
