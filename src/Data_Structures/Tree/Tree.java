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

    public void rotateRight(Node<T> node) {

    }

    public void rotateLeft(Node<T> node) {

    }

    public Node<T> rotate(Node<T> node){
        Node<T> parent = node.parent;
        Node<T> grandParent = parent.parent;
        switch (node.compareTo(parent) + parent.compareTo(grandParent) + grandParent.compareTo(node)){
            case 1: //LR rotation
                break;
            case 2://LL rotation

                break;
            case -1://RL rotation
                break;
            case -2://RR rotation
                break;
        }
        return null; //return new parent
    }

    public void replace(Node<T> prev, Node<T> node) {

    }
    public int compareTo(T o) {
        return 0;
    }

}
