package Data_Structures.Tree;

import Data_Structures.Node.AVLNode;
import Data_Structures.Node.NilNode;
import Data_Structures.Node.Node;

public class BST<T extends Comparable<T>> {
    /**
     * Each tree has its own root, so it is removed from the parent class
     * We can just add it for normal node, but it's preferred to make each tree has its own root!
     * */
    protected int size;
    public Node<T> root;

    public BST() {

    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Node<T> search(T data) {
        return search(this.root, data);
    }

    private Node<T> search(Node<T> current, T data){

        if(current == null)
            return null;

        if(current.getData().compareTo(data) < 0){
            return search(current.getRight(), data);
        }
        else if(current.getData().compareTo(data) == 0){
            return current;
        }
        else
            return search(current.getLeft(), data);
    }

    public Node<T> insert(T data){
        Node<T> newNode = new Node<>(data);
        insert(root, null, newNode);
        return newNode;
    }

    protected Node<T> insert(Node<T> node, Node<T> parent, Node<T> newNode) {
        if(this.root == null) {
            this.root = newNode;
        }
        if(node_isNull(node)) {
            node = newNode;
            node.setParent(parent);
            size++;
            return node;
        }
        if(node.compareTo(newNode) > 0)
            node.setLeft(insert(node.getLeft(), node, newNode));
        else if(node.compareTo(newNode) < 0)
            node.setRight(insert(node.getRight(), node, newNode));
        return node;
    }

    public Node<T> getRoot(){
        return this.root;
    }

    public boolean delete(T key) {
        Node<T> node = search(key);
        Node<T> deletedNode = delete(node);
        if(deletedNode.isLeftChild())
            deletedNode.getParent().setLeft(null);
        else
            deletedNode.getParent().setRight(null);
        return node != null;
    }

    public Node<T> delete(Node<T> node) {
        Node<T> tmp;
        if(node == null)
            return null;
        if(node_isNull(node.getLeft()) && node_isNull(node.getRight()))
            return node;
        else if(node_isNull(node.getLeft())) {
            tmp = node.getRight();
        }
        else if(node_isNull(node.getRight())) {
            tmp = node.getLeft();
        }
        else {
            tmp = getSuccessor(node);
        }
        node.setData(tmp.getData());
        return delete(tmp);
    }

    private Node<T> getSuccessor(Node<T> node){
        Node<T> current = node.getRight();
        if(node_isNull(current))
            return null;
        while(!node_isNull(current.getLeft()))
            current = current.getLeft();
        return current;
    }

    private Node<T> getPredeccessor(Node<T> node){
        Node<T> current = node.getLeft();
        if(node_isNull(current))
            return null;
        while(!node_isNull(current.getRight()))
            current = current.getRight();
        return current;
    }

    public void rotateRight(Node<T> X) {
        Node<T> Y = X.getLeft(); // Get reference to Y
        // Fix X's parent links
        replace(X,Y);
        // Fix Y's getRight() child links
        X.setLeft(Y.getRight()); // X replaces its getLeft() child with Y's getRight() child
        Y.getRight().setParent(X); // Y's getRight() child points to X as its new parent
        // Fix X's links
        Y.setRight(X); // X becomes Y's getRight() child
        X.setParent(Y); // Y becomes X's parent
    }

    public void rotateLeft(Node<T> X) {
        Node<T> Y = X.getRight(); // Get reference to Y
        // Fix X's parent links
        replace(X,Y);
        // Fix Y's getLeft() child links
        X.setRight(Y.getLeft()); // X replaces its getRight() child with Y's getLeft() child
        Y.getLeft().setParent(X); // Y's getLeft() child points to X as its new parent
        // Fix X's links
        Y.setLeft(X); // X becomes Y's getLeft() child
        X.setParent(Y); // Y becomes X's parent
    }

    private void replace(Node<T> A, Node<T> B) { // Make A's parent B's parent
        Node<T> P = A.getParent(); // Get reference to A's parent
        B.setParent(P); // B's parent reference points to A's parent
        if(A == this.root){
            this.root = B;
            return;
        }

        if (A == P.getLeft()) // A was the getLeft() child
            P.setLeft(B); // B is now the getLeft() child
        else    // A was the getRight() child
            P.setRight(B); // B is now the getRight() child
    }
    public int rotate_from_grandChild(Node<T> node){
        Node<T> parent = node.getParent();
        Node<T> grandParent = parent.getParent();
        return rotate(node, parent, grandParent);
    }
    public int rotate(Node<T> node, Node<T> parent, Node<T> grandParent){
        switch (node.compareTo(parent) + parent.compareTo(grandParent)){
            case -2: // LL case
                rotateRight(grandParent);
                return 0;
            case 2:// RR case
                rotateLeft(grandParent);
                return 1;
        }
        switch (grandParent.compareTo(node)){
            case 1:// LR case
                rotateLeft(grandParent.getLeft());
                rotateRight(grandParent);
                return 2;
            case -1:// RL case
                rotateRight(grandParent.getRight());
                rotateLeft(grandParent);
                return 3;
        }
        return -1;
    }

    public void visit(VisitType visitType) {
        if(visitType == VisitType.DFS)
            dfs(this.root);
        else if(visitType == VisitType.BFS)
            bfs(this.root);
    }

    public void dfs(Node<T> node) {
        if(node_isNull(node))
            return;
        System.out.println(node.data);

        dfs(node.getLeft());
        dfs(node.getRight());
    }

    public void bfs(Node<T> root) {
        System.out.println("bfs");
    }

    public boolean node_isNull(Node<T> node){
        return node == null || node instanceof NilNode<T>;
    }

}