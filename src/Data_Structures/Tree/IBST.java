package Data_Structures.Tree;

import Data_Structures.Node.Node;

public interface IBST<T> {

    public int getSize();

    public void search();

    public Node<T> simpleInsert(Node<T> node);

    public Node<T> simpleDelete(Node<T> node);

    public void rotateRight(Node<T> node);

    public void rotateLeft(Node<T> node);

    public void replace(Node<T> prev, Node<T> node);

}
