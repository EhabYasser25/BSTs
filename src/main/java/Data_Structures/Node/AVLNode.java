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
        int lh = (this.getLeft() == null)? -1 : this.getLeft().height;
        int rh = (this.getRight() == null)? -1 : this.getRight().height;
        this.balance = rh-lh;
        return this.balance;
    }

    public int getHeight() {
        return height;
    }

    public int setHeight() {
        int lh = (this.getLeft() == null)? -1 : this.getLeft().height;
        int rh = (this.getRight() == null)? -1 : this.getRight().height;
        this.height = Math.max(lh,rh) + 1;
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

}
