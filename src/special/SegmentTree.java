package special;

/**
 * https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 * The link to explain why the size of segment tree is not the size of input array and how to calculate the size
 * https://stackoverflow.com/questions/28470692/how-is-the-memory-of-the-array-of-segment-tree-2-2-ceillogn-1
 */
public class SegmentTree {

     int[] segmentSum;
     int[] array;

    public SegmentTree(int[] array) {
        this.array = array;
        int height = (int) (Math.ceil(Math.log(this.array.length) / Math.log(2))); // Get the height of segment tree
        int max_size = 2 * (int) Math.pow(2, height) - 1;
        this.segmentSum = new int[max_size];
        build(this.array, 0, 0, array.length - 1);
    }

    private void build(int[] array, int currentIdx, int start, int end) {
        if(start == end) {
            this.segmentSum[currentIdx] = array[start];
            return;
        }
        int mid = start + (end - start) / 2;
        build(array, currentIdx * 2 + 1, start, mid);
        build(array, currentIdx * 2 + 2, mid + 1, end);
        this.segmentSum[currentIdx] = this.segmentSum[currentIdx * 2 + 1] + this.segmentSum[currentIdx * 2 + 2];
    }

    public int getRangeSum(int start, int end) {
        return query(0, start, end, 0, this.array.length - 1);
    }


    int query(int rangeIdx, int qStart, int qEnd, int start, int end) {
        if(qStart > end || qEnd < start) { // If the query range is completely out of segment tree range
            return 0;
        }
        if(qStart <= start && qEnd >= end) { // If the segment tree range is completely inside query range
            return this.segmentSum[rangeIdx];
        }
        int mid = start + (end - start) / 2;

        if(qStart > mid) {
            /*                      qStart        qEnd
            *   start          mid         end
            * */
            return query(2 * rangeIdx + 2, qStart, qEnd, mid + 1, end);
        } else if (qEnd <= mid) {
            /* qStart            qEnd
            *        start        mid         end
            * */
            return query(2 * rangeIdx + 1, qStart, qEnd, start, mid);
        }
        /*               qStart        qEnd
        *        start          mid             end
        * */
        return query(rangeIdx * 2 + 1, qStart, mid, start, mid)
                + query(rangeIdx * 2 + 2, mid + 1, qEnd, mid + 1, end);
    }

    void update(int index, int newValue) {
        updateUtil(0, 0, this.array.length - 1, index, newValue);
    }

    private void updateUtil(int rangeIdx, int start, int end, int index, int value) {
        if(start == end) {
            this.segmentSum[rangeIdx] = value;
            return;
        }
        int mid = start + (end - start) / 2;
        // Update the child ranges
        if(index > mid) {
            updateUtil(rangeIdx * 2 + 2, mid + 1, end, index, value);
        } else if(index <= mid) {
            updateUtil(rangeIdx * 2 + 1, start, mid, index, value);
        }
        this.segmentSum[rangeIdx] = this.segmentSum[2 * rangeIdx + 1] + this.segmentSum[2 * rangeIdx + 2];
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 5, 7, 9, 11};
        SegmentTree segmentTree = new SegmentTree(array);
        System.out.println(segmentTree.getRangeSum(3, 10));
        segmentTree.update(3, 10);
        System.out.println(segmentTree.getRangeSum(3, 10));
    }
}
