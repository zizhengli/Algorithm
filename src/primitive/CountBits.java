package primitive;

/**
 *
 */
public class CountBits {

    static int countBits(int x) {
        short numBits = 0;
        while(x != 0) {
            numBits += (x & 1);
            x >>>= 1;
            System.out.println(Integer.toBinaryString(x));
        }
        return numBits;
    }

    public static void main(String[] args) {

        int number = 98;
        System.out.println(Integer.toBinaryString(number));
        System.out.println("Number of bit: " + CountBits.countBits(number));

    }
}
