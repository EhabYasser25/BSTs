package CLI;

import Data_Structures.Tree.*;
import Service.CLICommands;
import Service.CommandInvoker;
import Service.Commands;

import java.util.Scanner;

public class Dictionary implements IDictionary {

    BST<String> bst;
    AVL<String> avl;
    RB<String> rb;

    CommandInvoker invoker = new CommandInvoker();

    CLICommands command;

    Commands eCommand;

    TreeType treeType = null;

    public void startProgram() {
        Scanner sc = new Scanner(System.in);
        printIntro();
        System.out.println("Choose Dictionary tree:\n1. AVL tree\n2. Red Black tree");
        System.out.print("> ");
        int option = sc.nextInt();
        initiate(option);
        System.out.println("Please select an option:");
        System.out.println("1. Insert Word");
        System.out.println("2. Delete Word");
        System.out.println("3. Batch Insert");
        System.out.println("4. Batch Delete");
        System.out.println("5. Search");
        System.out.println("6. Size");
        System.out.println("7. Height");
        System.out.println("0. Exit");
        programLoop();
    }

    public void initiate(int option) {
        if(option == 1) {
            this.avl = new AVL<>();
            this.treeType = TreeType.AVL;
        } else if(option == 2) {
            this.rb = new RB<>();
            this.treeType = TreeType.RB;
        }
    }

    public void programLoop() {
        int option = -1;
        Scanner sc = new Scanner(System.in);
        while(option != 0) {
            System.out.print("> ");
            option = sc.nextInt();
            if(setCommand(option) == -1) continue;
            command = invoker.invoke(eCommand);

            switch (treeType) {
                case BST -> command.execute(bst);
                case AVL -> command.execute(avl);
                case RB -> command.execute(rb);
                default -> System.out.println("Tree type not supported!");
            }
            switch (treeType) {
                case BST -> bst.visit(VisitType.DFS);
                case AVL -> avl.visit(VisitType.DFS);
                case RB -> rb.visit(VisitType.DFS);
            }
        }
    }

    public int setCommand(int option) {
        if (option == 0) System.exit(0);
        if (option > 7 || option < 0){
            System.out.println("Invalid input!!");
            return -1;
        }
        eCommand = Commands.values()[option-1];
        return 0;
    }

    public void printIntro() {
        System.out.println("          v .   ._, |_  .,");
        System.out.println("           `-._\\/  .  \\ /    |/_");
        System.out.println("               \\\\  _\\, y | \\//");
        System.out.println("         _\\_.___\\\\, \\\\/ -.\\||");
        System.out.println("           `7-,--.`._||  / / ,");
        System.out.println("           /'     `-. `./ / |/_.'");
        System.out.println("                     |    |//");
        System.out.println("                     |_    /");
        System.out.println("                     |-   |");
        System.out.println("                     |   =|");
        System.out.println("                     |    |");
        System.out.println("--------------------/ ,  . \\--------._");
    }
}