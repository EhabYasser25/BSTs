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

    public void setBalance() {
        // Avoiding nulls /*Rowaina*/
        if(right == null && left == null) balance = 0;
        else if(left == null) balance = right.height;
        else if(right == null) balance = left.height;
        else balance = right.height - left.height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight() {
        // Avoiding nulls /*Rowaina*/
        if(left == null && right == null) height = 0;
        else if(left == null) height = right.height;
        else if(right == null) height = left.height;
        else height = Math.max(left.height, right.height) + 1;
    }

    private void update(Node<T> node) {

    }
}
