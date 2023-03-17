package Data_Structures.Tree;

import Data_Structures.Node.Node;

public abstract class Tree<T extends Comparable<T>> {

    protected Node<T> root;
    protected int size;

    public abstract Node<T> insert(T data);

    public abstract Node<T> delete(T data);

    public Tree(Node<T> root) {
        this.root = root;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void search() {

    }
    public Node<T> simpleInsert(Node<T> root, T data) {
        if(this.root == null) {
            return null;
        }
        Node<T> node = null;
        if(root.left == null && root.getData().compareTo(data) > 0) {
            node = new Node<T>(data);
            root.left = node;
            return node;
        } else if(root.right == null && root.getData().compareTo(data) < 0) {
            node = new Node<T>(data);
            root.right = node;
            return node;
        } else if(root.left != null && root.getData().compareTo(data) > 0) {
            System.out.println("Parent " + root.getData() + " left child " + root.left);
            simpleInsert(root.left, data);
        } else if(root.right != null && root.getData().compareTo(data) < 0) {
            System.out.println("Parent " + root.getData() + " right child " + root.right);
            simpleInsert(root.right, data);
        } else if((root.left != null && root.left.getData().compareTo(data) == 0) || (root.right != null && root.right.getData().compareTo(data) == 0)) {
            System.out.println("Found!!\n");
            return null;
        }
        return null;
    }

    public Node<T> simpleDelete(Node<T> node) {
        return null;
    }

    public void rotateRight(Node<T> node) {

    }

    public void rotateLeft(Node<T> node) {

    }

    public void replace(Node<T> prev, Node<T> node) {

    }
    public int compareTo(T o) {
        return 0;
    }

}
