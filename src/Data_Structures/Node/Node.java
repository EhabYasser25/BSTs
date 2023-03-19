package Data_Structures.Node;

public class Node<T extends Comparable<T>>{
    public T data;
    private Node<T> parent = null;
    private Node<T> left = null;
    private Node<T> right = null;

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node(T data) {
        this.data = data;
    }

    public Node(Node<T> n){
        this.data = n.data;
        this.parent = n.parent;
        this.left = n.left;
        this.right = n.right;
    }
    public Node(){}
    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public T getData() {
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

    public int compareTo(Node<T> o) {
        int value = this.data.compareTo(o.data);
        if(value == 0)
            return 0;
        else if(value >= 1)
            return 1;
        else
            return -1;
    }

    public boolean isLeftChild(){
        return this == this.parent.left;
    }

    public boolean isRightChild(){
        return this == this.parent.right;
    }

    public Node<T> getSibling(){
        if(this.parent == null)
            return null;
        return (isLeftChild())? this.parent.right : this.parent.left;
    }

    public Node<T> getUncle() {
        if(this.parent == null)
            return null;
        return this.parent.getSibling();
    }

    public Node<T> getFarNephew() {
        return this.isLeftChild()? this.getSibling().getRight() : this.getSibling().getLeft();
    }

    public Node<T> getNearNephew() {
        return this.isLeftChild()? this.getSibling().getLeft() : this.getSibling().getRight();
    }

}
