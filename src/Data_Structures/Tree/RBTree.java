package Data_Structures.Tree;

import Data_Structures.Node.Node;
import Data_Structures.Node.RBNode;

public class RBTree<T extends Comparable<T>>  extends Tree<T> {
    /**
     * Each tree has its own root, so it is removed from the parent class
     * We can just add it for normal node, but it's preferred to make each tree has its own root!
     * */

    private final RBNode<T> nil = new RBNode<>(null);

    private RBNode<T> root;

    public RBTree(T data) {
        this.root = new RBNode<>(data);
        this.root.left = nil;
        this.root.right = nil;
    }

    public RBTree(RBNode<T> root) {
        this.root = root;
        if(this.root.right == null)
            this.root.right = nil;
        if(this.root.left == null)
            this.root.left = nil;
    }

    public RBTree() { }

    public RBNode<T> insert(T data) {
        RBNode<T> newNode = (RBNode<T>) this.simpleInsert(this.root, data);
        // TODO RB insert fix
        return newNode;
    }

    public RBNode<T> delete(T key) {
        RBNode<T> node = delete(key);
        checkAndFix(node);
        return node;
    }

    private void checkAndFix(RBNode<T> Z) { // Called after inserting Z as a red node

        if (size == 1){ // Z is the root
            Z.recolor();
            return;
        }

        RBNode<T> Y = Z.parent; // Parent node

        if(Y.isBlack()) // No problems if parent is black
            return;

        // parent Y is red, grandparent X is black
        RBNode<T> U = Z.getUncle(), X = Y.parent;
        if(U.isBlack())
            rotateFix(X,Y,Z, getRotateCase(X,Y,Z));
        else
            colorFix(X,Y,Z); // Uncle is red, recolor and check the newly red node
    }

    private void colorFix(RBNode<T> X, RBNode<T> Y, RBNode<T> Z) {
        RBNode<T> U = Z.getUncle();
        Y.recolor(); X.recolor(); U.recolor(); // Change Uncle and parent to black, grandparent to red
        if (X == root){ // If the grandparent is the root
            X.recolor(); // recolor it back to black
        }else{
            checkAndFix(X); //If it is not, run the check method again on the newly red grandparent
        }
    }

    private void rotateFix(RBNode<T> X, RBNode<T> Y, RBNode<T> Z, int rotateCase) {
        switch(rotateCase){
            case 0 -> { //LL
                rotateRight(X);
                X.recolor(); Y.recolor();
            }
            case 1 -> { //LR
                rotateLeft(Y); rotateRight(X);
                X.recolor(); Z.recolor();
            }
            case 2 -> { //RL
                rotateRight(Y); rotateLeft(X);
                X.recolor(); Z.recolor();
            }
            case 3 -> { //RR
                rotateLeft(X);
                X.recolor(); Y.recolor();
            }
        }
    }

    private int getRotateCase(RBNode<T> X, RBNode<T> Y, RBNode<T> Z){
        if (Y == X.left){
            return (Z == Y.left)? 0:1;
        }else{
            return (Z == Y.left)? 2:3;
        }
    }

    public int getHeight() {
        return 0;
    }
}
