package array;

import utils.Print;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class GeneratePrimes {

    static List<Integer> getPrimes(int n) {
        List<Integer> res = new ArrayList<>();
        if(n < 0) {
            return res;
        }
        List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n + 1, true));
        isPrime.set(0, false);
        isPrime.set(1, false);
        for(int i = 2; i <= n; i++) {
            if(isPrime.get(i)) {
                res.add(i);
                for(int j = i; j <= n; j = j + i) {
                    isPrime.set(j, false);;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> res = getPrimes(100);
        Print.print(res);
    }
}
