package vk;

public class Solution11_11_1 {
	
	private int[] queue;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public Solution11_11_1(int k) {
        capacity = k;
        queue = new int[capacity];
        head = -1;
        tail = -1;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            head = 0; // Set head to the first position if the queue is initially empty
        }
        tail = (tail + 1) % capacity; // Move tail to the next position circularly
        queue[tail] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        if (head == tail) { // Only one element in the queue
            head = -1;
            tail = -1;
        } else {
            head = (head + 1) % capacity; // Move head to the next position circularly
        }
        size--;
        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : queue[head];
    }

    public int Rear() {
        return isEmpty() ? -1 : queue[tail];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

}
