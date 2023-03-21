package Data_Structures.Tree.HelpingClasses;

import Data_Structures.Node.Node;
import Data_Structures.Tree.AVL;
import Data_Structures.Tree.BST;
import Data_Structures.Tree.RB;

public class TreeCloner<T extends Comparable<T>> {

    BST<T> base;

    public TreeCloner(BST<T> tree){
        base = tree;
    }

    public BST<T> getClone() throws CloneNotSupportedException{
        BST<T> newTree = null;
        if(base instanceof RB<T>)
            newTree = new RB<>();
        else if(base instanceof AVL<T>)
            newTree = new AVL<>();
        else
            newTree = new BST<>();
        newTree.setRoot(clone_process(base.getRoot(), null));
        newTree.setSize(base.getSize());
        return newTree;
    }

    private Node<T> clone_process(Node<T> current, Node<T> parent) throws CloneNotSupportedException {
        if(current == null)
            return null;
        Node<T> node_clone = current.clone();
        node_clone.setParent(parent);
        node_clone.setLeft(clone_process(current.getLeft(), node_clone));
        node_clone.setRight(clone_process(current.getRight(), node_clone));
        return node_clone;
    }
}
