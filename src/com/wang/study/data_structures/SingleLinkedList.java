package com.wang.study.data_structures;

/**
 * 单链表的实现
 */
@SuppressWarnings("all")
public class SingleLinkedList {
    private Node frist;

    public SingleLinkedList() {
        frist = null;
    }

    /**
     * 从单链表头添加元素
     */
    public void addFrist(int data) {
        Node node = new Node(data);
        node.next = frist;
        frist = node;
    }

    /**
     * 从单链表头移除元素
     */
    public int removeFrist() {
        if (frist == null) {
            throw new IllegalArgumentException("SingleLinkedList is empty , can not remove element.");
        }

        int result = frist.data;
        frist = frist.next;
        return result;
    }

    /**
     * 查看链表头元素
     */
    public int printFrist() {
        if (frist == null) {
            throw new IllegalArgumentException("SingleLinkedList is empty , can not print frist element.");
        }

        return frist.data;
    }

    /**
     * 查看链表尾元素
     */
    public int printLast() {
        if (frist == null) {
            throw new IllegalArgumentException("SingleLinkedList is empty , can not print last element.");
        }

        Node currentNode = frist;
        while (true) {
            if (currentNode.next == null) {
                break;
            } else {
                currentNode = currentNode.next;
            }
        }

        return currentNode.data;
    }

    /**
     * 从单链表尾添加元素
     */
    public void addLast(int data) {
        Node node = new Node(data);

        if (frist == null) {
            frist = node;
        } else {
            Node currentNode = frist;
            while (true) {
                if (currentNode.next == null) {
                    currentNode.next = node;
                    break;
                } else {
                    currentNode = currentNode.next;
                }
            }
        }
    }

    /**
     * 从单链表尾移除元素
     */
    public int removeLast() {
        if (frist == null) {
            throw new IllegalArgumentException("SingleLinkedList is empty , can not remove element.");
        }

        int result;
        if (frist.next == null) {
            result = frist.data;
            frist = null;
        } else {
            Node currentNode = frist;
            while (true) {
                if (currentNode.next.next == null) {
                    result = currentNode.next.data;
                    currentNode.next = null;
                    break;
                } else {
                    currentNode = currentNode.next;
                }
            }
        }

        return result;
    }

    /**
     * 移除值为data的元素
     *
     * @param data 需要移除的元素
     */
    public void removeByData(int data) {
        if (frist == null) {
            throw new IllegalArgumentException("SingleLinkedList is empty , can not remove element.");
        } else {
            //该循环是保证链表头节点的值不等于data
            while (true) {
                if (frist != null && frist.data == data) {
                    frist = frist.next;
                } else {
                    break;
                }
            }

            if (frist == null) {
                return;
            }

            Node preNode = frist;
            Node currentNode = frist.next;

            while (true) {
                if (currentNode != null) {
                    if (currentNode.data == data) {
                        preNode.next = currentNode.next;
                        currentNode = preNode.next;
                    } else {
                        preNode = currentNode;
                        currentNode = currentNode.next;
                    }
                } else {
                    break;
                }
            }

        }
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("SingleLinkedList is empty.");
        } else {
            Node currentNode = frist;
            System.out.print("[ ");
            while (true) {
                if (currentNode == null) {
                    System.out.println("]");
                    break;
                } else {
                    System.out.print(currentNode.data + " ");
                    currentNode = currentNode.next;
                }
            }
        }
    }

    public boolean isEmpty() {
        return frist == null;
    }

    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        list.addFrist(3);
        list.addLast(3);
        list.print();                                                     // [ 3 3 ]
        list.removeByData(3);
        list.print();                                                     // SingleLinkedList is empty.

        list.addLast(1);
        list.addFrist(2);
        list.addLast(3);
        list.addLast(4);
        list.addFrist(5);
        list.addFrist(3);
        list.print();                                                     // [ 3 5 2 1 3 4 ]

        list.removeByData(3);
        list.print();                                                     // [ 5 2 1 4 ]

        System.out.println("当前移除的元素为：" + list.removeLast());        // 当前移除的元素为：4
        list.print();                                                     // [ 5 2 1 ]
        System.out.println("当前移除的元素为：" + list.removeFrist());       // 当前移除的元素为：5
        list.print();                                                     // [ 2 1 ]
        System.out.println("当前移除的元素为：" + list.removeLast());        // 当前移除的元素为：1
        list.print();                                                     // [ 2 ]
        System.out.println("当前移除的元素为：" + list.removeFrist());       // 当前移除的元素为：2
        list.print();                                                     // SingleLinkedList is empty.
        list.removeLast();                                                // java.lang.IllegalArgumentException: SingleLinkedList is empty , can not remove element.
    }

}
