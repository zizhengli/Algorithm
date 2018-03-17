package stack_queue;

/**
 * Created by zizhengli on 1/30/17.
 */
public class ImplementQueueWithArray {

    private int[] array;
    private int in;
    private int out;
    private int size;
    public ImplementQueueWithArray(int size) {
        this.array = new int[size];
        this.in = 0;
        this.out = 0;
        this.size = 0;
    }

    public void add(int val) throws Exception {
        if(size == array.length) {
            throw new Exception("Full");
        }
        size++;
        array[in++] = val;
        if(in >= array.length) {
            in = in % array.length;
        }
    }

    public int poll() throws Exception {
        if(size == 0) {
            throw new Exception("Empty");
        }
        size--;
        int temp = array[out++];
        if(out >= array.length) {
            out = out % array.length;
        }
        return temp;
    }

    public int peek() throws Exception {
        if(size == 0) {
            throw new Exception("Full");
        }
        return array[out];
    }

    public static void main(String[] args) {

        ImplementQueueWithArray queue = new ImplementQueueWithArray(3);
        try {
            queue.add(1);
            queue.add(2);
            queue.add(3);
            System.out.println(queue.poll());
            System.out.println(queue.poll());
            System.out.println(queue.poll());
            queue.add(4);
            System.out.println(queue.poll());
            queue.add(5);
            queue.add(6);
            System.out.println(queue.poll());
            System.out.println(queue.poll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
