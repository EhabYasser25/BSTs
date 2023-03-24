package Service;

import Data_Structures.Tree.BST;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class BatchInsert implements CLICommands {
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
        System.out.println(tree.getSize());
        return new Point(found, notFound);
    }
}