package Service;

import Data_Structures.Node.Node;
import Data_Structures.Tree.Tree;
import Data_Structures.Tree.TreeType;

import java.util.List;

public interface CLICommands {
    public void execute(Tree<String> tree, TreeType type);
}

