package Service;

import Data_Structures.Node.Node;
import Data_Structures.Tree.AVLTree;
import Data_Structures.Tree.RBTree;
import Data_Structures.Tree.Tree;
import Data_Structures.Tree.TreeType;

import java.util.Scanner;

public class CommandInvoker {

    public CLICommands invoke(Commands command) {
        switch (command) {
            case SEARCH:
                return new Search();
            case INSERT:
                return new Insert();
            case DELETE:
                return new Delete();
            case BATCHINSERT:
                return new BatchInsert();
            case BATCHDELETE:
                return new BatchDelete();
            case SIZE:
                return new Size();
            case HEIGHT:
                return new Height();
            default:
                return null;
        }
    }
}

class Search implements CLICommands {
    private String word;
    private Scanner sc = new Scanner(System.in);
    @Override
    public void execute(Tree<String> tree, TreeType type) {
        System.out.print("Enter Word to search: ");
        this.word = sc.next();
        if(type == TreeType.AVL) {
            AVLTree<String> avl = (AVLTree<String>) tree;
            avl.search(avl.root, this.word);
        } else if(type == TreeType.RB){
            RBTree<String> rb = (RBTree<String>) tree;
            rb.search(rb.root, this.word);
        }
    }

}

class Insert implements CLICommands {
    private String word;
    private Scanner sc = new Scanner(System.in);
    @Override
    public void execute(Tree<String> tree, TreeType type) {
        System.out.print("Enter Word to Insert: ");
        this.word = sc.next();
        if(type == TreeType.AVL) {
            AVLTree<String> avl = (AVLTree<String>) tree;
            avl.insert(this.word);
        } else if(type == TreeType.RB){
            RBTree<String> rb = (RBTree<String>) tree;
            rb.insert(this.word);
        }
    }
}

class Delete implements CLICommands {
    private String word;
    private Scanner sc = new Scanner(System.in);

    @Override
    public void execute(Tree<String> tree, TreeType type) {
        System.out.print("Enter Word to delete: ");
        this.word = sc.next();
        if(type == TreeType.AVL) {
            AVLTree<String> avl = (AVLTree<String>) tree;
            avl.delete(avl.root, this.word);
        } else if(type == TreeType.RB){
            RBTree<String> rb = (RBTree<String>) tree;
            rb.delete(rb.root, this.word);
        }
    }
}

class BatchInsert implements CLICommands {
    private String word;

    @Override
    public void execute(Tree<String> tree, TreeType type) {

    }
}

class BatchDelete implements CLICommands {
    private String word;

    @Override
    public void execute(Tree<String> tree, TreeType type) {

    }
}

class Size implements CLICommands {
    private String word;

    @Override
    public void execute(Tree<String> tree, TreeType type) {
        System.out.println("The size of your tree nodes = " + tree.getSize());
    }
}

class Height implements CLICommands {
    private String word;

    private int height;
    @Override
    public void execute(Tree<String> tree, TreeType type) {
        if(type == TreeType.AVL) {
            AVLTree<String> avl = (AVLTree<String>) tree;
            this.height = avl.getHeight();
        } else if(type == TreeType.RB){
            RBTree<String> rb = (RBTree<String>) tree;
            this.height = rb.getHeight();
        }
        System.out.println("The height of your tree is: " + this.height);
    }
}