package com.wang.study.data_structures;

/**
 * 栈的实现，先进后出
 */
@SuppressWarnings("all")
public class Stack {
    private int maxSize;
    private int[] stackArray;
    private int top;

    public Stack(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Stack maxSize must > 0.");
        }

        this.maxSize = maxSize;
        this.top = -1;
        stackArray = new int[maxSize];
    }

    /**
     * 栈是否满了
     */
    public boolean isFull() {
        return top == (maxSize - 1);
    }

    /**
     * 栈是否为空
     */
    public boolean isEmpty() {
        return top < 0;
    }

    /**
     * 压栈
     *
     * @param element
     */
    public void push(int element) {
        if (isFull()) {
            throw new IllegalArgumentException("Stack is full.");
        }

        stackArray[++top] = element;
    }

    /**
     * 出栈
     */
    public int pop() {
        if (top < 0) {
            throw new IllegalArgumentException("Stack is empty.");
        }

        return stackArray[top--];
    }

    /**
     * 查看栈顶元素
     */
    public int peek() {
        if (top < 0) {
            throw new IllegalArgumentException("Stack is empty.");
        }

        return stackArray[top];
    }

    public void print() {
        System.out.println("isEmpty() = " + isEmpty()
                + " , isFull() = " + isFull()
                + " , peek() = " + peek());
    }

    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        stack.print();                                    // isEmpty() = false , isFull() = true , peek() = 5
//        stack.push(6);                                  // java.lang.IllegalArgumentException: stack is full.
        System.out.println("pop() = " + stack.pop());     // pop() = 5
        stack.print();                                    // isEmpty() = false , isFull() = false , peek() = 4
        System.out.println("pop() = " + stack.pop());     // pop() = 4
        stack.print();                                    // isEmpty() = false , isFull() = false , peek() = 3
        System.out.println("pop() = " + stack.pop());     // pop() = 3
        stack.print();                                    // isEmpty() = false , isFull() = false , peek() = 2
        System.out.println("pop() = " + stack.pop());     // pop() = 2
        stack.print();                                    // isEmpty() = false , isFull() = false , peek() = 1
        System.out.println("pop() = " + stack.pop());     // pop() = 1
//        System.out.println("pop() = " + stack.pop());   // java.lang.IllegalArgumentException: stack is empty.
    }

}
