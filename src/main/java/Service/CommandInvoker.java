package Service;

import Data_Structures.Tree.BST;

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
    public long execute(BST<String> tree) {
        System.out.print("Enter Word to search: ");
        String word = sc.nextLine();
        boolean found;
        long startTime = System.nanoTime();
        found = tree.search(word);
        long endTime = System.nanoTime();
        if(found) {
            System.out.println("Word " + word + " Exists!!");
        } else {
            System.out.println("Word " + word + " does not exist");
        }
        return (endTime - startTime) / 1000;
    }
}

class Insert implements CLICommands {
    private final Scanner sc = new Scanner(System.in);
    @Override
    public long execute(BST<String> tree) {
        System.out.print("Enter Word to insert: ");
        String word = sc.nextLine();
        boolean found;
        long startTime = System.nanoTime();
        found = tree.insert(word);
        long endTime = System.nanoTime();
        if(found) {
            System.out.println("Word " + word + " inserted successfully!!");
        } else {
            System.out.println("Word " + word + " already exists");
        }
        return (endTime - startTime) / 1000;
    }
}

class Delete implements CLICommands {
    private final Scanner sc = new Scanner(System.in);
    @Override
    public long execute(BST<String> tree) {
        System.out.print("Enter Word to delete: ");
        String word = sc.nextLine();
        boolean found;
        long startTime = System.nanoTime();
        found = tree.delete(word);
        long endTime = System.nanoTime();
        if(found) {
            System.out.println("Word " + word + " deleted successfully!!");
        } else {
            System.out.println("Word " + word + " does not exist!");
        }
        return  (endTime - startTime) / 1000;
    }
}

class BatchInsert implements CLICommands {
    private final Scanner sc = new Scanner(System.in);
    @Override
    public long execute(BST<String> tree) {
        System.out.print("Enter File path to the file path: ");
        System.out.print("> ");
        String path = sc.nextLine();
        FileManager fileReader = new FileManager();
        List<String> words = fileReader.readFile(path);
        new Point();
        long startTime = System.nanoTime();
        Point feedback = batchInsert(tree, words);
        long endTime = System.nanoTime();
        System.out.println("Number of words inserted: " + feedback.y + "\nNumber of words found: " + feedback.x);
        return (endTime - startTime) / 1000;
    }
    public Point batchInsert(BST<String> tree, List<String> items) {
        int found = 0, notFound = 0;
        for (String item : items) {
            if (tree.insert(item)) notFound++;
            else found++;
        }
        return new Point(found, notFound);
    }
}

class BatchDelete implements CLICommands {
    private final Scanner sc = new Scanner(System.in);
    @Override
    public long execute(BST<String> tree) {
        System.out.print("Enter File path to the file path: ");
        System.out.print("> ");
        String path = sc.nextLine();
        FileManager fileReader = new FileManager();
        List<String> words = fileReader.readFile(path);
        long startTime = System.nanoTime();
        Point feedback = batchDelete(tree, words);
        long endTime = System.nanoTime();
        System.out.println("Number of words deleted: " + feedback.x + "\nNumber of words not found: " + feedback.y);
        return (endTime - startTime) / 1000;
    }
    public Point batchDelete(BST<String> tree, List<String> items) {
        int found = 0, notFound = 0;
        for (String item : items) {
            if (tree.delete(item)) found++;
            else notFound++;
        }
        return new Point(found, notFound);
    }
}

class Size implements CLICommands {
    @Override
    public long execute(BST<String> tree) {
        long startTime = System.nanoTime();
        System.out.println("Number of saved words = " + tree.getSize());
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000;
    }
}

class Height implements CLICommands {
    @Override
    public long execute(BST<String> tree) {
        int height;
        long startTime = System.nanoTime();
        height = tree.getHeight();
        long endTime = System.nanoTime();
        System.out.println("The height of your tree is: " + height);
        return (endTime - startTime) / 1000;
    }
}