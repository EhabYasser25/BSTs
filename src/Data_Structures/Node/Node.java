package Data_Structures.Node;

public abstract class Node<T> implements INode<T> {
    protected T data;
    Node<T> parent = null;
    Node<T> left = null;
    Node<T> right = null;

    @Override
    public T getData() {
            return data;
    }

    @Override
    public void setData(T data){
        this.data = data;
    }

}
