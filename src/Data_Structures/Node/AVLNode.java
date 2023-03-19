package Data_Structures.Node;

public class AVLNode<T extends Comparable<T>> extends Node<T> {

    private int height = 0;
    private int balance = 0;

    public AVLNode(T data) {
        super(data);
    }

    public int getBalance() {
        return balance;
    }

    public int setBalance() {
        this.balance = this.getRight().height-this.getLeft().height;
        return this.balance;
    }

    public int getHeight() {
        return height;
    }

    public int setHeight() {
        this.height = Math.max(this.getLeft().height, this.getRight().height) + 1;
        return this.height;
    }

    public AVLNode<T> getRight(){
        return (AVLNode<T>) super.getRight();
    }
    public AVLNode<T> getLeft(){
        return (AVLNode<T>) super.getLeft();
    }
    public AVLNode<T> getParent(){
        return (AVLNode<T>) super.getParent();
    }
    public AVLNode<T> getUncle() {
        return (AVLNode<T>) super.getUncle();
    }

    private void update(Node<T> node) { }

}
