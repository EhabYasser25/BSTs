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

class Search implements CLICommands {

    private String word;

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public void execute() {

    }
}

class Insert implements CLICommands {

    private String word;

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public void execute() {

    }
}

class Delete implements CLICommands {

    private String word;

    public void setWord(String word) {
        this.word = word;
    }

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

class Size implements CLICommands {

    private Node<String> root;

    public void setRoot(Node<String> root) {
        this.root = root;
    }

    @Override
    public void execute() {

    }
}

class Height implements CLICommands {

    private Node<String> root;

    public void setRoot(Node<String> root) {
        this.root = root;
    }

    @Override
    public void execute() {

    }
}






