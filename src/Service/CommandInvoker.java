package Service;

import Data_Structures.Tree.AVL;
import Data_Structures.Tree.BST;
import Data_Structures.Tree.RB;
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
    public void execute(BST<String> tree, TreeType type) {
        System.out.print("Enter Word to search: ");
        this.word = sc.nextLine();
        if(type == TreeType.AVL) {
            AVL<String> avl = (AVL<String>) tree;
            avl.search(this.word);
        } else if(type == TreeType.RB){
            RB<String> rb = (RB<String>) tree;
            rb.search(this.word);
        }
    }
}

class Insert implements CLICommands {
    private String word;
    private Scanner sc = new Scanner(System.in);
    @Override
    public void execute(BST<String> tree, TreeType type) {
        System.out.print("Enter Word to Insert: ");
        this.word = sc.nextLine();
        if(type == TreeType.AVL) {
            AVL<String> avl = (AVL<String>) tree;
            avl.insert(this.word);
        } else if(type == TreeType.RB){
            RB<String> rb = (RB<String>) tree;
            rb.insert(this.word);
        }
    }
}

class Delete implements CLICommands {

    private String word;
    private Scanner sc = new Scanner(System.in);
    @Override
    public void execute(BST<String> tree, TreeType type) {
        System.out.print("Enter Word to delete: ");
        this.word = sc.nextLine();
        if(type == TreeType.AVL) {
            AVL<String> avl = (AVL<String>) tree;
            avl.delete(this.word);
        } else if(type == TreeType.RB){
            RB<String> rb = (RB<String>) tree;
            rb.delete(this.word);
        }
    }
}

class BatchInsert implements CLICommands {
    private List<String> words;
    private String path;
    private Scanner sc = new Scanner(System.in);
    @Override
    public void execute(BST<String> tree, TreeType type) {
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
    public void execute(BST<String> tree, TreeType type) {
        System.out.println("Enter File path to the file path: ");
        this.path = sc.nextLine();
        FileManager fileReader = new FileManager();
        this.words = fileReader.readFile(this.path);
    }
}

class Size implements CLICommands {
    private String word;
    @Override
    public void execute(BST<String> tree, TreeType type) {
        System.out.println("The size of your tree nodes = " + tree.getSize());
    }
}

class Height implements CLICommands {
    private int height;
    @Override
    public void execute(BST<String> tree, TreeType type) {
        if(type == TreeType.AVL) {
            AVL<String> avl = (AVL<String>) tree;
            this.height = avl.getHeight();
        } else if(type == TreeType.RB) {
            RB<String> rb = (RB<String>) tree;
            //TODO getHeight
        }
        System.out.println("The height of your tree is: " + this.height);
    }
}