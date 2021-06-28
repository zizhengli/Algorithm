package primitive;

/**
 *
 */
public class CheckParity {

    final private static int[] cache = new int[65536];
    final private static int W0RD_SIZE = 16;
    final private static int BIT_MASK = 0xFFFF;

    static short parity(long x) {
        short res = 0;
        while (x != 0) {
            res ^= 1;
            x = x & (x - 1); // Remove the last 1 bit from right, minus 1 to reach directly the last 1s from left then we bitwise-AND with itself
        }
        return res;
    }

    static short parity2(long x) {
        return (short) (parityHelper((int)(x >>> (3 * W0RD_SIZE)))
                ^ parityHelper((int)(x >>> (2 * W0RD_SIZE)))
                ^ parityHelper((int)(x >>> W0RD_SIZE))
                ^ parityHelper((int)(x & BIT_MASK)));
    }

    private static void initialize() {
        for(int i = 0; i < cache.length; i++) {
            cache[i] = -1;
        }
    }

    private static int parityHelper(int x) {
        if(cache[x] != -1) {
            return cache[x];
        }
        int res = 0;
        while(x != 0) {
            res ^= 1;
            x &= (x - 1);
        }
        cache[x] = res;
        return cache[x];
    }

    public static void main(String[] args) {

        long number = 2112121212;
        System.out.println(Long.toBinaryString(number));
        System.out.println("Parity: " + CheckParity.parity(number));

        initialize();
        System.out.println("Parity 2: " + CheckParity.parity2(number));

    }
}
