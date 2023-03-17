package Data_Structures.Tree;

import Data_Structures.Node.Node;

public abstract class Tree<T extends Comparable>{

    protected Node<T> root;
    protected int size;

    public abstract Node<T> insert(T data);

    public abstract Node<T> delete(T data);

    public int getSize() {
        return size;
    }

    public void search() {

    }

    public Node<T> simpleInsert(Node<T> node) {
    }

    public Node<T> simpleDelete(Node<T> node) {
        return null;
    }

    public void rotateRight(Node<T> node) {

    }

    public void rotateLeft(Node<T> node) {

    }

    public void replace(Node<T> prev, Node<T> node) {

    }
    public int compareTo(T o) {
        return 0;
    }

}
