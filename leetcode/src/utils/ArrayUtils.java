package utils;

/**
 * Created by zizhengli on 1/31/17.
 */
public class ArrayUtils {

    public static String[] generateRandomArray(int len, int max) {
        String[] res = new String[len];
        for (int i = 0; i != len; i++) {
            res[i] = String.valueOf((int) (Math.random() * (max + 1)));
        }
        return res;
    }
}
