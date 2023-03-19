package Data_Structures.Node;

public class NilNode<T extends Comparable<T>> extends RBNode<T>{
    public NilNode(){
        this.setBlack(true);
    }
}
