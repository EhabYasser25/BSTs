package Data_Structures.Tree;

import Data_Structures.Node.Node;

public abstract class Tree<T> implements IBST<T> {

    protected Node<T> root;
    protected int size;

    public abstract Node<T> insert(T data);

    public abstract Node<T> delete(T data);

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void search() {

    }

    @Override
    public Node<T> simpleInsert(Node<T> node) {
        return null;
    }

    @Override
    public Node<T> simpleDelete(Node<T> node) {
        return null;
    }

    @Override
    public void rotateRight(Node<T> node) {

    }

    @Override
    public void rotateLeft(Node<T> node) {

    }

    @Override
    public void replace(Node<T> prev, Node<T> node) {

    }


}
