package Service;

import Data_Structures.Tree.BST;
import Data_Structures.Tree.TreeType;

public interface CLICommands {
    public void execute(BST<String> tree, TreeType type);
}