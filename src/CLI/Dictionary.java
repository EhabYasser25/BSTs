package CLI;

import Data_Structures.Tree.TreeType;
import Service.TreeFactory;

import java.util.Scanner;

public class Dictionary implements IDictionary {

    TreeFactory factory = new TreeFactory();

    public void startProgram() {
        Scanner sc = new Scanner(System.in);
        printIntro();
        System.out.println("Choose Dictionary tree:\n1. AVL tree\n2. Red Black tree\n\n");
        int option = sc.nextInt();
        initiate(option);
    }

    public void printIntro() {
        String[] bookDesign = {
                "          .-----.         .-----.         .-----.",
                "         //     \\\\       //     \\\\       //     \\\\",
                "        ||       ||     ||       ||     ||       ||",
                "        ||       ||     ||       ||     ||       ||",
                "        ||       ||     ||       ||     ||       ||",
                "        ||_______||     ||_______||     ||_______||",
                "        |         |     |         |     |         |",
                "        |         |     |         |     |         |",
                "        |         |     |         |     |         |",
                "        |_________|     |_________|     |_________|"
        };

        for (String s : bookDesign) {
            for (int j = 0; j < 1; j++) {
                System.out.print(s + "   ");
            }
            System.out.println(); // Move to the next line after each row is printed
        }
    }

    public void initiate(int option) {
        if(option == 1) {
            this.factory.getTree(TreeType.AVL);
        } else if(option == 2) {
            this.factory.getTree(TreeType.RB);
        }
    }
}
