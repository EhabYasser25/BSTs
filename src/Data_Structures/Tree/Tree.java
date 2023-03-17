package Data_Structures.Tree;

import Data_Structures.Node.Node;

public abstract class Tree<T extends Comparable<T>>{

    protected Node<T> root;
    protected int size;

    public abstract Node<T> insert(T data);

    public abstract Node<T> delete(T data);

    public int getSize() {
        return size;
    }

    public Node<T> search(T data) {
        return recursiveSearch(this.root, data);
    }

    private Node<T> recursiveSearch(Node<T> current, T data){

        if(current == null)
            return null;

        if(current.getData().compareTo(data) < 0){
            return recursiveSearch(current.right, data);
        }
        else if(current.getData().compareTo(data) == 0){
            return current;
        }
        else
            return recursiveSearch(current.left, data);
    }

    public Node<T> simpleInsert(Node <T> data) {
        return null;
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
