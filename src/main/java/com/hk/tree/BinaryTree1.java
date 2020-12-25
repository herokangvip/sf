package com.hk.tree;

/**
 * 二叉树
 *
 * @author k
 * @version 1.0
 * @date 2020/12/22 16:53
 */
public class BinaryTree1 {


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);

        node1.beforePrint();
        System.out.println("========");
        node1.midPrint();
        System.out.println("========");
        node1.afterPrint();
        System.out.println("========查找");
        Node node = node1.beforeSearch(7);
        System.out.println(node);
        System.out.println("========删除");
        boolean delete = node1.delete(7);
        System.out.println("=");
        node1.beforePrint();



    }

    static class Node {
        private int no;
        private Node left;
        private Node right;

        public Node() {
        }

        public Node(int no) {
            this.no = no;
        }

        public Node(int no, Node left, Node right) {
            this.no = no;
            this.left = left;
            this.right = right;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }


        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("no=").append(no);
            sb.append('}');
            return sb.toString();
        }

        //前序遍历
        private void beforePrint() {
            System.out.println(this.no);
            if (this.getLeft() != null) {
                this.getLeft().beforePrint();
            }
            if (this.getRight() != null) {
                this.getRight().beforePrint();
            }
        }

        //前序遍历查找
        private Node beforeSearch(int num) {
            if (this.no == num) {
                return this;
            }
            Node res = null;
            if (this.getLeft() != null) {
                res = this.getLeft().beforeSearch(num);
            }
            if (res != null) {
                return res;
            }
            if (this.getRight() != null) {
                res = this.getRight().beforeSearch(num);
            }
            return res;
        }

        //删除节点
        private boolean delete(int num) {

            boolean res = false;
            Node left = this.getLeft();
            Node right = this.getRight();
            if (left != null) {
                if (left.getNo() != num) {
                    res = left.delete(num);
                } else {
                    this.setLeft(null);
                    return true;
                }
            }
            if (res) {
                return res;
            }
            if (right != null) {
                if (right.getNo() != num) {
                    res = right.delete(num);
                } else {
                    this.setRight(null);
                    return true;
                }
            }
            return res;
        }

        //中序遍历
        private void midPrint() {
            if (this.getLeft() != null) {
                this.getLeft().midPrint();
            }
            System.out.println(this.no);
            if (this.getRight() != null) {
                this.getRight().midPrint();
            }
        }

        //后序遍历
        private void afterPrint() {
            if (this.getLeft() != null) {
                this.getLeft().afterPrint();
            }
            if (this.getRight() != null) {
                this.getRight().afterPrint();
            }
            System.out.println(this.no);
        }
    }


}




