package Data_Structures.Node;

public class RBNode<T extends Comparable<T>> extends Node<T> {

   private boolean black = false;

    public RBNode(T data) {
        super(data);
    }
    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }

    public void recolor() {

    }

    public void getUncle(RBNode<T> node) {

    }

    public boolean isBlack(RBNode<T> node) {
        return true;
    }
}
