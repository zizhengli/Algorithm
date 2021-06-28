package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class lc273_Integer_To_EnglishWords {

    private static Map<Integer, String> map = new HashMap();
    static {
        map.put(0, "Zero");
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
    }

    public String numberToWords(int num) {
        if(num < 1_000_000_000) {
            return belowBillion(num);
        } else if (num % 1_000_000_000 == 0) {
            return map.get(num / 1_000_000_000) + " Billion";
        } else {
            return map.get(num / 1_000_000_000) + " Billion " + belowBillion(num % 1_000_000_000);
        }
    }

    private String belowHundred(int x) {
        if(map.containsKey(x)) {
            return map.get(x);
        }
        return map.get(x - x % 10) + " " + map.get(x % 10);
    }

    private String belowThousand(int x) {
        if(x < 100) {
            return belowHundred(x);
        }
        int result = x / 100;
        if(x % 100 == 0) {
            return map.get(result) + " Hundred";
        } else {
            return map.get(result) + " Hundred " + belowHundred(x % 100);
        }
    }

    private String belowMillion(int x) {
        if(x < 1000) {
            return belowThousand(x);
        }
        int result = x / 1000;
        if(x % 1000 == 0) {
            return belowThousand(result) + " Thousand";
        } else {
            return belowThousand(result) + " Thousand " + belowThousand(x % 1000);
        }
    }

    private String belowBillion(int x) {
        if (x < 1000_000) {
            return belowMillion(x);
        }
        int result = x / 1000_000;
        if (x % 1000_000 == 0) {
            return belowThousand(result) + " Million";
        } else {
            return belowThousand(result) + " Million " + belowMillion(x % 1000_000);
        }
    }
}
