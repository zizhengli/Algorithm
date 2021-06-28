package string;

public class KMP {

    static int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0; // counter of length of prefix
        while (i < next.length) {
            if (match[i - 1] == match[cn]) {
                // Length increase by 1 based on the value of previous char
                next[i++] = ++cn;
            } else if (cn > 0) {
                // If not equals, it should go back to the
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }


    public static void main(String[] args) {

        char[] match = "abasabatabasabakz".toCharArray();
        int[] next = getNextArray(match);
    }
}
