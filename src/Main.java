import Data_Structures.Node.Node;
import Data_Structures.Node.RBNode;
import Data_Structures.Tree.RBTree;
import Data_Structures.Tree.Tree;

public class Main {
    public static void main(String[] args) {
        RBNode<String> a = new RBNode<>("a");
        RBNode<String> b = new RBNode<>("b");
        RBNode<String> c = new RBNode<>("c");
        RBNode<String> d = new RBNode<>("d");
        RBNode<String> e = new RBNode<>("e");
        RBNode<String> f = new RBNode<>("f");
        RBNode<String> g = new RBNode<>("g");

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