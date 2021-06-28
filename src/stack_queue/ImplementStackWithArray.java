package stack_queue;

/**
 * Created by zizhengli on 1/30/17.
 */
public class ImplementStackWithArray {

    private int[] array;
    private int index;
    public ImplementStackWithArray(int size) {
        this.array = new int[size];
        this.index = 0;
    }

    public void push(int val) throws Exception {
        if(index == array.length - 1) {
            throw new Exception("Full");
        }
        array[index++] = val;
    }

    public int pop() throws Exception {
        if(index < 0) {
            throw new Exception("Empty");
        }
        return array[--index];
    }

    public int top() throws Exception {
        if(index < 0) {
            throw new Exception("Empty");
        }
        return array[index - 1];
    }

}
