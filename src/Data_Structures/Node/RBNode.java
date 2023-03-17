package Data_Structures.Node;

public class RBNode<T extends Comparable<T>> extends Node<T> {

   private boolean black = true;
    public RBNode<T> parent = null;
    public RBNode<T> left = null;
    public RBNode<T> right = null;

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

    public Node<T> getUncle() {
        if(this.parent == null)
            return null;
        return this.parent.getSibling();
    }

    public boolean isBlack(RBNode<T> node) {
        return true;
    }

    public RBNode<T> getParent(){
        return (RBNode<T>) this.parent;
    }
}
