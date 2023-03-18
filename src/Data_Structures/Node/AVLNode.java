package Data_Structures.Node;

public class AVLNode<T extends Comparable<T>> extends Node<T> {
    public AVLNode<T> parent = null;
    public AVLNode<T> left = null;
    public AVLNode<T> right = null;
    private int height = 0;
    private int balance = 0;

    public AVLNode(T data) {
        super(data);
    }

    public int getBalance() {
        return balance;
    }

    public int setBalance() {
        int lh = (left == null)? -1 : left.height, rh = (right == null)? -1 : right.height;
        balance = rh - lh;
        return balance;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight() {
        int lh = (left == null)? -1 : left.height, rh = (right == null)? -1 : right.height;
        height = Math.max(lh,rh) + 1;
    }

    private void update(Node<T> node) {

    }
}
