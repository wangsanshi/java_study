package com.wang.study.data_structures;

/**
 * 队列的实现，先进先出
 */
@SuppressWarnings("all")
public class Queue {
    private int[] queueArray;
    private int maxSize;
    /**
     * 队列中的元素个数
     */
    private int itemCount;

    public Queue(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Queue maxSize must > 0.");
        }
        this.maxSize = maxSize;
        this.itemCount = 0;
        queueArray = new int[maxSize];
    }

    /**
     * 队列是否满了
     */
    public boolean isFull() {
        return itemCount == maxSize;
    }

    /**
     * 队列是否为空
     */
    public boolean isEmpty() {
        return itemCount == 0;
    }

    /**
     * 向队列中添加元素，即从队尾添加
     */
    public void insert(int element) {
        if (isFull()) {
            throw new IllegalArgumentException("Queue is full , can not insert element.");
        }

        queueArray[itemCount] = element;
        itemCount++;
    }

    /**
     * 移除队列中的一个元素，即从队头移除
     */
    public int remove() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty , can not remove element.");
        }

        int result = queueArray[0];
        System.arraycopy(queueArray, 1, queueArray, 0, itemCount - 1);
        itemCount--;
        return result;
    }

    /**
     * 查看队头元素
     */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Quwuw is empty , can not peek element.");
        }

        return queueArray[0];
    }

    /**
     * 返回队列中元素个数
     */
    public int size() {
        return itemCount;
    }

    public void print() {
        System.out.println("isEmpty() = " + isEmpty()
                + " , isFull() = " + isFull()
                + " , size() = " + size()
                + " , peek() = " + peek());
    }

    public static void main(String[] args) {
        Queue queue = new Queue(5);
        queue.insert(1);
        queue.print();                                             // isEmpty() = false , isFull() = false , size() = 1 , peek() = 1
        queue.insert(2);
        queue.print();                                             // isEmpty() = false , isFull() = false , size() = 2 , peek() = 1
        queue.insert(3);
        queue.print();                                             // isEmpty() = false , isFull() = false , size() = 3 , peek() = 1
        queue.insert(4);
        queue.print();                                             // isEmpty() = false , isFull() = false , size() = 4 , peek() = 1
        queue.insert(5);
        queue.print();                                             // isEmpty() = false , isFull() = true , size() = 5 , peek() = 1

//        queue.insert(6);                                         // java.lang.IllegalArgumentException: Queue is full , can not insert element.

        System.out.println("remove() = " + queue.remove());        // remove() = 1
        queue.print();                                             // isEmpty() = false , isFull() = false , size() = 4 , peek() = 2
        System.out.println("remove() = " + queue.remove());        // remove() = 2
        queue.print();                                             // isEmpty() = false , isFull() = false , size() = 3 , peek() = 3
        System.out.println("remove() = " + queue.remove());        // remove() = 3
        queue.print();                                             // isEmpty() = false , isFull() = false , size() = 2 , peek() = 4
        System.out.println("remove() = " + queue.remove());        // remove() = 4
        queue.print();                                             // isEmpty() = false , isFull() = false , size() = 1 , peek() = 5
        System.out.println("remove() = " + queue.remove());        // remove() = 5
        System.out.println("isEmpty() = " + queue.isEmpty());      // isEmpty() = true

//        System.out.println("remove() = " + queue.remove());      // java.lang.IllegalArgumentException: Quwuw is empty , can not remove element.
    }

}
