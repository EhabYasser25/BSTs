package Data_Structures.Node;

public class RBNode<T extends Comparable<T>> extends Node<T> {
    private boolean black = true;

    public RBNode(T data) {
        super(data);
    }
    public RBNode(){}
    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }

    public boolean isBlack(RBNode<T> node) {
        return true;
    }

    public RBNode<T> getRight(){
        return (RBNode<T>) super.getRight();
    }
    public RBNode<T> getLeft(){
        return (RBNode<T>) super.getLeft();
    }
    public RBNode<T> getParent(){
        return (RBNode<T>) super.getParent();
    }
    public RBNode<T> getSibling(){
        return (RBNode<T>) super.getSibling();
    }

    public RBNode<T> getFarNephew() {
        return (RBNode<T>) super.getFarNephew();
    }

    public RBNode<T> getNearNephew() {
        return (RBNode<T>) super.getNearNephew();
    }

    public void recolor() {
        this.black = !this.black;
    }

    public void swapColors(RBNode<T> node) {
        boolean tmp = node.isBlack();
        node.setBlack(this.black);
        this.black = tmp;
    }
}
