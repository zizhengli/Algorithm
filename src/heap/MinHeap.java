package heap;

import jdk.nashorn.internal.runtime.ECMAException;

/**
 * Created by lim20 on 10/5/2018.
 */
public class MinHeap {

    private int[] array;
    private int capacity;
    private int size;

    public MinHeap(int size) {
        this.array = new int[size];
        this.capacity = size;
        this.size = 0;
    }

    public int extractMin() throws Exception {
        if(this.size == 0) {
            throw new Exception("Empty");
        } else if(this.size == 1) {
            this.size--;
            return this.array[0];
        }
        int result = this.array[0];
        this.array[0] = this.array[size - 1];
        this.size--;
        shiftDown(0);
        return result;
    }

    public void insert(int value) throws Exception {
        if(this.size == capacity) {
            throw new Exception("out of spalce");
        }
        this.array[size++] = value;
        int index = size - 1;
        shiftUp(index, value);
    }

    public int getSize() {
        return this.size;
    }

    private void shiftUp(int index, int value) {
        while(index > 0) {
            int parent = parentIdx(index);
            if(this.array[parent] < this.array[index]) {
                break;
            }
            swap(parent, index);
            index = parentIdx(index);
        }
        this.array[index] = value;
    }

    private void shiftDown(int index) {
        int half = this.size / 2;
        while(index < half) {
            int left = leftChildIdx(index);
            int right = rightChildIdx(index);
            int smallest = index;

            if(this.array[left] < this.array[smallest]) {
                smallest = left;
            }

            if(this.array[right] < this.array[smallest]) {
                smallest = right;
            }

            if(smallest != index) {
                swap(smallest, index);
            } else {
                break;
            }
        }
    }

    private void minHeapify(int i) {

        int left = leftChildIdx(i);
        int right = rightChildIdx(i);
        int smallest = i;

        if(left < this.size && this.array[left] < this.array[smallest]) {
            smallest = left;
        }

        if(right < this.size && this.array[right] < this.array[smallest]) {
            smallest = right;
        }

        if(smallest != i) {
            swap(smallest, i);
            minHeapify(smallest);
        }
    }

    private int parentIdx(int i) {
        return (i - 1) / 2;
    }

    private int leftChildIdx(int i) {
        return i * 2 + 1;
    }

    private int rightChildIdx(int i) {
        return i * 2 + 2;
    }

    private void swap(int a, int b) {
        int tmp = this.array[a];
        this.array[a] = this.array[b];
        this.array[b] = tmp;
    }

    public static void main(String[] args) throws Exception {
        MinHeap heap = new MinHeap(10);

        heap.insert(2);
        heap.insert(3);
        heap.insert(1);
        heap.insert(10);
        heap.insert(5);
        heap.insert(8);
        heap.insert(4);

        while(heap.size > 0) {
            System.out.println(heap.extractMin());
        }
    }
}
