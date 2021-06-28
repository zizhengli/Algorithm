package leetcode;

import utils.Print;

/**
 *
 */
public class lc44_WildcardMatching {

    static boolean isMatch(String s, String p) {
        if(s == null || p == null) {
            return false;
        }
        return matchHelper(s, 0, p, 0);
    }

    /**
     * Brute force way
     */
    private static boolean matchHelper(String s, int indexS, String p, int indexP) {
        //System.out.println(indexS + " " + indexP);
        if(indexP == p.length()) {
            return indexS == s.length();
        }
        if(indexS == s.length()) {
            while(indexP < p.length() && p.charAt(indexP) == '*') {
                indexP++;
            }
            return indexP == p.length();
        }
        for(int i = indexP; i < p.length(); i++) {
            if(s.charAt(indexS) == p.charAt(i) || p.charAt(i) == '?') {
                return matchHelper(s, indexS + 1, p, i + 1);
            } else if(s.charAt(indexS) != p.charAt(indexP) && p.charAt(i) != '*') {
                return matchHelper(s, indexS, p, indexP + 1);
            } else if(p.charAt(i) == '*') {
                int offset = 0;
                while(indexS + offset < s.length()) {
                    if(matchHelper(s, indexS + offset, p, i + 1)) {
                        return true;
                    }
                    offset++;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /*
    * DP solution
    * */
    private static boolean isMatch2(String s, String p) {
        if(s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        initializeDP(dp, p);

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if(p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        Print.printMatrix(dp);
        return dp[p.length()][s.length()];
    }

    private static void initializeDP(boolean[][] dp, String p) {
        if(dp == null || dp.length == 0) {
            return;
        }
        dp[0][0] = true;
        // If p is empty string
        for(int i = 1; i < dp[0].length; i++) {
            dp[0][i] = false;
        }

        // If s is empty string
        for(int i = 1; i < dp.length; i++) {
            dp[i][0] = p.charAt(i - 1) == '*' ? dp[i - 1][0] : false;
        }
    }

    public static void main(String[] args) {

        String s = "adceb";
        String p = "*a*b";
        System.out.println(isMatch2(s, p));
    }
}
