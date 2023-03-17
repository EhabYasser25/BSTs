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

    public int balance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int height() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private void update(Node<T> node) {

    }
}
