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

    public Node<T> simpleInsert(T data) {
        if(this.root == null) {
            return null;
        }
        Node<T> node = null;
        if(this.root.left == null && this.root.data.compareTo(data) > 0) {
            node = new Node<T>(data);
            this.root.setLeft(node);
            System.out.println("Parent " + this.root.data() + " Left Child " + data + " this.root height " + this.root.height());
            node.setHeight(1);
            return node;
        } else if(this.root.right() == null && this.root.data().compareTo(data) <= 0) {
            node = new Node<T>(data);
            this.root.setRight(node);
            System.out.println("Parent " + this.root.data() + " Right Child " + data + " this.root height " + this.root.height());
            node.setHeight(1);
            return node;
        } else if (this.root.left() != null && this.root.data().compareTo(data) > 0) {
            Node<T> newNode = insert(this.root.left(), data);
            this.root.setLeft(newNode);
        } else if(this.root.right() != null && this.root.data().compareTo(data) <= 0) {
            Node<T> newNode = insert(this.root.right(), data);
            this.root.setRight(newNode);
        }

        int left = (this.root.left() == null ? 0 : this.root.left().height());
        int right = (this.root.right() == null ? 0 : this.root.right().height());

        this.root.setHeight(Math.max(left, right) + 1);

        return this.root;
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
