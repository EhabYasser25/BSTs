package Data_Structures.Tree;

import Data_Structures.Node.AVLNode;
import Data_Structures.Node.Node;
import Data_Structures.Node.RBNode;

public class AVLTree<T extends Comparable<T>> extends Tree<T> {
   /**
    * Each tree has its own root, so it is removed from the parent class
    * We can just add it for normal node, but it's preferred to make each tree has its own root!
    * */
    public AVLNode<T> root;

    public AVLTree(T root) {
        this.root = new AVLNode<>(root);
    }

    public AVLTree() { }

    public void insert(T data) {
        root = insert(root,data);
    }
    
    public AVLNode<T> insert(AVLNode<T> node,T data) {
        //insert data in its place in bst
        AVLNode<T> tmp = new AVLNode<T>(data);
        if(node == null){
            node = tmp;
            return node;
        } 
        int compare = node.getData().compareTo(data);
        if(node.left == null && compare > 0) {
            node.left = tmp;
            tmp.parent = node;
            System.out.println("Parent " + node.getData() + " left child " + node.left.getData());
            return tmp;
        } else if(node.right == null && compare < 0) {
            node.right = tmp;
            tmp.parent = node;
            System.out.println("Parent " + node.getData() + " left child " + node.left.getData());
            return tmp;
        } else if(node.left != null && compare > 0) {
            insert(node.left, data);
        } else if(node.right != null && compare < 0) {
            insert(node.right, data);
        } else if((node.left != null && node.left.getData().compareTo(data) == 0) || (node.right != null && node.right.getData().compareTo(data) == 0)) {
            System.out.println("Found!!\n");
            return null;
        }
        return tmp;
    }

    public AVLNode<T> delete(T data) {
        return null;
    }

    public int getHeight() {
        return this.root.getHeight();
    }

    public void update(AVLNode<T> Y) {
        if (Y == null) return;
        Y.setBalance();
        int bf = Y.getBalance();
        // Test for imbalance and fix heights
        if (bf == -2){ // Left imbalance
            if (Y.left.getBalance() == 1)
                leftRightFix(Y); // Caused by right subtree of left child
            else
                leftLeftFix(Y); // Caused by left subtree of left child
        }else if (bf == 2){ // Right imbalance
            if (Y.right.getBalance() == -1)
                rightLeftFix(Y); // Caused by left subtree of right child
            else
                rightRightFix(Y); // Caused by right subtree of right child
        }else{ // Node has no imbalance
            Y.setHeight();
        }

        update(Y.parent);
    }

    private void leftLeftFix(AVLNode<T> x){
        // Avoiding nulls /*Rowaina*/
        if(x == null || x.left == null || x.right == null) return;

        AVLNode<T> y = x.left;
        rotateRight(x);
        x.setHeight(); x.setBalance();
        y.setHeight(); y.setBalance();
    }

    private void leftRightFix(AVLNode<T> x){
        // Avoiding nulls /*Rowaina*/
        if(x == null || x.left == null || x.right == null) return;

        AVLNode<T> y = x.left, z = y.right;
        rotateLeft(y); rotateRight(x);
        y.setHeight(); y.setBalance();
        x.setHeight(); x.setBalance();
        z.setHeight(); z.setBalance();
    }

    private void rightLeftFix(AVLNode<T> x){
        // Avoiding nulls /*Rowaina*/
        if(x == null || x.left == null || x.right == null) return;

        AVLNode<T> y = x.right, z = y.left;
        rotateRight(y); rotateLeft(x);
        y.setHeight(); y.setBalance();
        x.setHeight(); x.setBalance();
        z.setHeight(); z.setBalance();
    }

    private void rightRightFix(AVLNode<T> x){
        // Avoiding nulls /*Rowaina*/
        if(x == null || x.right == null) return;

        AVLNode<T> y = x.right;
        rotateLeft(x);
        x.setHeight(); x.setBalance();
        y.setHeight(); y.setBalance();
    }
}
