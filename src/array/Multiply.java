package array;

import utils.Print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class Multiply {

    static List<Integer> multiply(List<Integer> input1, List<Integer> input2) {
        if(input1 == null || input1.size() == 0 || input2 == null || input2.size() == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>(Collections.nCopies(input1.size() + input2.size(), 0));
        Collections.fill(res, 0);
        int sign = input1.get(0) * input2.get(0) >= 0 ? 1 : -1;
        for(int i = input1.size() - 1; i >= 0; i--) {
            for(int j = input2.size() - 1; j >= 0; j--) {
                res.set(i + j + 1, input1.get(i) * input2.get(j) + res.get(i + j + 1));
                res.set(i + j, res.get(i + j) + res.get(i + j + 1) / 10);
                res.set(i + j + 1, res.get(i + j + 1) % 10);
            }
        }
        int firstIdx = 0;
        while(firstIdx < res.size() && res.get(firstIdx) == 0) {
            firstIdx++;
        }
        res = res.subList(firstIdx, res.size());
        res.set(0, res.get(0) * sign);
        return res;
    }

    public static void main(String[] args) {
        List<Integer> input1 = Arrays.asList(7, 8, 9);
        List<Integer> input2 = Arrays.asList(1, 2, 3, 4,5,6,7, 8, 9, 9);
        List<Integer> res = multiply(input1, input2);
        Print.print(res);
    }
}
