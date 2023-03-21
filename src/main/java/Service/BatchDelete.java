package Service;

import Data_Structures.Tree.BST;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class BatchDelete implements CLICommands {
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