import Data_Structures.Node.AVLNode;
import Data_Structures.Node.Node;
import Data_Structures.Tree.AVLTree;
import Data_Structures.Tree.Tree;

public class Main {
    public static void main(String[] args) {
        Node<String> root = new AVLNode<>("apple");
        AVLTree<String> AVL = new AVLTree<>(root.getData());
        AVL.simpleInsert(root, "banana");
        AVL.simpleInsert(root, "cherry");
        AVL.simpleInsert(root, "avocado");
        AVL.simpleInsert(root, "dates");
        AVL.simpleInsert(root, "kiwi");
        AVL.simpleInsert(root, "fig");
        AVL.simpleInsert(root, "lemon");
        AVL.simpleInsert(root, "mango");

        c.right = d;
        d.right = e;
        e.right = g;
        g.left = f;
        c.left = a;
        a.right = b;
        Tree<String> t = new RBTree<>(c);
        System.out.println(t.search("g").left.getData());
    }
}