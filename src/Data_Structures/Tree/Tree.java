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

    public Tree() {

    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Node<T> search(T data) {
        return recursiveSearch(this.root, data);
    }

    private Node<T> recursiveSearch(Node<T> current, T data){

        if(current == null)
            return null;

        if(current.getData().compareTo(data) < 0){
            return recursiveSearch(current.right, data);
        }
        else if(current.getData().compareTo(data) == 0){
            return current;
        }
        else
            return recursiveSearch(current.left, data);
    }
    
    public Node<T> simpleInsert(Node<T> root, T data) {
        if(this.root == null) {
            return null;
        }
        Node<T> node = null;
        if(root.left == null && root.getData().compareTo(data) > 0) {
            node = new Node<T>(data);
            root.left = node;
            System.out.println("Parent " + root.getData() + " left child " + root.left.getData());
            return node;
        } else if(root.right == null && root.getData().compareTo(data) < 0) {
            node = new Node<T>(data);
            root.right = node;
            System.out.println("Parent " + root.getData() + " right child " + root.right.getData());
            return node;
        } else if(root.left != null && root.getData().compareTo(data) > 0) {
            simpleInsert(root.left, data);
        } else if(root.right != null && root.getData().compareTo(data) < 0) {
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

    public void rotateRight(Node<T> X) {
        Node<T> Y = X.left; // Get reference to Y
        // Fix X's parent links
        replace(X,Y);
        // Fix Y's right child links
        X.left = Y.right; // X replaces its left child with Y's right child
        Y.right.parent = X; // Y's right child points to X as its new parent
        // Fix X's links
        Y.right = X; // X becomes Y's right child
        X.parent = Y; // Y becomes X's parent
    }

    public void rotateLeft(Node<T> X) {
        Node<T> Y = X.right; // Get reference to Y
        // Fix X's parent links
        replace(X,Y);
        // Fix Y's left child links
        X.right = Y.left; // X replaces its right child with Y's left child
        Y.left.parent = X; // Y's left child points to X as its new parent
        // Fix X's links
        Y.left = X; // X becomes Y's left child
        X.parent = Y; // Y becomes X's parent
    }

    private void replace(Node<T> A, Node<T> B) { // Make A's parent B's parent
        Node<T> P = A.parent; // Get reference to A's parent
        B.parent = P; // B's parent reference points to A's parent
        if (A == P.left) // A was the left child
            P.left = B; // B is now the left child
        else    // A was the right child
            P.right = B; // B is now the right child
    }

    public int rotate(Node<T> node){
        Node<T> parent = node.parent;
        Node<T> grandParent = parent.parent;
        switch (node.compareTo(parent) + parent.compareTo(grandParent) + grandParent.compareTo(node)){
            case 2: // LL case
                return 0;
            case 1:// LR case
                return 1;
            case -1:// RL case
                return 2;
            case -2:// RR case
                return 3;
        }
        return -1;
    }

}
