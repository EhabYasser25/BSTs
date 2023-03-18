package Data_Structures.Tree;

import Data_Structures.Node.AVLNode;
import Data_Structures.Node.Node;
import Data_Structures.Node.RBNode;

public class AVLTree<T extends Comparable<T>> extends Tree<T> {
   /**
    * Each tree has its own root, so it is removed from the parent class
    * We can just add it for normal node, but it's preferred to make each tree has its own root!
    * */
    AVLNode<T> root;

    public AVLTree(T root) {
        this.root = new AVLNode<>(root);
    }

    public AVLTree() { }

    public AVLNode<T> insert(T data) {
        return null;
    }

    public AVLNode<T> delete(T data) {
        return null;
    }

    public int getHeight() {
        return 0;
    }

    public void update(Node<T> node) {

    }

    public void update(AVLNode<T> Y){ //CALLED ON PARENT AFTER INSERTION AND DELETION
        if (Y == null) return;
        int bf = Y.setBalance();

        if (bf == -2){
            if (Y.left.getBalance() == 1){
                //LR
            }else{
                //LL
            }
        }else if (bf == 2){
            if (Y.right.getBalance() == 1){
                //RL
            }else{
                //RR
            }
        }else{
            Y.setHeight();
        }

        update(Y.parent);
    }

}
