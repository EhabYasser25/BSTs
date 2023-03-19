package Service;

import Data_Structures.Tree.AVL;
import Data_Structures.Tree.BST;
import Data_Structures.Tree.RB;
import Data_Structures.Tree.TreeType;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class CommandInvoker {

    public CLICommands invoke(Commands command) {
        return switch (command) {
            case SEARCH -> new Search();
            case INSERT -> new Insert();
            case DELETE -> new Delete();
            case BATCHINSERT -> new BatchInsert();
            case BATCHDELETE -> new BatchDelete();
            case SIZE -> new Size();
            case HEIGHT -> new Height();
        };
    }
}

class Search implements CLICommands {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void execute(BST<String> tree, TreeType type) {
        System.out.print("Enter Word to search: ");
        String word = sc.nextLine();
        boolean found = false;
        if(type == TreeType.AVL) {
            AVL<String> avl = (AVL<String>) tree;
            found = avl.search(word);
        } else if(type == TreeType.RB){
            RB<String> rb = (RB<String>) tree;
            found = rb.search(word);
        }
        if(found) {
            System.out.println("Word " + word + " Exists!!");
        } else {
            System.out.println("Word " + word + " does not exist");
        }
    }
}

class Insert implements CLICommands {
    private final Scanner sc = new Scanner(System.in);
    @Override
    public void execute(BST<String> tree, TreeType type) {
        System.out.print("Enter Word to insert: ");
        String word = sc.nextLine();
        boolean found = false;
        if(type == TreeType.AVL) {
            AVL<String> avl = (AVL<String>) tree;
            found = avl.insert(word);
        } else if(type == TreeType.RB){
            RB<String> rb = (RB<String>) tree;
            found = rb.insert(word);
        }
        if(found) {
            System.out.println("Word " + word + " inserted successfully!!");
        } else {
            System.out.println("Word " + word + " already exists");
        }
    }
}

class Delete implements CLICommands {

    private final Scanner sc = new Scanner(System.in);
    @Override
    public void execute(BST<String> tree, TreeType type) {
        System.out.print("Enter Word to delete: ");
        String word = sc.nextLine();
        boolean found = false;
        if(type == TreeType.AVL) {
            AVL<String> avl = (AVL<String>) tree;
            found = avl.delete(word);
        } else if(type == TreeType.RB){
            RB<String> rb = (RB<String>) tree;
            found = rb.delete(word);
        }
        if(found) {
            System.out.println("Word " + word + " deleted successfully!!");
        } else {
            System.out.println("Word " + word + " does not exist!");
        }
    }
}

class BatchInsert implements CLICommands {
    private final Scanner sc = new Scanner(System.in);
    @Override
    public void execute(BST<String> tree, TreeType type) {
        System.out.print("Enter File path to the file path: ");
        System.out.print("> ");
        String path = sc.nextLine();
        FileManager fileReader = new FileManager();
        List<String> words = fileReader.readFile(path);
        Point feedback = new Point();
        if(type == TreeType.AVL) {
            AVL<String> avl = (AVL<String>) tree;
            feedback = avl.batchInsert(words);
        } else if(type == TreeType.RB){
            RB<String> rb = (RB<String>) tree;
            feedback = rb.batchInsert(words);
        }
        System.out.println("Number of words inserted: " + feedback.y + "\nNumber of words found: " + feedback.x);
    }
}

class BatchDelete implements CLICommands {
    private final Scanner sc = new Scanner(System.in);
    @Override
    public void execute(BST<String> tree, TreeType type) {
        System.out.print("Enter File path to the file path: ");
        System.out.print("> ");
        String path = sc.nextLine();
        FileManager fileReader = new FileManager();
        List<String> words = fileReader.readFile(path);
        Point feedback = new Point();
        if(type == TreeType.AVL) {
            AVL<String> avl = (AVL<String>) tree;
            feedback = avl.batchDelete(words);
        } else if(type == TreeType.RB){
            RB<String> rb = (RB<String>) tree;
            feedback = rb.batchDelete(words);
        }
        System.out.println("Number of words deleted: " + feedback.x + "\nNumber of words not found: " + feedback.y);
    }
}

class Size implements CLICommands {
    @Override
    public void execute(BST<String> tree, TreeType type) {
        System.out.println("Number of saved words = " + tree.getSize());
    }
}

class Height implements CLICommands {
    @Override
    public void execute(BST<String> tree, TreeType type) {
        int height = 0;
        if(type == TreeType.AVL) {
            AVL<String> avl = (AVL<String>) tree;
            height = avl.getHeight();
        } else if(type == TreeType.RB) {
            RB<String> rb = (RB<String>) tree;
            height = rb.getHeight();
        }
        System.out.println("The height of your tree is: " + height);
    }
}