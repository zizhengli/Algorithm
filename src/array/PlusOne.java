package array;

import utils.Print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * \
 */
public class PlusOne {

    static List<Integer> plusOne(List<Integer> input) {
        if(input == null || input.size() == 0) {
            return new ArrayList<>();
        }
        int index = input.size() - 1;
        input.set(index, input.get(index) + 1);

        while(index > 0 && input.get(index) == 10) {
            input.set(index, 0);
            input.set(index - 1, input.get(index - 1) + 1);
            index--;
        }

        if(input.get(0) == 10) {
            input.set(0, 0);
            input.add(0, 1);
        }
        return input;
    }

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        input.add(9);
        input.add(9);
        input.add(9);
        List<Integer> res = plusOne(input);
        Print.print(res);
    }
}
