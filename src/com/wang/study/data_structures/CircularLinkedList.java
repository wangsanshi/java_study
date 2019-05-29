package com.wang.study.data_structures;

/**
 * 循环链表的实现
 */
@SuppressWarnings("all")
public class CircularLinkedList {
    private Node frist;
    private int size;

    public CircularLinkedList() {
        frist = null;
        size = 0;
    }

    public void add(int data) {
        Node newNode = new Node(data);
        add(newNode);
    }

    public void add(Node newNode) {
        if (frist == null) {
            frist = newNode;
            frist.next = frist;
        } else {
            Node currentNode = frist.next;
            Node preNode = frist;

            while (true) {
                if (currentNode == frist) {
                    preNode.next = newNode;
                    newNode.next = frist;
                    break;
                } else {
                    preNode = currentNode;
                    currentNode = currentNode.next;
                }
            }
        }

        size++;
    }

    public int remove() {
        if (isEmpty()) {
            throw new IllegalArgumentException("LinkedStack is empty , can not remove element.");
        }

        int result;

        if (size == 1) {
            result = frist.data;
            frist.next = null;
            frist = null;
        } else {
            Node currentNode = frist;     //当前节点
            Node preNode = frist;         //前一个节点

            while (true) {
                //如果当前节点的下一个节点指向链表头，则说明当前节点为尾节点
                if (currentNode.next == frist) {
                    result = currentNode.data;
                    preNode.next = frist;
                    currentNode.next = null;
                    break;
                } else {
                    preNode = currentNode;
                    currentNode = currentNode.next;
                }
            }
        }

        size--;
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        CircularLinkedList list = new CircularLinkedList();
        list.add(node1);
        System.out.println("add node1 to list.");                                         // add node1 to list.
        System.out.println("node1 next node data is " + node1.next.next.data);            // node1 next node data is 1
        System.out.println();
        list.add(node2);
        System.out.println("add node2 to list.");                                         // add node2 to list.
        System.out.println("node1 next node data is " + node1.next.data);                 // node1 next node data is 2
        System.out.println("node2 next node data is " + node2.next.data);                 // node2 next node data is 1
        System.out.println();
        list.add(node3);
        System.out.println("add node3 to list.");                                         // add node3 to list.
        System.out.println("node1 next node data is " + node1.next.data);                 // node1 next node data is 2
        System.out.println("node2 next node data is " + node2.next.data);                 // node2 next node data is 3
        System.out.println("node3 next node data is " + node3.next.data);                 // node3 next node data is 1
        System.out.println();

        System.out.println("current remove node is " + list.remove());                    // current remove node is 3
        System.out.println("node1 next node data is " + node1.next.data);                 // node1 next node data is 2
        System.out.println("node2 next node data is " + node2.next.data);                 // node2 next node data is 1
        System.out.println("node3 next node data is " + node3.next);                      // node3 next node data is null
        System.out.println();

        System.out.println("current remove node is " + list.remove());                    // current remove node is 2
        System.out.println("node1 next node data is " + node1.next.data);                 // node1 next node data is 1
        System.out.println("node2 next node data is " + node2.next);                      // node2 next node data is null
        System.out.println("node3 next node data is " + node3.next);                      // node3 next node data is null
        System.out.println();

        System.out.println("current remove node is " + list.remove());                    // current remove node is 1
        System.out.println("node1 next node data is " + node1.next);                      // node1 next node data is null
        System.out.println("node2 next node data is " + node2.next);                      // node2 next node data is null
        System.out.println("node3 next node data is " + node3.next);                      // node3 next node data is null
    }

}
