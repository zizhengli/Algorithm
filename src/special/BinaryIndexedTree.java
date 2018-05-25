package special;

/**
 * https://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
 * Good Explanation:
 * https://www.hackerearth.com/practice/data-structures/advanced-data-structures/fenwick-binary-indexed-trees/tutorial/
 *
 *
 * x & (-x) gives the last set bit in a number x
 */
public class BinaryIndexedTree {

    final static int MAX = 1000;
    static int BITree[] = new int[MAX];

    public static int getSum(int index) {
        int sum = 0; // Iniialize result
        // index in BITree[] is 1 more than
        // the index in arr[]
        index = index + 1;

        // Traverse ancestors of BITree[index]
        while(index > 0) {
            // Add current element of BITree
            // to sum
            sum += BITree[index];

            System.out.println("index : " + index);

            // Move index to parent node in
            // getSum View
            index -= index & (-index);
        }
        return sum;
    }

    public static void updateBIT(int n, int index, int val) {
        // index in BITree[] is 1 more than
        // the index in arr[]
        index = index + 1;

        // Traverse all ancestors and add 'val'
        while(index <= n) {
            // Add 'val' to current node of BIT Tree
            BITree[index] += val;

            // Update index to that of parent
            // in update View
            index += index & (-index);
        }
    }

    public void constructBITree(int arr[], int n) {
        // Initialize BITree[] as 0
        for(int i = 1; i <= n; i++)
            BITree[i] = 0;

        // Store the actual values in BITree[]
        // using update()
        for(int i = 0; i < n; i++)
            updateBIT(n, i, arr[i]);
    }

    public static void main(String args[]) {
        int freq[] = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = freq.length;
        BinaryIndexedTree tree = new BinaryIndexedTree();

        // Build fenwick tree from given array
        tree.constructBITree(freq, n);
        /*for(int i = 0; i < BITree.length; i++) {
            System.out.print(BITree[i] + " ");
        }*/

        System.out.println("Sum of elements in arr[0..5]" + " is = "+ tree.getSum(5));

        // Let use test the update operation
        //freq[3] += 6;

        // Update BIT for above change in arr[]
        //updateBIT(n, 3, 6);

        // Find sum after the value is updated
        System.out.println("Sum of elements in arr[0..5]" + " after update is = " + tree.getSum(5));
    }
}
