package CLI;

import Data_Structures.Node.Node;

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

abstract class OneOperation {
    private String word;
    private Node<String> root;
    public void setData(String word, Node<String> root) {
        this.word = word;
        this.root = root;
    }
}

class Search extends OneOperation implements CLICommands {
    @Override
    public void execute() {

    }
}

class Insert extends OneOperation implements CLICommands {

    @Override
    public void execute() {

    }
}

class Delete extends OneOperation implements CLICommands {

    @Override
    public void execute() {

    }
}

class BatchInsert implements CLICommands {

    @Override
    public void execute() {

    }
}

class BatchDelete implements CLICommands {

    @Override
    public void execute() {

    }
}

class Size extends OneOperation implements CLICommands {

    @Override
    public void execute() {

    }
}

class Height extends OneOperation implements CLICommands {

    @Override
    public void execute() {

    }
}