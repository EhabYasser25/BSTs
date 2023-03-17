import Data_Structures.Node.AVLNode;
import Data_Structures.Node.Node;
import Data_Structures.Tree.AVLTree;
import Data_Structures.Tree.Tree;

public class Main {
    public static void main(String[] args) {
        Node<Integer> root = new AVLNode<>(10);
        Tree<Integer> tree = new AVLTree<>(root);
    }
}