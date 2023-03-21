package Data_Structures.Tree;

import Data_Structures.Node.AVLNode;

public class AVL<T extends Comparable<T>> extends BST<T> {
    /**
     * Each tree has its own root, so it is removed from the parent class
     * We can just add it for normal node, but it's preferred to make each tree has its own root!
     * */

    public AVL(T data) {
        this.root = new AVLNode<>(data);
        this.root.setParent(null);
        this.root.setLeft(null);
        this.root.setRight(null);
        this.size++;
    }
    public AVL(AVLNode<T> root) {
        this.root = root;
        if(this.root.getParent() == null)
            this.root.setParent(null);
        if(this.root.getRight() == null)
            this.root.setRight(null);
        if(this.root.getLeft() == null)
            this.root.setLeft(null);
    }
    public AVL() { }

    public AVLNode<T> getRoot(){
        return (AVLNode<T>) this.root;
    }
    public int getHeight() {
        return ((AVLNode<T>) this.root).getHeight();
    }

    public boolean insert(T data) {
        AVLNode<T> newNode = new AVLNode<>(data);
        if(node_isNull(this.root)){
            this.root = newNode;
            size++;
            return true;
        }
        if(node_isNull(super.insert(this.root, null, newNode)))
            return false;
        update(newNode.getParent());
        return true;
    }

    // The method the client deals with, returns false if value doesn't exist and true if it was successfully removed
    public boolean delete(T key){
        AVLNode<T> target = (AVLNode<T>) super.search(this.root, key);
        if (target == null){ // If the key doesn't exist
            return false;
        }
        update(actualDelete(target)); // Update the parent of the deleted node
        size--; // Decrement size
        return true;
    }

    // Takes reference to node we want to delete and return reference to the parent of the deleted node
    private AVLNode<T> actualDelete(AVLNode<T> target) {
        // Check for the node having right or left children
        boolean noLeft = target.getLeft() == null, noRight = target.getRight() == null;
        // Recursive case: node has both left and right children
        if(!noLeft && !noRight){
            // Get the smallest (leftmost) element of the right subtree
            AVLNode<T> newTarget = target.getRight();
            while(newTarget.getLeft() != null) newTarget = newTarget.getLeft();
            // Copy its data to the node we originally intended to delete
            target.setData(newTarget.getData());
            // Run algorithm again on the new target for a base case
            return actualDelete(newTarget);
        }
        // Set reference to parent being returned
        AVLNode<T> p = target.getParent();
        // Base case 1: node being deleted is a leaf node
        if (noLeft && noRight){
            if (target.isLeftChild()) p.setLeft(null); else p.setRight(null);
            target.setParent(null);
            return p;
        }
        // Set reference to node replacing the deleted node in the other base cases
        AVLNode<T> successor;
        if(noLeft) // Base case 2: node has only a right child
            successor = target.getRight();
        else // Base case 3: node only has a left child
            successor = target.getLeft();
        // Make parent replace target with successor
        if (target.isLeftChild()) p.setLeft(successor); else p.setRight(successor);
        successor.setParent(p); // Set successor's parent to be the target's parent
        // Disconnect the target
        target.setParent(null); target.setRight(null);
        return p; // Return reference to parent node to update it
    }

    private void update(AVLNode<T> Y) {
        if (Y == null) return;
        int bf = Y.setBalance();
        // Test for imbalance and fix heights
        if (bf == -2){ // Left imbalance
            if (Y.getLeft().getBalance() == 1)
                leftRightFix(Y); // Caused by right subtree of left child
            else
                leftLeftFix(Y); // Caused by left subtree of left child
        }else if (bf == 2){ // Right imbalance
            if (Y.getRight().getBalance() == -1)
                rightLeftFix(Y); // Caused by left subtree of right child
            else
                rightRightFix(Y); // Caused by right subtree of right child
        }else{ // Node has no imbalance
            Y.setHeight();
        }
        update(Y.getParent());
    }
    private void leftLeftFix(AVLNode<T> x){
        AVLNode<T> y = x.getLeft();
        rotateRight(x);
        x.setHeight(); x.setBalance();
        y.setHeight(); y.setBalance();
    }
    private void leftRightFix(AVLNode<T> x){
        AVLNode<T> y = x.getLeft(), z = y.getRight();
        rotateLeft(y); rotateRight(x);
        y.setHeight(); y.setBalance();
        x.setHeight(); x.setBalance();
        z.setHeight(); z.setBalance();
    }
    private void rightLeftFix(AVLNode<T> x){
        AVLNode<T> y = x.getRight(), z = y.getLeft();
        rotateRight(y); rotateLeft(x);
        y.setHeight(); y.setBalance();
        x.setHeight(); x.setBalance();
        z.setHeight(); z.setBalance();
    }
    private void rightRightFix(AVLNode<T> x) {
        AVLNode<T> y = x.getRight();
        rotateLeft(x);
        x.setHeight(); x.setBalance();
        y.setHeight(); y.setBalance();
    }

}