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

    /**
     * 搜索数据
     */
    public void search(int data) {
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

    /**
     * 插入数据
     */
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

    /**
     * 删除节点
     *
     * @param data 需要删除的节点的值
     * @return 是否删除成功
     */
    public boolean delete(int data) {
        if (root == null) {
            System.out.println("the BinarySearchTree is empty.");
            return false;
        }

        Node currentNode = root;
        Node parentNode = root;
        boolean isLeftChild = true;

        while (currentNode.data != data) {
            parentNode = currentNode;

            if (data < currentNode.data) {
                isLeftChild = true;
                currentNode = currentNode.left;
            } else {
                isLeftChild = false;
                currentNode = currentNode.right;
            }

            //未找到值等于data的节点，退出循环，删除失败
            if (currentNode == null) {
                return false;
            }
        }

        //走到这里表示已经找到需要删除的节点
        if (currentNode.left == null && currentNode.right == null) {
            //需要删除的节点为叶子节点
            if (currentNode != root) {
                //如果该节点不为根的话，直接将其父节点的引用置为空
                if (isLeftChild) {
                    parentNode.left = null;
                } else {
                    parentNode.right = null;
                }
            } else {
                //如果该节点为根节点，则直接将根节点置空
                root = null;
            }
        } else if (currentNode.left == null) {
            //需要删除的节点只有右子节点
            if (currentNode == root) {
                root = currentNode.right;
            } else if (isLeftChild) {
                parentNode.left = currentNode.right;
            } else {
                parentNode.right = currentNode.right;
            }
        } else if (currentNode.right == null) {
            //需要删除的节点只有左子节点
            if (currentNode == root) {
                root = currentNode.left;
            } else if (isLeftChild) {
                parentNode.left = currentNode.left;
            } else {
                parentNode.right = currentNode.left;
            }
        } else {
            //需要删除的节点既有左子节点，也有右子节点
            Node successorNode = getSuccessorNode(currentNode);
            if (currentNode == root) {
                root = successorNode;
            } else if (isLeftChild) {
                parentNode.left = successorNode;                            // c
            } else {
                parentNode.right = successorNode;                           // c
            }

            //将当前需要删除的节点的左子树指向后继节点的左子树
            successorNode.left = currentNode.left;                          // d
        }

        return true;
    }

    /**
     * 获取需要删除节点的后继节点
     *
     * @param deleteNode 需要删除的节点
     * @return 后继节点
     */
    private Node getSuccessorNode(Node deleteNode) {
        Node successorParentNode = deleteNode;
        Node successorNode = deleteNode;
        Node currentNode = deleteNode.right;

        while (currentNode != null) {
            successorParentNode = successorNode;
            successorNode = currentNode;
            currentNode = currentNode.left;
        }

        /*
         * 走到这里表示需要删除的节点的后继节点已找到，它有两种可能
         * 1.后继节点是需要删除节点的右子节点（此时右字节点没有左子节点），其操作步骤为： c -->> d
         * 2.后继节点是需要删除节点的右子节点的左子孙节点，其操作步骤为： a -->> b -->> c -->> d
         */
        if (successorNode != deleteNode.right) {
            //这里为第二种情况
            //此时后继节点是肯定没有左子节点的
            successorParentNode.left = successorNode.right;               // a
            successorNode.right = deleteNode.right;                       // b
        }

        return successorNode;
    }

    /**
     * 获取根节点
     */
    public Node getRootNode() {
        return this.root;
    }

    /**
     * 前序遍历
     */
    public void frontTraversal(Node node) {
        if (node != null) {
            frontTraversal(node.left);
            System.out.print(node.data + " ");
            frontTraversal(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void centerTraversal(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
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
            System.out.print(node.data + " ");
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

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(9);
        tree.insert(1);
        tree.insert(3);
        tree.insert(2);
        tree.insert(7);
        tree.insert(8);
        tree.insert(4);
        tree.insert(6);

        System.out.print("先序遍历为：");                             // 先序遍历为：1 2 3 4 5 6 7 8 9
        tree.frontTraversal(tree.getRootNode());
        System.out.print("\n中序遍历为：");                           // 中序遍历为：5 1 3 2 4 9 7 6 8
        tree.centerTraversal(tree.getRootNode());
        System.out.print("\n后序遍历为：");                           // 后序遍历为：8 6 7 9 4 2 3 1 5
        tree.afterTravelsal(tree.getRootNode());

        System.out.println();
        tree.search(7);                                        // 在二叉搜索树中找到 data = 7 的元素.
        tree.search(0);                                        // 在二叉搜索树中未找到 data = 0 的元素.

        System.out.println(tree.delete(0));                    // false
        System.out.println(tree.delete(7));                    // true

        System.out.print("先序遍历为：");                             // 先序遍历为：1 2 3 4 5 6 8 9
        tree.frontTraversal(tree.getRootNode());
        System.out.print("\n中序遍历为：");                           // 中序遍历为：5 1 3 2 4 9 8 6
        tree.centerTraversal(tree.getRootNode());
        System.out.print("\n后序遍历为：");                           // 后序遍历为：6 8 9 4 2 3 1 5
        tree.afterTravelsal(tree.getRootNode());
    }

}
