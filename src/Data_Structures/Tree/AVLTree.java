package Data_Structures.Tree;

import Data_Structures.Node.AVLNode;
import Data_Structures.Node.Node;
import Data_Structures.Node.RBNode;

public class AVLTree<T extends Comparable<T>> extends Tree<T> {

    public AVLTree(T root) {
        super(new AVLNode<>(root));
    }


    @Override
    public Node<T> insert(T data) {
        return null;
    }

    @Override
    public Node<T> delete(T data) {
        return null;
    }

    public int getHeight() {
        return 0;
    }

    public void update(Node<T> node) {

    }

}
