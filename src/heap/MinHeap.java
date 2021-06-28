package heap;

import utils.Swap;

/**
 * Created by lim20 on 10/5/2018.
 */
public class MinHeap {

    private int[] array;
    private int capacity;
    private int elmIndex;

    public MinHeap(int capacity) {
        this.array = new int[capacity];
        this.capacity = capacity;
        this.elmIndex = -1;
    }

    public void insert(int value) throws Exception {
        if(this.elmIndex >= capacity) {
            throw new Exception("out of spalce");
        }
        this.array[++elmIndex] = value;
        int index = elmIndex;
        // Shift up
        while(index >= 0 && array[index] < array[index / 2]) {
            swap(index, index / 2);
            index = index / 2;
        }
    }

    public int extractMin() throws Exception {
        if(this.elmIndex < 0) {
            throw new Exception("Empty");
        }
        int result = array[0];
        this.array[0] = this.array[this.elmIndex--];
        // Shift down
        heapify(this.array, elmIndex, 0);
        return result;
    }

    private void heapify(int[] array, int size, int index) {
        int half = size / 2;
        int left = 0;
        int right = 0;
        while(index < half) {
            left = index * 2 + 1;
            right = index * 2 + 2;
            int smallest = index;
            if(array[left] < array[smallest]) {
                smallest = left;
            }
            if(array[right] < array[smallest]) {
                smallest = right;
            }
            if(smallest != index) {
                swap(smallest, index);
            } else {
                break;
            }
        }
    }

    public static void sort(int[] array) {
        int size = array.length;
        for(int i = size / 2 - 1; i >= 0; i--) {
            maxHeapify(array, size, i);
        }

        int last = array.length - 1;
        while(last >= 0) {
            Swap.swap(array, last, 0);
            last--;
            maxHeapify(array, last, 0);
        }
    }

    private static void maxHeapify(int[] array, int last, int index) {
        int left = 0;
        int right = 0;
        int largest = index;
        while(true) {
            left = index * 2 + 1;
            right = index * 2 + 2;
            largest = index;
            if(left < last && array[largest] < array[left]) {
                largest = left;
            }
            //System.out.println(right);
            if(right < last && array[largest] < array[right]) {
                largest = right;
            }
            if(largest != index) {
                Swap.swap(array, largest, index);
                index = largest;
            } else {
                break;
            }
        }
    }

    public int getCount() {
        return this.elmIndex;
    }

    /*private void shiftUp(int index, int value) {
        while(index > 0 && this.array[index] > this.array[parentIdx(index)]) {
            *//*int parent = parentIdx(index);
            if(this.array[parent] < this.array[index]) {
                break;
            }*//*
            swap(parentIdx(index), index);
            index = parentIdx(index);
        }
        this.array[index] = value;
    }*/

    /*private void shiftDown(int index) {
        int half = this.elmIndex / 2;
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
    }*/

    private void minHeapify(int i) {

        int left = leftChildIdx(i);
        int right = rightChildIdx(i);
        int smallest = i;

        if(left < this.elmIndex && this.array[left] < this.array[smallest]) {
            smallest = left;
        }

        if(right < this.elmIndex && this.array[right] < this.array[smallest]) {
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
        //System.out.println(heap.extractMin());
        heap.insert(5);
        heap.insert(8);
        heap.insert(4);
        heap.insert(0);
        //System.out.println(heap.extractMin());
        heap.insert(11);
        //System.out.println(heap.extractMin());


        int[] array = {2, 3, 1, 10, 15, 8, 4, 0};
        heap.sort(array);

        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
