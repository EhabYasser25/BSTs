package Service;

import Data_Structures.Node.Node;
import Data_Structures.Tree.AVLTree;
import Data_Structures.Tree.RBTree;
import Data_Structures.Tree.Tree;
import Data_Structures.Tree.TreeType;

import java.util.List;
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
        this.word = sc.nextLine();
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
        this.word = sc.nextLine();
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
        this.word = sc.nextLine();
        if(type == TreeType.AVL) {
            AVLTree<String> avl = (AVLTree<String>) tree;
            avl.delete(avl.root, this.word);
        } else if(type == TreeType.RB){
            RBTree<String> rb = (RBTree<String>) tree;
            rb.delete(rb.root, this.word);
        }
    }
}

// TODO merge both insert and delete in one method is possible
class BatchInsert implements CLICommands {
    private List<String> words;
    private String path;
    private Scanner sc = new Scanner(System.in);
    @Override
    public void execute(Tree<String> tree, TreeType type) {
        System.out.println("Enter File path to the file path: ");
        this.path = sc.nextLine();
        FileManager fileReader = new FileManager();
        this.words = fileReader.readFile(this.path);
        // TODO call batch insert method and print confirmation message
    }
}

class BatchDelete implements CLICommands {
    private String path;
    private List<String> words;
    private Scanner sc = new Scanner(System.in);
    @Override
    public void execute(Tree<String> tree, TreeType type) {
        System.out.println("Enter File path to the file path: ");
        this.path = sc.nextLine();
        FileManager fileReader = new FileManager();
        this.words = fileReader.readFile(this.path);
        // TODO call batch delete method and print confirmation message
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