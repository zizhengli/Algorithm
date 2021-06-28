package test;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by zizhengli on 9/11/18.
 */
public class QueueTest {

    private int[] queue;
    private int numElement;
    private int head;
    private int tail;

    QueueTest(int size) {
        this.queue = new int[size];
        this.numElement = 0;
        head = 0;
        tail = 0;
    }

    void enqueue(int value) {
        if(numElement == this.queue.length) {
            resize();
        }
        this.queue[tail] = value;
        tail = (tail + 1) % this.queue.length;
        numElement++;
    }

    int dequeue() throws Exception {
        if(numElement == 0) {
            throw new Exception("Empty queue");
        }
        int val = this.queue[head];
        head = (head + 1) % this.queue.length;
        numElement--;
        return val;
    }

    int getSize() {
        return numElement;
    }

    void resize() {
        Collections.rotate(Arrays.asList(this.queue), -head);
        head = 0;
        tail = numElement;
        this.queue = Arrays.copyOf(this.queue, numElement * 2);
    }

    public static void main(String[] args) {

        try {
            QueueTest queue = new QueueTest(5);
            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
            queue.enqueue(4);
            queue.enqueue(5);
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.getSize());
            queue.enqueue(6);
            queue.enqueue(7);
            System.out.println(queue.getSize());
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
