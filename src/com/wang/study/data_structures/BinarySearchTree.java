package com.wang.study.data_structures;

/**
 * 二叉搜索树
 * <p>
 * 性质：
 * 1、若它的左子树不为空，则左子树上所有节点的值均小于它的根节点的值
 * 2、若它的右子树不为空，则右子树上所有节点的值均大于它的根节点的值
 */
@SuppressWarnings("all")
public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void find(int data) {
        Node currentNode = root;

        while (currentNode != null) {
            if (currentNode.data == data) {
                System.out.println("在二叉搜索树中找到 data = " + data + " 的元素.");
                return;
            } else if (currentNode.data < data) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
        }

        System.out.println("在二叉搜索树中未找到 data = " + data + " 的元素.");
    }

    public void insert(int data) {
        Node newNode = new Node(data);

        if (root == null) {
            root = newNode;
        } else {
            Node currentNode = root;
            Node parentNode;

            while (true) {
                parentNode = currentNode;

                if (currentNode.data == data) {
                    System.out.println("当前需要插入的数据 " + data + " 已存在，插入失败!");
                    return;
                } else if (data > currentNode.data) {
                    currentNode = currentNode.right;
                    if (currentNode == null) {
                        parentNode.right = newNode;
                        return;
                    }
                } else {
                    currentNode = currentNode.left;
                    if (currentNode == null) {
                        parentNode.left = newNode;
                        return;
                    }
                }
            }
        }

    }

    public void delete(int data) {

    }

    /**
     * 前序遍历
     */
    public void frontTraversal(Node node) {
        if (node != null) {
            frontTraversal(node.left);
            System.out.println(node.data + " 、 ");
            frontTraversal(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void centerTraversal(Node node) {
        if (node != null) {
            System.out.println(node.data + " 、 ");
            centerTraversal(node.left);
            centerTraversal(node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void afterTravelsal(Node node) {
        if (node != null) {
            afterTravelsal(node.right);
            afterTravelsal(node.left);
            System.out.println(node.data + " 、 ");
        }
    }

    /**
     * 找到最小值
     */
    public int findMin() {
        if (root == null) {
            throw new IllegalArgumentException("the binary search tree is empty.");
        }
        int result = root.data;
        Node currentNode = root;
        while (true) {
            if (currentNode.left == null) {
                result = currentNode.data;
                break;
            } else {
                currentNode = currentNode.left;
            }
        }

        return result;
    }

    /**
     * 找到最大值
     */
    public int findMax() {
        if (root == null) {
            throw new IllegalArgumentException("the binary search tree is empty.");
        }
        int result = root.data;
        Node currentNode = root;
        while (true) {
            if (currentNode.right == null) {
                result = currentNode.data;
                break;
            } else {
                currentNode = currentNode.right;
            }
        }

        return result;
    }

    private static class Node {
        public Node(int data) {
            this.data = data;
        }

        int data;
        Node left;
        Node right;
    }

}
