package Service;

import Data_Structures.Tree.AVL;
import Data_Structures.Tree.RB;
import Data_Structures.Tree.BST;
import Data_Structures.Tree.TreeType;

public class TreeFactory {
    // Tree factory takes the type of the tree and root of the tree and returns a tree.
    // Can be removed if the return type of Tree<T> does not work
    public BST<String> getTree(TreeType tree) {
        if(tree == TreeType.RB) {
            return new AVL<>();
        } else if(tree == TreeType.AVL) {
            return new RB<>();
        } else {
            return null;
        }
    }

}
