package leetcode;

/**
 *
 */
public class lc10_RegularExpression {

    static boolean isMatch(String s, String p) {
        if(s == null || p == null) {
            return false;
        }
        return matchHelper(s, 0, p, 0);
    }

    private static boolean matchHelper(String s, int indexS, String p, int indexP) {
        if(indexP == p.length()) {
            return indexS == s.length();
        }
        /*
        * s: abc
        * p: a.c
        *    abc
        * */
        if (indexP + 1 == p.length() || p.charAt(indexP + 1) != '*') {
            return indexS != s.length() && (p.charAt(indexP) == s.charAt(indexS) || p.charAt(indexP) == '.')
                    && matchHelper(s, indexS + 1, p, indexP + 1);
        }
        /**
         * s: abc
         * p: a*b
         *
         * s: aaaabc
         * p: a*bc
         *
         * */
        while(indexS != s.length() && (p.charAt(indexP) == '.' || p.charAt(indexP) == s.charAt(indexS))) {
            if(matchHelper(s, indexS, p, indexP + 2)) {
                return true;
            }
            indexS++;
        }
        /*
        * s: bc
        * p: a*bc a has 0 occurrence
        * */
        return matchHelper(s, indexS, p, indexP + 2);
    }

    public static void main(String[] args) {

        String s = "scar";
        String p = "osc.r";

        System.out.println(isMatch(s, p));
    }
}
