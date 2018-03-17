package src.leetcode;

/**
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * */
public class lc85_MaximalRectangle {

    public static int maximalRectangle(int[][] matrix) {

        int[] helper = new int[matrix[0].length];
        int maxRectangle = 0;

        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
                helper[col] += matrix[row][col];
            }
            // Get max width by row
            for(int col = 0; col < matrix[0].length; col++) {
                maxRectangle = Math.max(maxRectangle, getMaxWidth(helper, col) * helper[col]);
                //System.out.println(row + " " + col + " " + maxRectangle);
            }
        }
        return maxRectangle;
    }

    private static int getMaxWidth(int[] helper, int start) {
        if(helper[start] != 0) {
            int left = start;
            int right = start;

            while(left - 1 >= 0 && helper[left - 1] >= helper[start]) {
                left--;
            }

            while(right + 1 <= helper.length - 1 && helper[right + 1] >= helper[start]) {
                right++;
            }
            return right - left + 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {

        int[][] test = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        };
        System.out.println(lc85_MaximalRectangle.maximalRectangle(test));

    }
}
