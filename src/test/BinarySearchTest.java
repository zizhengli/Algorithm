package test;

/**
 * Created by zizhengli on 9/14/18.
 */
public class BinarySearchTest {

    static int getFirstOccurrence(int[] array, int k) {
        if(array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;
        int result = -1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(array[mid] > k) {
                right = mid - 1;
            } else if (array[mid] == k) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    static int getFirstOccurrenceGreater(int[] array, int k) {
        if(array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        int result = -1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(array[mid] <= k) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result + 1 >= array.length ? -1 : result + 1;
    }

    static int searchEntryEqualsToItsIndex(int[] array) {
        if(array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(mid == array[mid]) {
                return mid;
            } else if(mid > array[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    static int searchSmallest(int[] array) {
        if(array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;

        while(left < right) {
            int mid = left + (right - left) / 2;
            if(array[mid] < array[left]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    static boolean matrixSearch(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0) {
            return false;
        }
        int row = 0;
        int col = matrix[0].length - 1;

        while(row < matrix.length && col > 0) {
            System.out.println(row + " " + col);
            if(matrix[row][col] == k) {
                return true;
            } else if(matrix[row][col] < k) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }


    public static void main(String[] args) {

        int[] array = {-14, -10, 2, 108, 108, 243, 285, 285, 285, 401};

        System.out.println(getFirstOccurrence(array, 0));
        System.out.println(getFirstOccurrenceGreater(array, 108));

        int[] search = {-2};
        System.out.println(searchEntryEqualsToItsIndex(search));

        int[][] matrix = {new int[] {-1, 2, 4, 4, 6},
                          new int[] {1, 5, 5, 9, 21},
                          new int[] {3, 6, 6, 9, 22},
                          new int[] {3, 6, 8, 10, 24},
                          new int[] {6, 8, 9, 12, 25},
                        new int[] {8, 10, 12, 13, 40},
        };

        System.out.println(matrixSearch(matrix, 5));
    }
}
