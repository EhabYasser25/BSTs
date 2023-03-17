package Data_Structures.Tree;

import Data_Structures.Node.Node;
import Data_Structures.Node.RBNode;

public class RBTree<T extends Comparable<T>>  extends Tree<T> {
    private final RBNode<T> nil = new RBNode<>(null);

    public RBTree(T data) {
        super.root = new Node<>(data);
        nil.setBlack(true);
    }

    public RBTree(RBNode<T> root) {
        this.root = root;
        nil.setBlack(true);
    }

    @Override
    public Node<T> insert(T data) {
        return null;
    }

    @Override
    public Node<T> delete(T data) {
        return null;
    }

    private void checkAndFix(Node<T> node) {

    }

    private void colorFix() {

    }

    private void rotateFix() {

    }

    private int getRotateCase(Node<T> child, Node<T> parent, Node<T> grandparent) {
        return 0;
    }

    public int getHeight() {
        return 0;
    }
}
