package CLI;

import Data_Structures.Tree.Tree;
import Data_Structures.Tree.TreeType;
import Service.CLICommands;
import Service.CommandInvoker;
import Service.Commands;
import Service.TreeFactory;

import java.util.Scanner;

public class Dictionary implements IDictionary {

    TreeFactory factory = new TreeFactory();

    Tree<String> tree;

    CommandInvoker invoker = new CommandInvoker();

    CLICommands command;

    Commands eCommand;

    public void startProgram() {
        Scanner sc = new Scanner(System.in);
        printIntro();
        System.out.println("Choose Dictionary tree:\n1. AVL tree\n2. Red Black tree");
        System.out.print("> ");
        int option = sc.nextInt();
        initiate(option);
        System.out.println("Please select an option:");
        System.out.println("1. Insert word");
        System.out.println("2. Delete word");
        System.out.println("3. Search word");
        System.out.println("4. Batch insert");
        System.out.println("5. Batch delete");
        System.out.println("0. Exit");
        programLoop();
    }

    public void initiate(int option) {
        if(option == 1) {
            this.tree = this.factory.getTree(TreeType.AVL);
        } else if(option == 2) {
            this.tree = this.factory.getTree(TreeType.RB);
        }
    }

    public void programLoop() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("> ");
            int option = sc.nextInt();
            if(setCommand(option) == -1) continue;
            command = invoker.invoke(eCommand);
            // TODO command.setData(word, root);
            command.execute();
        }
    }

    public int setCommand(int option) {
        switch (option) {
            case 0:
                System.exit(0);
            case 1:
                eCommand = Commands.INSERT;
                break;
            case 2:
                eCommand = Commands.DELETE;
                break;
            case 3:
                eCommand = Commands.SEARCH;
                break;
            case 4:
                eCommand = Commands.BATCHINSERT;
                break;
            case 5:
                eCommand = Commands.BATCHDELETE;
            default:
                System.out.println("Invalid input!!");
                return -1;
        }
        return 0;
    }

    public void printIntro() {
        System.out.println("          v .   ._, |_  .,");
        System.out.println("           `-._\\/  .  \\ /    |/_");
        System.out.println("               \\\\  _\\, y | \\//");
        System.out.println("         _\\_.___\\\\, \\\\/ -.\\||");
        System.out.println("           `7-,--.`._||  / / ,");
        System.out.println("           /'     `-. `./ / |/_.\'");
        System.out.println("                     |    |//");
        System.out.println("                     |_    /");
        System.out.println("                     |-   |");
        System.out.println("                     |   =|");
        System.out.println("                     |    |");
        System.out.println("--------------------/ ,  . \\--------._");
    }
}
