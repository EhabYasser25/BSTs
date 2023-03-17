package Data_Structures.Tree;

import Data_Structures.Node.Node;
import Data_Structures.Node.RBNode;

public class RBTree<T extends Comparable<T>>  extends Tree<T> {
    private final RBNode<T> nil = new RBNode<>(null);

    public RBTree(T data) {
        super(new RBNode<>(data));
        this.root.left = nil;
        this.root.right = nil;
    }

    public RBTree(RBNode<T> root) {
        super(root);
        if(this.root.right == null)
            this.root.right = nil;
        if(this.root.left == null)
            this.root.left = nil;
    }

    public RBTree() { }

    @Override
    public Node<T> insert(T data) {
        return null;
    }

    @Override
    public Node<T> delete(T data) {
        return null;
    }

    private void checkAndFix(RBNode<T> node) {
        if(node.parent.isBlack())
            return;
        RBNode<T> uncle = (RBNode<T>) node.getUncle();
        if(uncle.isBlack())
            rotateFix(node);
        else
            colorFix(node);
    }

    private void colorFix(RBNode <T> node) {
        RBNode<T> parent = node.parent;
        RBNode<T> grandParent = node.parent.parent;
        parent.setBlack(true);
        ((RBNode<T>) parent.getSibling()).setBlack(true);
        grandParent.setBlack(false);
        checkAndFix(grandParent);
    }

    private void rotateFix(RBNode<T> node) {
//        rotate(node);
    }

    private int getRotateCase(Node<T> child, Node<T> parent, Node<T> grandparent) {
        return 0;
    }

    public int getHeight() {
        return 0;
    }
}
