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
        this.balance = this.right.balance - this.left.balance;
        return this.balance;
    }

    public int height() {
        return height;
    }

    public void setHeight() {
        this.height = Math.max(this.left.height, this.right.height) + 1;
    }

    private void update(Node<T> node) {

    }
}
