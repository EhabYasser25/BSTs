import Data_Structures.Node.RBNode;
import Data_Structures.Tree.BST;
import Data_Structures.Tree.RB;
import Data_Structures.Tree.VisitType;

public class Main {
    public static void main(String[] args) {
        RB<Integer> t = new RB<>();
        t.insert(0);
        t.insert(7);
        t.insert(53);
        t.insert(8);
        t.insert(3);
        t.insert(77);
        t.insert(20);
        t.insert(1);
        t.insert(2);
        t.visit(VisitType.DFS);
        t.delete(20);
        System.out.println();
        t.visit(VisitType.DFS);
        t.delete(2);
        System.out.println();
        t.visit(VisitType.DFS);
        t.delete(3);
        System.out.println();
        t.visit(VisitType.DFS);
        t.delete(0);
        System.out.println();
        t.visit(VisitType.DFS);
        t.delete(1);
        System.out.println();
        t.visit(VisitType.DFS);
        t.delete(53);
        System.out.println();
        t.visit(VisitType.DFS);
        t.delete(8);
        System.out.println();
        t.visit(VisitType.DFS);
        t.delete(77);
        System.out.println();
        t.visit(VisitType.DFS);
        t.delete(7);
        System.out.println();
        t.visit(VisitType.DFS);
    }
}