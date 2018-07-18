package array;

import utils.Print;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BuySellStock {

    static int buySellOneTime(int[] array) {
        if(array == null || array.length == 0) {
            return 0;
        }
        int minValue = array[0];
        int maxProfit = 0;

        for(int i = 1; i < array.length; i++) {
            maxProfit = Math.max(maxProfit, array[i] - minValue);
            minValue = Math.min(minValue, array[i]);
        }
        return maxProfit;
    }

    static int buySellTwice(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        List<Integer> firstBuySellProfit = new ArrayList();
        int minPrice = prices[0];
        int maxProfit = 0;

        for(int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            firstBuySellProfit.add(maxProfit);
        }
        int maxPrice = Integer.MIN_VALUE;
        int finalMaxProfit = 0;
        Print.print(firstBuySellProfit);
        finalMaxProfit = Math.max(finalMaxProfit, firstBuySellProfit.get(firstBuySellProfit.size() - 1));
        for(int i = prices.length - 1; i > 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            finalMaxProfit = Math.max(finalMaxProfit, firstBuySellProfit.get(i - 1) + maxPrice - prices[i]);
        }
        return finalMaxProfit;
    }

    public static void main(String[] args) {
        int[] array = {3,3,5,0,0,3,1,4};
        System.out.println(buySellTwice(array));

    }
}
