package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zizhengli on 9/19/18.
 */
public class RecursionTest {

    static List<List<Integer>> allSubSetWithSize(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        allSubSetHelper(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private static void allSubSetHelper(int n, int k, int offset, List<Integer> partial, List<List<Integer>> result) {
        if(partial.size() == k) {
            result.add(new ArrayList<>(partial));
            return;
        }
        int remainingNum = k - partial.size();

        System.out.println("Test: " + offset + " " + remainingNum);

        for(int i = offset; i <= n && remainingNum <= n - i + 1; i++) {
            partial.add(i);
            allSubSetHelper(n, k, i + 1, partial, result);
            partial.remove(partial.size() - 1);
        }
    }

    public static void main(String[] args) {

        List<List<Integer>> result = allSubSetWithSize(5, 3);
        for(List<Integer> list : result) {
            for(Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
