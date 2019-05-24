package com.wang.study.data_structures;

/**
 * 栈的实现，先进后出（用链表实现）
 */
@SuppressWarnings("all")
public class LinkedStack {
    private SingleLinkedList linkedList;
    private int size;

    public LinkedStack() {
        linkedList = new SingleLinkedList();
        size = 0;
    }

    public void push(int element) {
        linkedList.addLast(element);
        size++;
    }

    public int pop() {
        if (size <= 0) {
            throw new IllegalArgumentException("LinkedStack is empty , can not pop element.");
        }

        size--;
        return linkedList.removeLast();
    }

    public int peek() {
        if (size <= 0) {
            throw new IllegalArgumentException("LinkedStack is empty , can not pop element.");
        }

        return linkedList.printLast();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        if (size <= 0) {
            System.out.println("LinkedStack is empty.");
        } else {
            linkedList.print();
        }
    }

    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();

        stack.push(1);
        stack.print();                                                  // [ 1 ]
        stack.push(2);
        stack.print();                                                  // [ 1 2 ]
        System.out.println("当前出栈的元素为：" + stack.pop());            // 当前出栈的元素为：2
        stack.print();                                                  // [ 1 ]
        stack.push(3);
        stack.print();                                                  // [ 1 3 ]
        System.out.println("当前出栈的元素为：" + stack.pop());            // 当前出栈的元素为：3
        stack.print();                                                  // [ 1 ]
        stack.push(4);
        stack.print();                                                  // [ 1 4 ]
        stack.push(5);
        stack.print();                                                  // [ 1 4 5 ]
        System.out.println("当前出栈的元素为：" + stack.pop());            // 当前出栈的元素为：5
        stack.print();                                                  // [ 1 4 ]
        System.out.println("当前出栈的元素为：" + stack.pop());            // 当前出栈的元素为：4
        stack.print();                                                  // [ 1 ]
        System.out.println("当前出栈的元素为：" + stack.pop());            // 当前出栈的元素为：1
        stack.print();                                                  // LinkedStack is empty.
        System.out.println("当前出栈的元素为：" + stack.pop());            // java.lang.IllegalArgumentException: LinkedStack is empty , can not pop element.
    }

}
