package Data_Structures.Tree;

import Data_Structures.Node.Node;

public abstract class Tree<T extends Comparable<T>> {
    /**
     * Each tree has its own root, so it is removed from the parent class
     * We can just add it for normal node, but it's preferred to make each tree has its own root!
     * */
    protected int size;

    public Tree() { }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Node<T> search(Node<T> root, T data) {
        return recursiveSearch(root, data);
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

    public Node<T> simpleInsert(Node<T> root, T data) {
        Node<T> node = null;
        if(root == null) {
            node = new Node<T>(data);
            root = node;
            return node;
        }
        if(root.left == null && root.getData().compareTo(data) > 0) {
            node = new Node<T>(data);
            root.left = node;
            node.parent = root;
            System.out.println("Parent " + root.getData() + " left child " + root.left.getData());
            return node;
        } else if(root.right == null && root.getData().compareTo(data) < 0) {
            node = new Node<T>(data);
            root.right = node;
            node.parent = root;
            System.out.println("Parent " + root.getData() + " right child " + root.right.getData());
            return node;
        } else if(root.left != null && root.getData().compareTo(data) > 0) {
            simpleInsert(root.left, data);
        } else if(root.right != null && root.getData().compareTo(data) < 0) {
            simpleInsert(root.right, data);
        } else if((root.left != null && root.left.getData().compareTo(data) == 0) || (root.right != null && root.right.getData().compareTo(data) == 0)) {
            System.out.println("Found!!\n");
            return null;
        }
        return null;
    }

    public Node<T> delete(Node<T> root, T key) {
        Node<T> node = search(root, key);
        return simpleDelete(node);
    }

    public Node<T> simpleDelete(Node<T> node) {
        Node<T> tmp;
        if(node == null)
            return null;
        if(node.left == null && node.right == null) {
            if(node.parent.right == node)
                node.parent.right = null;
            else
                node.parent.left = null;
            return node;
        }
        else if(node.left == null) {
            tmp = node.right;
        }
        else if(node.right == null) {
            tmp = node.left;
        }
        else {
            tmp = getSuccessor(node.right);
        }
        node.setData(tmp.getData());
        return simpleDelete(tmp);
    }

    private Node<T> getSuccessor(Node<T> node) {
        // Avoiding nulls /*Rowaina*/
        if(node == null || node.left == null) {
            System.out.println("Node passed null right rotate");
            return null;
        }

        Node<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public void rotateRight(Node<T> X) {
        // Avoiding nulls /*Rowaina*/
        if(X == null || X.left == null || X.right == null) {
            System.out.println("Node passed null right rotate");
            return;
        }

        Node<T> Y = X.left; // Get reference to Y
        // Fix X's parent links
        replace(X,Y);
        // Fix Y's right child links
        X.left = Y.right; // X replaces its left child with Y's right child
        Y.right.parent = X; // Y's right child points to X as its new parent
        // Fix X's links
        Y.right = X; // X becomes Y's right child
        X.parent = Y; // Y becomes X's parent
    }

    public void rotateLeft(Node<T> X) {
        // Avoiding nulls /*Rowaina*/
        if(X == null || X.left == null || X.right == null) {
            System.out.println("Node passed null left rotate");
            return;
        }

        Node<T> Y = X.right; // Get reference to Y
        // Fix X's parent links
        replace(X,Y);
        // Fix Y's left child links
        X.right = Y.left; // X replaces its right child with Y's left child
        Y.left.parent = X; // Y's left child points to X as its new parent
        // Fix X's links
        Y.left = X; // X becomes Y's left child
        X.parent = Y; // Y becomes X's parent
    }

    private void replace(Node<T> A, Node<T> B) { // Make A's parent B's parent
        // Avoiding nulls /*Rowaina*/
        if(A == null || B == null) {
            System.out.println("Null nodes passed in replace");
            return;
        }

        Node<T> P = A.parent; // Get reference to A's parent
        B.parent = P; // B's parent reference points to A's parent
        if (A == P.left) // A was the left child
            P.left = B; // B is now the left child
        else    // A was the right child
            P.right = B; // B is now the right child
    }

    public int rotate(Node<T> node){
        // Avoiding nulls /*Rowaina*/
        if(node == null || node.parent == null) {
            System.out.println("Null node passed rotate");
            return -1;
        }

        Node<T> parent = node.parent;
        Node<T> grandParent = parent.parent;
        return switch (node.compareTo(parent) + parent.compareTo(grandParent) + grandParent.compareTo(node)) {
            case 2 -> // LL case
                    0;
            case 1 ->// LR case
                    1;
            case -1 ->// RL case
                    2;
            case -2 ->// RR case
                    3;
            default -> -1;
        };
    }

    public void visit(VisitType visitType, Node<T> root) {
        if(visitType == VisitType.DFS)
            dfs(root);
        else if(visitType == VisitType.BFS)
            bfs(root);
    }

    public void dfs(Node<T> root) {
        if(root == null)
            return;
        System.out.println(root.getData());

        dfs(root.left);
        dfs(root.right);
    }

    public void bfs(Node<T> root) {
        System.out.println("bfs");
    }

}
