package primitive;

/**
 * Created by lim20 on 6/26/2018.
 */
public class BitWise {

    static int clearLastOne(int x) {
        return x & (x - 1);
    }

    static int extractLastOne(int x) {
        return x & ~(x- 1);
    }

    static int swapBits(int x, int i, int j) {
        if(((x >>> i) & 1) != ((x >>> j) & 1)) {
            int bitmask = (1 << i) | (1 << j);
            x ^= bitmask; // Use flip XOR
        }
        return x;
    }

    public static void main(String[] args) {

        int x = 13222312;
        System.out.println("x = " + clearLastOne(x));
        System.out.println("x = " + clearLastOne(x));



        System.out.println(Long.toBinaryString(1L << 5 | 1L << 2));

    }
}
