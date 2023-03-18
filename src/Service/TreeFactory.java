package Service;

import Data_Structures.Tree.AVLTree;
import Data_Structures.Tree.RBTree;
import Data_Structures.Tree.Tree;
import Data_Structures.Tree.TreeType;

public class TreeFactory {
    // Tree factory takes the type of the tree and root of the tree and returns a tree.
    // Can be removed if the return type of Tree<T> does not work
    public Tree<String> getTree(TreeType tree) {
        if(tree == TreeType.RB) {
            return new AVLTree<>();
        } else if(tree == TreeType.AVL) {
            return new RBTree<>();
        } else {
            return null;
        }
    }

}
